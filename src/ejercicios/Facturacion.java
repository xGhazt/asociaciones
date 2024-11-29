package ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuentoItem;
    private double subtotal;

    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario, double descuentoItem, double subtotal) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoItem = descuentoItem;
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return codigoArticulo + "\t" + nombreArticulo + "\t" + cantidad + "\t" + precioUnitario + "\t" + descuentoItem + "\t" + subtotal;
    }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private double totalCalculadoFactura;
    private String cliente;
    private ArrayList<DetalleFactura> detallesFactura;

    public Factura() {
        detallesFactura = new ArrayList<>();
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detallesFactura.add(detalle);
    }

    public void calcularMontoTotal() {
        totalCalculadoFactura = 0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubtotal();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fecha: ").append(fechaFactura).append("\n");
        sb.append("Numero: ").append(numeroFactura).append("\n");
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("Código\tNombre\tCantidad\tPrecio Unitario\tDescuento\tSubtotal\n");
        for (DetalleFactura detalle : detallesFactura) {
            sb.append(detalle).append("\n");
        }
        sb.append("Total: ").append(totalCalculadoFactura).append("\n");
        return sb.toString();
    }
}

public class Facturacion {
    private static final String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        // Punto b
        System.out.print("Ingrese la fecha de la factura: ");
        factura.setFechaFactura(scanner.nextLine());
        long numeroFactura;
        do {
            System.out.print("Ingrese el número de la factura (entero mayor a 0): ");
            numeroFactura = scanner.nextLong();
        } while (numeroFactura <= 0);
        factura.setNumeroFactura(numeroFactura);
        scanner.nextLine();

        String cliente;
        do {
            System.out.print("Ingrese el nombre del cliente: ");
            cliente = scanner.nextLine();
        } while (cliente.isEmpty());
        factura.setCliente(cliente);

        // Punto c
        boolean continuar = true;
        while (continuar) {
            System.out.print("Ingrese el código del artículo a facturar: ");
            String codigo = scanner.nextLine();
            boolean encontrado = false;

            for (String[] articulo : articulos) {
                if (articulo[0].equals(codigo)) {
                    encontrado = true;
                    System.out.print("Ingrese la cantidad: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    double precioUnitario = Double.parseDouble(articulo[2]);
                    double descuento = cantidad > 5 ? precioUnitario * 0.1 : 0;
                    double subtotal = (precioUnitario - descuento) * cantidad;

                    DetalleFactura detalle = new DetalleFactura(codigo, articulo[1], cantidad, precioUnitario, descuento, subtotal);
                    factura.agregarDetalle(detalle);
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
            }

            System.out.print("¿Desea continuar agregando artículos? (true/false): ");
            continuar = scanner.nextBoolean();
            scanner.nextLine();
        }

        // Punto d
        factura.calcularMontoTotal();

        // Punto e
        System.out.println("\nFactura generada:");
        System.out.println(factura);

        scanner.close();
    }
}
