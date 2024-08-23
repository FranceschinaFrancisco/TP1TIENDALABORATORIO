package Tienda;

import java.util.Arrays;
import java.util.List;

public class TestTienda {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Tienda Progreso", 200, 1000.0);

        // Creando los productos
        Producto cafe = new Bebida("AC116", "Café", 15, 15, 20, 0, true, 0);
        Producto aceite = new ProductoEnvasado("AB113", "Aceite", 20, 10, 14, "Botella", false, 100);
        Producto jabon = new ProductoLimpieza("AZ114", "Jabón", 10, 10, 12, ProductoLimpieza.TipoAplicacion.MULTIUSO);

        // Para agregar productos a la tienda
        tienda.agregarProducto(cafe, 10);
        tienda.agregarProducto(aceite, 5);
        tienda.agregarProducto(jabon, 8);

        // Realizar ventas comunes, café, aceite y jabón.
        List<ProductoVenta> venta = Arrays.asList(
                new ProductoVenta("AC116", 2),
                new ProductoVenta("AZ114", 2),
                new ProductoVenta("AB113", 3)
        );
        tienda.venderProductos(venta);

        //Realizar ventas de productos que no estan disponibles, un producto con identificador que no existe y otro que intenta comprar mas de 12 unidades
        List<ProductoVenta> ventaInvalida = Arrays.asList(
                new ProductoVenta("AB665", 2),
                new ProductoVenta("AZ114", 13)
        );
        tienda.venderProductos(ventaInvalida);

        // Consultar productos comestibles no importados con descuento menor a un porcentaje
        List<String> comestiblesConMenorDescuento = tienda.obtenerComestiblesConMenorDescuento(15.0);
        System.out.println("Comestibles no importados con descuento menor al 15%: " + comestiblesConMenorDescuento);

        System.out.println("Saldo final en caja: " + tienda.getSaldoCaja());
    }
}