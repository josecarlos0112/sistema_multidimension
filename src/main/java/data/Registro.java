package data;

public class Registro implements Comparable<Registro> {
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

    @Override
    public int compareTo(Registro o) {
        return this.nombre.compareTo(o.nombre);
    }
}