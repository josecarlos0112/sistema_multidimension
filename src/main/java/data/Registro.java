package data;

public class Registro {
    private String nombre;
    private double venta;

    public Registro(String nombre, double venta) {
        this.nombre = nombre;
        this.venta = venta;
    }

    public String getNombre() {
        return nombre;
    }

    public double getVenta() {
        return venta;
    }
}
