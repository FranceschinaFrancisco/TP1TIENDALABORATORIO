package Tienda;

public class ProductoLimpieza extends Producto {
    public enum TipoAplicacion { COCINA, BANIO, ROPA, MULTIUSO }
    private TipoAplicacion tipoAplicacion;

    public ProductoLimpieza(String identificador, String descripcion, int cantidadEnStock, double precioPorUnidad, double porcentajeGanancia, TipoAplicacion tipoAplicacion) {
        super(validarIdentificadorLimpieza(identificador), descripcion, cantidadEnStock, precioPorUnidad, porcentajeGanancia);
        this.tipoAplicacion = tipoAplicacion;
    }

    private static String validarIdentificadorLimpieza(String identificador) {
        if (!identificador.matches("AZ\\d{3}")) {
            throw new IllegalArgumentException("El identificador de un producto de limpieza debe seguir el formato AZXXX, donde XXX son dígitos.");
        }
        return identificador;
    }

    @Override
    public double calcularPrecioFinal() {
        // Ajustar porcentaje de ganancia según el tipo de aplicación
        double porcentajeGananciaAjustado = porcentajeGanancia;

        if (tipoAplicacion != TipoAplicacion.COCINA && tipoAplicacion != TipoAplicacion.MULTIUSO) {
            // Aplicar restricciones de porcentaje de ganancia
            if (porcentajeGananciaAjustado < 10) {
                porcentajeGananciaAjustado = 10;
            } else if (porcentajeGananciaAjustado > 25) {
                porcentajeGananciaAjustado = 25;
            }
        }

        // Calcular el precio final con el porcentaje de ganancia ajustado
        return precioPorUnidad * (1 + porcentajeGananciaAjustado / 100);
    }

    @Override
    public String obtenerTipo() {
        return "Limpieza";
    }
}