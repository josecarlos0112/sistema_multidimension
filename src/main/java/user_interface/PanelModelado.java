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
        JButton addButton = new JButton("Agregar");
        JButton removeButton = new JButton("Eliminar");
        JButton modifyButton = new JButton("Modificar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Genera una nueva pareja con elementos aleatorios y la agrega a la lista
                Pareja pareja = new Pareja(random.nextInt(100), random.nextInt(100));
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
                // Modifica la pareja seleccionada actualmente en la lista a una nueva pareja generada aleatoriamente
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Pareja pareja = new Pareja(random.nextInt(100), random.nextInt(100));
                    listModel.set(selectedIndex, pareja);
                }
            }
        });

        panelButtons.add(addButton);
        panelButtons.add(removeButton);
        panelButtons.add(modifyButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }
}