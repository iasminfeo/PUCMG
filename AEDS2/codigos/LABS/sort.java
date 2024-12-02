import java.util.Scanner;

public class sort {

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);

        while (true) {
            int N = scanf.nextInt();
            int M = scanf.nextInt();

            // Parar o processamento quando N e M forem zero
            if (N == 0 && M == 0) {
                System.out.println("0 0");
                break;
            }

            int[] numbers = new int[N];
            int[] m = new int[N];

            // Leitura dos números e cálculo do módulo ajustado
            for (int i = 0; i < N; i++) {
                numbers[i] = scanf.nextInt();
                m[i] = ((numbers[i] % M) + M) % M; // Ajustar módulo para números negativos
            }

            // Bubble sort adaptado para as regras especificadas
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < N - i - 1; j++) {
                    if (m[j] > m[j + 1]) {
                        swap(m, j, j + 1);
                        swap(numbers, j, j + 1);
                    } else if (m[j] == m[j + 1]) {
                        // Empate entre ímpar e par
                        if (numbers[j] % 2 == 0 && numbers[j + 1] % 2 != 0) {
                            swap(m, j, j + 1);
                            swap(numbers, j, j + 1);
                        }
                        // Empate entre ímpares
                        else if (numbers[j] % 2 != 0 && numbers[j + 1] % 2 != 0 && numbers[j] < numbers[j + 1]) {
                            swap(m, j, j + 1);
                            swap(numbers, j, j + 1);
                        }
                        // Empate entre pares
                        else if (numbers[j] % 2 == 0 && numbers[j + 1] % 2 == 0 && numbers[j] > numbers[j + 1]) {
                            swap(m, j, j + 1);
                            swap(numbers, j, j + 1);
                        }
                    }
                }
            }

            // Imprimir o resultado
            System.out.println(N + " " + M);
            for (int i = 0; i < N; i++) {
                System.out.println(numbers[i]);
            }
        }

        scanf.close();
    }
}
