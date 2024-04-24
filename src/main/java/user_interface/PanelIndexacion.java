package user_interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class PanelIndexacion extends JPanel {
    private final TreeMap<String, String> archivos;
    private final List<String> ordenIndexacion;
    private final DefaultTableModel tableModel;
    private final JLabel directorioActualLabel;
    private boolean ordenAlfabetico;

    public PanelIndexacion() {
        setLayout(new BorderLayout());

        // Crear el TreeMap para almacenar los nombres de los archivos y sus rutas
        archivos = new TreeMap<>();
        ordenIndexacion = new ArrayList<>();
        ordenAlfabetico = true;

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel(new String[]{"Nombre del archivo", "Ruta completa"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear los botones
        JButton indexarButton = new JButton("Indexar archivos");
        JButton listarButton = new JButton("Listar archivos");
        JButton ordenarButton = new JButton("Ordenar alfabéticamente");

        // Agregar los action listeners a los botones
        indexarButton.addActionListener(e -> indexarArchivos());
        listarButton.addActionListener(e -> listarArchivos());
        ordenarButton.addActionListener(e -> {
            ordenAlfabetico = !ordenAlfabetico;
            listarArchivos();
        });

        // Crear etiquetas descriptivas
        JLabel indexarLabel = new JLabel("Seleccione un directorio para indexar sus archivos:");
        JLabel listarLabel = new JLabel("Presione para listar los archivos indexados:");
        directorioActualLabel = new JLabel("Directorio actual: Ninguno");

        // Agregar los botones y las etiquetas al panel
        JPanel panelButtons = new JPanel(new GridLayout(4, 2));
        panelButtons.add(indexarLabel);
        panelButtons.add(indexarButton);
        panelButtons.add(listarLabel);
        panelButtons.add(listarButton);
        panelButtons.add(new JLabel("Cambiar orden de listado:"));
        panelButtons.add(ordenarButton);
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
            ordenIndexacion.clear(); // Limpiar la lista de orden de indexación
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
                        ordenIndexacion.add(file.getName());
                    }
                }
            }
        }
    }

    private void listarArchivos() {
        tableModel.setRowCount(0);
        if (ordenAlfabetico) {
            for (String nombreArchivo : archivos.keySet()) {
                tableModel.addRow(new Object[]{nombreArchivo, archivos.get(nombreArchivo)});
            }
        } else {
            for (String nombreArchivo : ordenIndexacion) {
                tableModel.addRow(new Object[]{nombreArchivo, archivos.get(nombreArchivo)});
            }
        }
    }
}