package user_interface;

import modelo.Pareja;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelRelaciones extends JPanel {
    private HashMap<String, String> relaciones;

    public PanelRelaciones() {
        setLayout(new BorderLayout());

        // Crear el HashMap para almacenar las relaciones
        relaciones = new HashMap<>();

        // Crear los botones
        JButton agregarRelacionButton = new JButton("Agregar relación");
        JButton recuperarRelacionButton = new JButton("Recuperar relación");

        // Agregar los action listeners a los botones
        agregarRelacionButton.addActionListener(e -> agregarRelacion());
        recuperarRelacionButton.addActionListener(e -> recuperarRelacion());

        // Agregar los botones al panel
        add(agregarRelacionButton, BorderLayout.NORTH);
        add(recuperarRelacionButton, BorderLayout.SOUTH);
    }

    private void agregarRelacion() {
        String clave = JOptionPane.showInputDialog("Ingrese la clave de la relación:");
        String valor = JOptionPane.showInputDialog("Ingrese el valor de la relación:");
        relaciones.put(clave, valor);
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
}