import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private int estadoActual;
    private int saldo;
    private int costoProductoSeleccionado;

    // Definir estados como constantes
    private static final int[] estadosProductos = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
    private static final String[] nombresProductos = {
            "Chicle (5ctvs)", "Chupete (15ctvs)", "Nucita (25ctvs)", "K-chitos (35ctvs)",
            "Leche Chocolatada (45ctvs)", "Botella de Agua (55ctvs)", "Gaseosa (65ctvs)",
            "Vive100 (75ctvs)", "Barra de Granola (85ctvs)", "Saviloe (95ctvs)"
    };
    private static final int[] costosProductos = {5, 15, 25, 35, 45, 55, 65, 75, 85, 95};

    public Main() {
        this.estadoActual = 0; // Estado inicial q0
        this.saldo = 0;
        this.costoProductoSeleccionado = 0;
    }

    public void procesarCompra(String monedas) {
        // Convertir la entrada de monedas a un arreglo
        List<Integer> listaMonedas = Arrays.stream(monedas.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Actualizar saldo según las monedas ingresadas
        for (int moneda : listaMonedas) {
            // Verificar si la moneda es válida
            if (moneda != 5 && moneda != 10 && moneda != 25 && moneda != 50 && moneda != 100) {
                System.out.println("Moneda no válida: " + moneda + ". Inténtalo de nuevo.");
                return;
            }

            this.saldo += moneda;
        }

        // Mostrar lista de productos
        System.out.println("\nLista de productos:");
        for (int i = 0; i < nombresProductos.length; i++) {
            System.out.println((i + 1) + ". " + nombresProductos[i] + " - " + costosProductos[i] + " ctvs");
        }

        // Pedir al usuario que seleccione un producto
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSelecciona un producto (1-" + nombresProductos.length + "): ");
        int opcionProducto = scanner.nextInt();

        // Verificar si la opción de producto es válida
        if (opcionProducto < 1 || opcionProducto > nombresProductos.length) {
            System.out.println("Opción de producto no válida. Inténtalo de nuevo.");
            return;
        }

        // Obtener el costo del producto seleccionado
        this.costoProductoSeleccionado = costosProductos[opcionProducto - 1];

        // Verificar si el usuario tiene suficiente saldo
        if (this.saldo >= this.costoProductoSeleccionado) {
            // Entregar producto y calcular vuelto si es necesario
            System.out.println("Compra exitosa. ¡Disfruta tu " + nombresProductos[opcionProducto - 1] + "!");
            int vuelto = this.saldo - this.costoProductoSeleccionado;
            if (vuelto > 0) {
                System.out.println("Vuelto: " + vuelto + " ctvs");
            }
        } else {
            System.out.println("Saldo insuficiente. Selecciona un producto más barato.");
        }

        // Reiniciar valores para la próxima transacción
        this.estadoActual = 0;
        this.saldo = 0;
        this.costoProductoSeleccionado = 0;
    }

    public static void main(String[] args) {
        Main maquina = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Máquina expendedora - Estado inicial: q0");

        while (true) {
            System.out.print("Ingresa las monedas separadas por comas (5,10,25,50,100) o 0 para salir: ");
            String monedas = scanner.next();

            if (monedas.equals("0")) {
                System.out.println("Saliendo del programa.");
                break;
            }

            maquina.procesarCompra(monedas);
        }
    }
}
