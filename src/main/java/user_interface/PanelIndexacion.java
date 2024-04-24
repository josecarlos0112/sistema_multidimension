package user_interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.TreeMap;

public class PanelIndexacion extends JPanel {
    private TreeMap<String, String> archivos;
    private DefaultTableModel tableModel;

    public PanelIndexacion() {
        setLayout(new BorderLayout());

        // Crear el TreeMap para almacenar los nombres de los archivos y sus rutas
        archivos = new TreeMap<>();

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel(new String[]{"Nombre del archivo", "Ruta completa"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear los botones
        JButton indexarButton = new JButton("Indexar archivos");
        JButton listarButton = new JButton("Listar archivos");

        // Agregar los action listeners a los botones
        indexarButton.addActionListener(e -> indexarArchivos());
        listarButton.addActionListener(e -> listarArchivos());

        // Agregar los botones al panel
        JPanel panelButtons = new JPanel();
        panelButtons.add(indexarButton);
        panelButtons.add(listarButton);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void indexarArchivos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            indexarDirectorio(selectedDirectory);
            JOptionPane.showMessageDialog(null, "Los archivos han sido indexados correctamente.");
        }
    }

    private void indexarDirectorio(File directorio) {
        File[] files = directorio.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    indexarDirectorio(file);
                } else {
                    archivos.put(file.getName(), file.getAbsolutePath());
                }
            }
        }
    }

    private void listarArchivos() {
        tableModel.setRowCount(0);
        for (String nombreArchivo : archivos.keySet()) {
            tableModel.addRow(new Object[]{nombreArchivo, archivos.get(nombreArchivo)});
        }
    }
}