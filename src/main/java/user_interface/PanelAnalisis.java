package user_interface;

import data.Registro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        // Crear los botones
        JButton ordenarPorNombreButton = new JButton("Ordenar por nombre");
        JButton ordenarPorVentaButton = new JButton("Ordenar por venta");

        // Agregar los action listeners a los botones
        ordenarPorNombreButton.addActionListener(e -> ordenarPorNombre());
        ordenarPorVentaButton.addActionListener(e -> ordenarPorVenta());

        // Agregar los componentes al panel
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(ordenarPorNombreButton, BorderLayout.NORTH);
        add(ordenarPorVentaButton, BorderLayout.SOUTH);
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