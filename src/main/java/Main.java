import user_interface.*;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JFrame mainFrame;
    public void funcionBoton1() {
        //System.out.println("Botón 1 presionado");
        PanelModelado panelModelado = new PanelModelado();
        mainFrame.getContentPane().removeAll();
        mainFrame.add(panelModelado);
        mainFrame.validate();
        mainFrame.repaint();
    }

    public void funcionBoton2() {
        //System.out.println("Botón 2 presionado");
        PanelAnalisis panelAnalisis = new PanelAnalisis();
        mainFrame.getContentPane().removeAll();
        mainFrame.add(panelAnalisis);
        mainFrame.validate();
        mainFrame.repaint();
    }

    public void funcionBoton3() {
        System.out.println("Botón 3 presionado");
    }

    public void funcionBoton4() {
        System.out.println("Botón 4 presionado");
    }

    public void funcionBoton5() {
        System.out.println("Botón 5 presionado");
    }
    public Main() {
        // Configuración del JFrame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setLocation(width/2-500, height/2-300);
        String iconPath = "src/resources/uax-corto.png";
        ImageIcon icon = new ImageIcon(iconPath);
        setIconImage(icon.getImage());
        setTitle("Sistema de Gestión y Análisis de Datos Multidimensionales");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout del JFrame
        setLayout(new BorderLayout());

        // Panel izquierdo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(500, 600));
        panelIzquierdo.setBackground(Color.GRAY);

        // Imagen
        ImageIcon imagen = new ImageIcon("src/resources/logouax(1).png"); // Ajusta la ruta de la imagen según tu ubicación
        Image logouax = imagen.getImage();
        Image newimg = logouax.getScaledInstance(443, 95, java.awt.Image.SCALE_SMOOTH);//Para cambiarle el tamaño a la imagen
        imagen = new ImageIcon(newimg);//Agrega la imagen al JLabel
        JLabel labelImagen = new JLabel(imagen);
        labelImagen.setAlignmentX(Component.CENTER_ALIGNMENT);//Centra la imagen en el panel
        panelIzquierdo.add(Box.createVerticalGlue());//Espacio en la parte superior
        panelIzquierdo.add(labelImagen);//Agrega la imagen al panel
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));//Espacio entre la imagen y el texto debajo

        // Frase
        JLabel labelFrase = new JLabel("Bienvenido al Sistema de Gestión de Publicaciones");
        labelFrase.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelFrase.setFont(new Font("Arial", Font.BOLD, 20));
        panelIzquierdo.add(labelFrase);
        panelIzquierdo.add(Box.createVerticalGlue());

        // Panel derecho
        JPanel panelDerecho = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(500, 600));
        panelDerecho.setBackground(Color.LIGHT_GRAY);
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.add(Box.createVerticalGlue());

        // Textos para los botones
        String[] textosBotones = {
                "Gestión de Datos Dinámicos",
                "Análisis y Organización de Información",
                "Mapas y Asociación de Datos",
                "Interfaz Gráfica Avanzada",
                "Indexación y Visualización de Archivos"
        };

        // Crear y agregar los botones al panel derecho
        for (int i = 0; i < textosBotones.length; i++) {
            JButton button = new JButton(textosBotones[i]);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Establecer el tamaño máximo del botón
            button.setMaximumSize(new Dimension(350, button.getPreferredSize().height));
            final int index = i;
            button.addActionListener(e -> {
                switch (index) {
                    case 0:
                        funcionBoton1();
                        break;
                    case 1:
                        funcionBoton2();
                        break;
                    case 2:
                        funcionBoton3();
                        break;
                    case 3:
                        funcionBoton4();
                        break;
                    case 4:
                        funcionBoton5();
                        break;
                }
            });
            panelDerecho.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
            panelDerecho.add(button);
        }

        mainFrame = this;

        panelDerecho.add(Box.createVerticalGlue());

        // Panel inferior
        JPanel panelInferior = new JPanel();
        JLabel labelAutor = new JLabel("Derechos Reservados © 2024 - José Zorrilla");
        panelInferior.add(labelAutor);

        // Agregar componentes al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Centrar JFrame en la pantalla
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Crear y mostrar el JFrame
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
