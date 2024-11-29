package ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    @Override
    public String toString() {
        return "Cátedra: " + catedra + ", Nota: " + notaExamen;
    }
}

class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return notas.size() > 0 ? suma / notas.size() : 0;
    }

    @Override
    public String toString() {
        return "Alumno: " + nombreCompleto + ", Legajo: " + legajo + ", Promedio: " + calcularPromedio();
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}

public class CargaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre completo del alumno: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el legajo del alumno: ");
            long legajo = scanner.nextLong();
            scanner.nextLine();

            Alumno alumno = new Alumno(nombre, legajo);

            System.out.print("Ingrese la cantidad de notas del alumno: ");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la cátedra: ");
                String catedra = scanner.nextLine();
                System.out.print("Ingrese la nota: ");
                double nota = scanner.nextDouble();
                scanner.nextLine();
                alumno.agregarNota(new Nota(catedra, nota));
            }

            alumnos.add(alumno);
        }

        System.out.println("\nInformación cargada:");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
            for (Nota nota : alumno.getNotas()) {
                System.out.println(" - " + nota);
            }
        }
        scanner.close();
    }
}
