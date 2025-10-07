/**
 * Main.java
 *
 * Programa de ejemplo para demostrar la documentación con Javadoc.
 * Contiene varias operaciones matemáticas.
 */
public class Main {

    /**
     * Método principal para ejecutar el programa.
     * 
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        System.out.println("=== OPERACIONES MATEMÁTICAS ===\n");

        int num1 = 5;
        int num2 = 3;

        System.out.println("Suma: " + sumar(num1, num2));
        System.out.println("Resta: " + restar(num1, num2));
        System.out.println("Promedio: " + promedio(new double[]{2.5, 3.0, 4.5}));
        System.out.println("¿" + num1 + " es par?: " + esPar(num1));
        System.out.println("Factorial de " + num2 + ": " + factorial(num2));
    }

    /**
     * Suma dos números enteros.
     *
     * @param num1 Primer número.
     * @param num2 Segundo número.
     * @return La suma de ambos números.
     */
    public static int sumar(int num1, int num2) {
        return num1 + num2;
    }

    /**
     * Resta dos números enteros.
     *
     * @param num1 Primer número.
     * @param num2 Segundo número.
     * @return Resultado de la resta (num1 - num2).
     */
    public static int restar(int num1, int num2) {
        return num1 - num2;
    }

    /**
     * Calcula el promedio de una lista de números.
     *
     * @param numeros Array de números (double).
     * @return El promedio de los valores del array.
     * @throws IllegalArgumentException si el array está vacío.
     */
    public static double promedio(double[] numeros) {
        if (numeros.length == 0) {
            throw new IllegalArgumentException("ERROR -> El array no puede estar vacío.");
        }

        double suma = 0;
        for (double num : numeros) {
            suma += num;
        }
        return suma / numeros.length;
    }

    /**
     * Determina si un número es par.
     *
     * @param num Número entero a evaluar.
     * @return true si el número es par, false si el número es impar.
     */
    public static boolean esPar(int num) {
        return num % 2 == 0;
    }

    /**
     * Calcula el factorial de un número entero.
     *
     * @param num Número entero mayor o igual a 0.
     * @return El factorial de num.
     * @throws IllegalArgumentException si el número es negativo.
     */
    public static int factorial(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("ERROR -> El número no debe ser negativo.");
        }

        int resultado = 1;
        for (int i = 2; i <= num; i++) {
            resultado *= i;
        }
        return resultado;
    }
}
