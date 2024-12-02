import java.util.Scanner;

public class alienigena {

    public static boolean compara(String alfabeto, String frase, int k, int n, int i) {
        // Se todos os caracteres da frase foram verificados
        if (i >= n) {
            return true;
        }

        // Tenta encontrar o caractere frase.charAt(i) no alfabeto
        boolean found = false;
        for (int j = 0; j < k; j++) {
            if (frase.charAt(i) == alfabeto.charAt(j)) {
                found = true;
                break;
            }
        }

        // Se não encontrou o caractere atual da frase no alfabeto, retorna falso
        if (!found) {
            return false;
        }

        // Verifica o próximo caractere da frase
        return compara(alfabeto, frase, k, n, i + 1);
    }

    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        int K = scanf.nextInt();
        int N = scanf.nextInt();
        scanf.nextLine();

        String alfabeto = scanf.nextLine();
        String frase = scanf.nextLine();

        if (compara(alfabeto, frase, K, N, 0)) {
            System.out.println("S");
        } else {
            System.out.println("N");
        }
        scanf.close();
    }
}
