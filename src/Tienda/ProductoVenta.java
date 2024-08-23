package Tienda;

public class ProductoVenta {
    private String identificador;
    private int cantidad;

    public ProductoVenta(String identificador, int cantidad) {
        this.identificador = identificador;
        this.cantidad = cantidad;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getCantidad() {
        return cantidad;
    }
}