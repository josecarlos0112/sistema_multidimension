package user_interface;

import javax.swing.*;

public class InterfazUsuario {
    private JFrame frame;

    public InterfazUsuario() {
        frame = new JFrame("Sistema de Gestión y Análisis de Datos Multidimensionales");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}