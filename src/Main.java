import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine().trim();
            if (linea.equalsIgnoreCase("salir")) break;
            if (linea.isEmpty()) continue;

            String[] partes = linea.split(" ");
            String comando = partes[0].toLowerCase();

            if (comando.equals("ingresar")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: comando desconocido.");
                    continue;
                }
                String tipo = partes[1].toLowerCase();
                if (tipo.equals("perro") && partes.length == 7) {
                    System.out.println(hospital.ingresarPerro(partes[2], Double.parseDouble(partes[3]), Integer.parseInt(partes[4]), partes[5], partes[6]));
                } else if (tipo.equals("gato") && partes.length == 5) {
                    System.out.println(hospital.ingresarGato(partes[2], Double.parseDouble(partes[3]), Integer.parseInt(partes[4])));
                } else if (tipo.equals("ave") && partes.length == 6) {
                    System.out.println(hospital.ingresarAve(partes[2], Double.parseDouble(partes[3]), Integer.parseInt(partes[4]), Double.parseDouble(partes[5])));
                } else {
                    System.out.println("ERROR: datos invalidos");
                }
            } else if (comando.equals("vacunar") && partes.length == 2) {
                System.out.println(hospital.vacunar(partes[1]));
            } else if (comando.equals("asignar") && partes.length == 2) {
                System.out.println(hospital.asignar(partes[1]));
            } else if (comando.equals("medicar") && partes.length == 3) {
                System.out.println(hospital.medicar(partes[1], Double.parseDouble(partes[2])));
            } else if (comando.equals("alta") && partes.length == 2) {
                System.out.println(hospital.alta(partes[1]));
            } else if (comando.equals("reporte")) {
                System.out.println(hospital.generarReporte());
            } else {
                System.out.println("Comando desconocido: ERROR: comando desconocido.");
            }
        }
        scanner.close();
    }
}
