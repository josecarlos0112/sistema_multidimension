package user_interface;

import modelo.Pareja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PanelModelado extends JPanel {
    private DefaultListModel<Pareja> listModel;
    private JList<Pareja> list;
    private Random random = new Random();

    public PanelModelado() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        JPanel panelButtons = new JPanel();
        JButton addButton = new JButton("Agregar Aleatorio");
        JButton addManualButton = new JButton("Agregar Manualmente");
        JButton removeButton = new JButton("Eliminar");
        JButton modifyButton = new JButton("Modificar Manualmente");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Genera una nueva pareja con elementos aleatorios y la agrega a la lista
                Pareja pareja = new Pareja(random.nextInt(100), random.nextInt(100));
                listModel.addElement(pareja);
            }
        });

        addManualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicita al usuario que ingrese los valores para una nueva pareja y la agrega a la lista
                int primerElemento = solicitarEntero("Ingrese el primer elemento de la pareja:");
                int segundoElemento = solicitarEntero("Ingrese el segundo elemento de la pareja:");
                Pareja pareja = new Pareja(primerElemento, segundoElemento);
                listModel.addElement(pareja);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Elimina la pareja seleccionada actualmente de la lista
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicita al usuario que ingrese los nuevos valores para la pareja seleccionada y la modifica
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    int primerElemento = solicitarEntero("Ingrese el nuevo primer elemento de la pareja:");
                    int segundoElemento = solicitarEntero("Ingrese el nuevo segundo elemento de la pareja:");
                    Pareja pareja = new Pareja(primerElemento, segundoElemento);
                    listModel.set(selectedIndex, pareja);
                }
            }
        });

        panelButtons.add(addButton);
        panelButtons.add(addManualButton);
        panelButtons.add(removeButton);
        panelButtons.add(modifyButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private int solicitarEntero(String mensaje) {
        Integer valor = null;
        do {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                valor = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El valor ingresado debe ser un número entero y no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (valor == null);
        return valor;
    }
}