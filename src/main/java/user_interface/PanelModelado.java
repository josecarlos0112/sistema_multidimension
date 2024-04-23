package user_interface;

import modelo.Pareja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelModelado extends JPanel {
    private DefaultListModel<Pareja> listModel;
    private JList<Pareja> list;

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
                // Aquí puedes agregar el código para agregar una nueva pareja a la lista
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar el código para eliminar una pareja de la lista
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar el código para modificar una pareja de la lista
            }
        });

        panelButtons.add(addButton);
        panelButtons.add(removeButton);
        panelButtons.add(modifyButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }
}