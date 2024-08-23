package Tienda;

public class ProductoEnvasado extends Producto {
    private String tipoEnvase;
    private boolean importado;
    private double calorias;

    public ProductoEnvasado(String identificador, String descripcion, int cantidadEnStock, double precioPorUnidad, double porcentajeGanancia, String tipoEnvase, boolean importado, double calorias) {
        super(identificador, descripcion, cantidadEnStock, precioPorUnidad, porcentajeGanancia);
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
        this.calorias = calorias;
    }

    public double getCalorias() {
        return calorias;
    }

    private static String validarIdentificadorEnvasado(String identificador) {
        if (!identificador.matches("AB\\d{3}")) {
            throw new IllegalArgumentException("El identificador de un producto envasado debe seguir el formato ABXXX, donde XXX son dígitos.");
        }
        return identificador;
    }

    @Override
    public double calcularPrecioFinal() {
        double precioFinal = precioPorUnidad * (1 + porcentajeGanancia / 100);
        if (importado) {
            precioFinal *= 1.12; // Impuesto del 12%
        }
        return precioFinal;
    }

    @Override
    public String obtenerTipo() {
        return "Envasado";
    }

    public boolean isImportado() {
        return importado;
    }
}
