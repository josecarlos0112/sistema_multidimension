package user_interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class PanelRelaciones extends JPanel {
    private HashMap<String, String> relaciones;
    private JTable table;
    private DefaultTableModel tableModel;

    public PanelRelaciones() {
        setLayout(new BorderLayout());

        // Crear el HashMap para almacenar las relaciones
        relaciones = new HashMap<>();

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel(new String[]{"Clave", "Valor"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear los botones
        JButton agregarRelacionButton = new JButton("Agregar relación");
        JButton recuperarRelacionButton = new JButton("Recuperar relación");

        // Agregar los action listeners a los botones
        agregarRelacionButton.addActionListener(e -> agregarRelacion());
        recuperarRelacionButton.addActionListener(e -> recuperarRelacion());

        // Agregar los botones al panel
        JPanel panelButtons = new JPanel();
        panelButtons.add(agregarRelacionButton);
        panelButtons.add(recuperarRelacionButton);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void agregarRelacion() {
        String clave = JOptionPane.showInputDialog("Ingrese la clave de la relación:");
        String valor = JOptionPane.showInputDialog("Ingrese el valor de la relación:");
        relaciones.put(clave, valor);
        actualizarTabla();
    }

    private void recuperarRelacion() {
        String clave = JOptionPane.showInputDialog("Ingrese la clave de la relación que desea recuperar:");
        String valor = relaciones.get(clave);
        if (valor != null) {
            JOptionPane.showMessageDialog(null, "El valor asociado a la clave '" + clave + "' es '" + valor + "'.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna relación con la clave '" + clave + "'.");
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (String clave : relaciones.keySet()) {
            tableModel.addRow(new Object[]{clave, relaciones.get(clave)});
        }
    }
}