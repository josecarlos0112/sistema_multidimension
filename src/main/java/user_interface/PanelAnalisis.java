package user_interface;// Clase PanelAnalisis

import data.Registro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
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
        registros.sort(Comparator.comparing(Registro::getNombre));
        actualizarTabla();
    }

    private void ordenarPorVenta() {
        registros.sort(Comparator.comparing(Registro::getVenta));
        actualizarTabla();
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Registro registro : registros) {
            tableModel.addRow(new Object[]{registro.getNombre(), registro.getVenta()});
        }
    }
}