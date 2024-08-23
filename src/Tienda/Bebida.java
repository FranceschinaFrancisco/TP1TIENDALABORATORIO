package Tienda;

public class Bebida extends Producto {
    private double graduacionAlcoholica;
    private boolean importado;
    private double calorias;

    public Bebida(String identificador, String descripcion, int cantidadEnStock, double precioPorUnidad, double porcentajeGanancia, double graduacionAlcoholica, boolean importado, double calorias) {
        super(identificador, descripcion, cantidadEnStock, precioPorUnidad, porcentajeGanancia);
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.importado = importado;
        this.calorias = calorias;
    }

    private static String validarIdentificadorBebida(String identificador) {
        if (!identificador.matches("AC\\d{3}")) {
            throw new IllegalArgumentException("El identificador de una bebida debe seguir el formato ACXXX, donde XXX son dígitos.");
        }
        return identificador;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    private void ajustarCalorias() {
        if (graduacionAlcoholica >= 0 && graduacionAlcoholica <= 2) {
            setCalorias(getCalorias());
        } else if (graduacionAlcoholica > 2 && graduacionAlcoholica <= 4.5) {
            setCalorias(getCalorias() * 1.25);
        } else if (graduacionAlcoholica > 4.5) {
            setCalorias(getCalorias() * 1.5);
        }
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
    public String obtenerTipo() { return "Bebida"; }
}