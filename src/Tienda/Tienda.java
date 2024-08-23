package Tienda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tienda {
    private String nombre;
    private int maxStock;
    private double saldoCaja;
    private List<Producto> productos;

    public Tienda(String nombre, int maxStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldoCaja = saldoCaja;
        this.productos = new ArrayList<>();
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public boolean agregarProducto(Producto producto, int unidades) {
        double costoTotal = producto.calcularPrecioFinal() * unidades;
        if (costoTotal > saldoCaja) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
            return false;
        }
        int totalStock = productos.stream().mapToInt(p -> p.getCantidadEnStock()).sum();
        if (totalStock + unidades > maxStock) {
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock");
            return false;
        }
        producto.setCantidadEnStock(producto.getCantidadEnStock() + unidades);
        productos.add(producto);
        saldoCaja -= costoTotal;  // Para actualizar el saldo después de la compra
        System.out.println("Saldo después de agregar el producto: " + saldoCaja);
        return true;
    }

    public void venderProductos(List<ProductoVenta> productosVenta) {
        double totalVenta = 0;
        StringBuilder detalleVenta = new StringBuilder();
        boolean hayStockInsuficiente = false;
        boolean hayProductoNoDisponible = false;

        // Limitar la venta a 3 productos diferentes, y hasta 12 unidades por producto
        if (productosVenta.size() > 3) {
            System.out.println("No se pueden vender más de 3 productos diferentes en una sola transacción.");
            return;
        }

        for (ProductoVenta productoVenta : productosVenta) {
            Producto producto = encontrarProductoPorId(productoVenta.getIdentificador());

            if (producto == null) {
                System.out.println("El producto " + productoVenta.getIdentificador() + " no se encuentra disponible");
                hayProductoNoDisponible = true;
                continue;
            }

            if (!producto.isDisponibleParaVenta()) {
                System.out.println("El producto " + productoVenta.getIdentificador() + " no se encuentra disponible");
                hayProductoNoDisponible = true;
                continue;
            }

            int unidades = productoVenta.getCantidad();
            if (unidades > 12) {
                System.out.println("No se pueden vender más de 12 unidades de un producto.");
                return;
            }

            if (unidades > producto.getCantidadEnStock()) {
                unidades = producto.getCantidadEnStock(); // Para asegurar que se venda sólo lo que hay en stock.
                hayStockInsuficiente = true;
            }

            double precioTotal = producto.calcularPrecioFinal() * unidades;
            totalVenta += precioTotal;

            // Esto es para imprimir los detalles de la venta
            detalleVenta.append(producto.getIdentificador())
                    .append(" ")
                    .append(producto.getDescripcion())
                    .append(" ")
                    .append(unidades)
                    .append(" x ")
                    .append(producto.calcularPrecioFinal())
                    .append("\n");

            producto.setCantidadEnStock(producto.getCantidadEnStock() - unidades);
            if (producto.getCantidadEnStock() == 0) {
                producto.setDisponibleParaVenta(false);
            }
        }

        if (hayStockInsuficiente) {
            detalleVenta.append("Hay productos con stock disponible menor al solicitado\n");
        }

        if (hayProductoNoDisponible) {
            detalleVenta.append("Algunos productos no se encuentran disponibles\n");
        }

        saldoCaja += totalVenta;  // Actualizamos el saldo después de la venta
        System.out.println(detalleVenta.toString());
        System.out.println("Total de Venta: " + totalVenta);
        System.out.println("Saldo después de la venta: " + saldoCaja);
    }

    private Producto encontrarProductoPorId(String id) {
        return productos.stream()
                .filter(p -> p.getIdentificador().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<String> obtenerComestiblesConMenorDescuento(double porcentajeDescuento) {
        return productos.stream()
                .filter(p -> p instanceof ProductoEnvasado && !((ProductoEnvasado) p).isImportado())
                .filter(p -> p.getPorcentajeGanancia() < porcentajeDescuento)
                .sorted((p1, p2) -> Double.compare(p1.calcularPrecioFinal(), p2.calcularPrecioFinal()))
                .map(p -> p.getDescripcion().toUpperCase())
                .collect(Collectors.toList());
    }
}