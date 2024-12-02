
import java.util.Scanner;

public class Ciframentorec {
    public static void troca(int size, char[] array, int i) {
        if (i == size) {
            return;
        } else {
            if (array[i] <= 128) {
                array[i] += 3;
            }
            troca(size, array, i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        boolean fim = false;
        int size;

        while (!fim) {
            String cifra = scanf.nextLine();
            if (cifra.equals("FIM")) {
                fim = true;
            } else {
                char[] array = cifra.toCharArray();
                size = cifra.length();
                troca(size, array, 0);
                String nova = new String(array);
                System.out.println(nova);
            }
        }
        scanf.close();
    }
}
