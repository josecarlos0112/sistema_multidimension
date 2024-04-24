package user_interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.TreeMap;

public class PanelIndexacion extends JPanel {
    private TreeMap<String, String> archivos;
    private DefaultTableModel tableModel;
    private JLabel directorioActualLabel;

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

        // Crear etiquetas descriptivas
        JLabel indexarLabel = new JLabel("Seleccione un directorio para indexar sus archivos:");
        JLabel listarLabel = new JLabel("Presione para listar los archivos indexados:");
        directorioActualLabel = new JLabel("Directorio actual: Ninguno");

        // Agregar los botones y las etiquetas al panel
        JPanel panelButtons = new JPanel(new GridLayout(3, 2));
        panelButtons.add(indexarLabel);
        panelButtons.add(indexarButton);
        panelButtons.add(listarLabel);
        panelButtons.add(listarButton);
        panelButtons.add(directorioActualLabel);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void indexarArchivos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            archivos.clear(); // Limpiar el TreeMap antes de indexar
            indexarDirectorio(selectedDirectory);
            directorioActualLabel.setText("Directorio actual: " + selectedDirectory.getAbsolutePath());
            JOptionPane.showMessageDialog(null, "Los archivos han sido indexados correctamente.");
        }
    }

    private void indexarDirectorio(File directorio) {
        File[] files = directorio.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isHidden()) { // Ignorar los archivos y carpetas ocultos
                    if (file.isDirectory()) {
                        indexarDirectorio(file);
                    } else {
                        archivos.put(file.getName(), file.getAbsolutePath());
                    }
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