package Tienda;

public abstract class Producto {
    protected String identificador;
    protected String descripcion;
    protected int cantidadEnStock;
    protected double precioPorUnidad;
    protected double porcentajeGanancia;
    protected boolean disponibleParaVenta;

    // Constructor
    public Producto(String identificador, String descripcion, int cantidadEnStock, double precioPorUnidad, double porcentajeGanancia) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.cantidadEnStock = cantidadEnStock;
        this.precioPorUnidad = precioPorUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponibleParaVenta = true;
    }

    // Métodos abstractos
    public abstract double calcularPrecioFinal();
    public abstract String obtenerTipo();

    // Métodos getters y setters
    public String getIdentificador() {
        return identificador;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getCantidadEnStock() {
        return cantidadEnStock;
    }
    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }
    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }
    public boolean isDisponibleParaVenta() {
        return disponibleParaVenta;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }
    public void setDisponibleParaVenta(boolean disponibleParaVenta) {
        this.disponibleParaVenta = disponibleParaVenta;
    }
}