import java.util.Scanner;

public class gnomos {

    static void swap(int[] idades, String[] nomes, int j) {
        int aux = idades[j];
        String auxi = nomes[j];
        idades[j] = idades[j + 1];
        idades[j + 1] = aux;
        nomes[j] = nomes[j + 1];
        nomes[j + 1] = auxi;
    }

    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        int N = scanf.nextInt();
        String[] nomes = new String[N];
        int[] idades = new int[N];

        for (int i = 0; i < N; i++) {
            nomes[i] = scanf.next();
            idades[i] = scanf.nextInt();
            scanf.nextLine();
        }

        // Bubble Sort com consideração para nomes
        int last_swap = N - 1;
        for (int i = 0; i < N - 1; i++) {
            int new_last_swap = 0;
            for (int j = 0; j < last_swap; j++) {
                if (idades[j] < idades[j + 1] || (idades[j] == idades[j + 1] && nomes[j].compareTo(nomes[j + 1]) > 0)) {
                    swap(idades, nomes, j);
                    new_last_swap = j;
                }
            }
            last_swap = new_last_swap;
            if (last_swap == 0) {
                break;
            }
        }

        // Impressão dos resultados
        for (int i = 0; i < N/3; i ++) {
            if (i + 2 < N) {  // Verifica se há pelo menos três elementos restantes
                System.out.println("Time " + (i+1));
                System.out.println(nomes[i] + " " + idades[i]);
                System.out.println(nomes[(N/3)+i] + " " + idades[(N/3)+i]);
                System.out.println(nomes[((N/3)*2)+i] + " " + idades[((N/3)*2)+i]+"\n");

            } 
        }

        scanf.close();
    }
}
