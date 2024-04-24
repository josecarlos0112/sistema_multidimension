package user_interface;
package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;
import com.thealgorithms.sorts.QuickSort;
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
    private QuickSort quickSort;

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

        // Crear una instancia de QuickSort
        quickSort = new QuickSort();

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
        quickSort.sort(registrosArray);
        registros = new ArrayList<>(Arrays.asList(registrosArray));
        actualizarTabla();
    }

    private void ordenarPorVenta() {
        Registro[] registrosArray = registros.toArray(new Registro[0]);
        quickSort.sort(registrosArray);
        registros = new ArrayList<>(Arrays.asList(registrosArray));
        actualizarTabla();
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Registro registro : registros) {
            tableModel.addRow(new Object[]{registro.getNombre(), registro.getVenta()});
        }
    }
}