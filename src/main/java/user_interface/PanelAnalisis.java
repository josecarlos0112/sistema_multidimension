package user_interface;

import data.Registro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PanelAnalisis extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Registro> registros;

    public PanelAnalisis() {
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel(new String[]{"Nombre", "Venta"}, 0);
        table = new JTable(tableModel);

        // Crear la lista de registros
        registros = new ArrayList<>();

        agregarRegistro(new Registro("Carlos", 100.0));
        agregarRegistro(new Registro("Ana", 200.0));
        agregarRegistro(new Registro("Luis", 150.0));
        agregarRegistro(new Registro("Maria", 250.0));
        agregarRegistro(new Registro("Juan", 300.0));
        agregarRegistro(new Registro("Sofia", 350.0));
        agregarRegistro(new Registro("Pedro", 400.0));
        agregarRegistro(new Registro("Lucia", 450.0));
        agregarRegistro(new Registro("Pablo", 500.0));
        agregarRegistro(new Registro("Carmen", 550.0));
        agregarRegistro(new Registro("Jose", 600.0));
        agregarRegistro(new Registro("Teresa", 650.0));
        agregarRegistro(new Registro("Antonio", 700.0));
        agregarRegistro(new Registro("Isabel", 750.0));
        agregarRegistro(new Registro("Francisco", 800.0));
        agregarRegistro(new Registro("Laura", 850.0));
        agregarRegistro(new Registro("Javier", 900.0));
        agregarRegistro(new Registro("Susana", 950.0));

        // Crear los botones
        JButton ordenarPorNombreButton = new JButton("Ordenar por nombre");
        JButton ordenarPorVentaButton = new JButton("Ordenar por venta");
        JButton filtrarPorVentaButton = new JButton("Filtrar por venta");
        JButton filtrarPorRangoDeVentaButton = new JButton("Filtrar por rango de venta");

        // Agregar los action listeners a los botones
        ordenarPorNombreButton.addActionListener(e -> ordenarPorNombre());
        ordenarPorVentaButton.addActionListener(e -> ordenarPorVenta());
        filtrarPorVentaButton.addActionListener(e -> filtrarPorVenta());
        filtrarPorRangoDeVentaButton.addActionListener(e -> filtrarPorRangoDeVenta());

        // Agregar los componentes al panel
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(ordenarPorNombreButton, BorderLayout.NORTH);
        add(ordenarPorVentaButton, BorderLayout.SOUTH);
        add(filtrarPorVentaButton, BorderLayout.EAST);
        add(filtrarPorRangoDeVentaButton, BorderLayout.WEST);
    }

    public void agregarRegistro(Registro registro) {
        registros.add(registro);
        tableModel.addRow(new Object[]{registro.getNombre(), registro.getVenta()});
    }

    private void ordenarPorNombre() {
        Registro[] registrosArray = registros.toArray(new Registro[0]);
        quickSort(registrosArray, 0, registrosArray.length - 1, (a, b) -> a.getNombre().compareTo(b.getNombre()));
        registros = new ArrayList<>(Arrays.asList(registrosArray));
        actualizarTabla();
    }

    private void ordenarPorVenta() {
        Registro[] registrosArray = registros.toArray(new Registro[0]);
        quickSort(registrosArray, 0, registrosArray.length - 1, (a, b) -> Double.compare(a.getVenta(), b.getVenta()));
        registros = new ArrayList<>(Arrays.asList(registrosArray));
        actualizarTabla();
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Registro registro : registros) {
            tableModel.addRow(new Object[]{registro.getNombre(), registro.getVenta()});
        }
    }

    private void filtrarPorVenta() {
        String input = JOptionPane.showInputDialog("Ingrese el valor de venta mínimo:");
        try {
            double minVenta = Double.parseDouble(input);
            registros = registros.stream()
                    .filter(registro -> registro.getVenta() >= minVenta)
                    .collect(Collectors.toList());
            actualizarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarPorRangoDeVenta() {
        String inputMin = JOptionPane.showInputDialog("Ingrese el valor de venta mínimo:");
        String inputMax = JOptionPane.showInputDialog("Ingrese el valor de venta máximo:");
        try {
            double minVenta = Double.parseDouble(inputMin);
            double maxVenta = Double.parseDouble(inputMax);
            registros = registros.stream()
                    .filter(registro -> registro.getVenta() >= minVenta && registro.getVenta() <= maxVenta)
                    .collect(Collectors.toList());
            actualizarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los valores ingresados deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private <T> void quickSort(T[] array, int left, int right, java.util.Comparator<T> comparator) {
        if (left < right) {
            int pivot = partition(array, left, right, comparator);
            quickSort(array, left, pivot - 1, comparator);
            quickSort(array, pivot + 1, right, comparator);
        }
    }

    private <T> int partition(T[] array, int left, int right, java.util.Comparator<T> comparator) {
        T pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        return i + 1;
    }
}