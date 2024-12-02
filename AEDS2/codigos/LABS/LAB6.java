import java.util.Random;

public class LAB6 {
    
    // Define o tamanho para o vetor
    static final int TAM = 7;
    static Random random = new Random();

    // Realiza a troca de posição de alguns elementos
    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // QuickSort com o primeiro elemento como pivô
    public static void quickSortFirstPivot(int[] array, int left, int right) {
        int i = left, j = right;
        int pivot = array[left];
        while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (left < j) quickSortFirstPivot(array, left, j);
        if (i < right) quickSortFirstPivot(array, i, right);
    }

    // QuickSort com o último elemento como pivô
    public static void quickSortLastPivot(int[] array, int left, int right) {
        int i = left, j = right;
        int pivot = array[right];
        while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (left < j) quickSortLastPivot(array, left, j);
        if (i < right) quickSortLastPivot(array, i, right);
    }

    // QuickSort com pivô aleatório
    public static void quickSortRandomPivot(int[] array, int left, int right) {
        int randomIndex = left + random.nextInt(right - left);
        swap(randomIndex, right, array); // Mover o pivô aleatório para o final
        quickSortLastPivot(array, left, right);
    }

    // QuickSort com a mediana de três elementos como pivô
    public static void quickSortMedianOfThree(int[] array, int left, int right) {
        int mid = left + (right - left) / 2;
        int pivot = median(array[left], array[mid], array[right]);
        if (pivot == array[left]) {
            swap(left, right, array);
        } else if (pivot == array[mid]) {
            swap(mid, right, array);
        }
        quickSortLastPivot(array, left, right);
    }

    // Método para encontrar a mediana de três valores
    private static int median(int a, int b, int c) {
        if ((a <= b && b <= c) || (c <= b && b <= a)) return b;
        if ((b <= a && a <= c) || (c <= a && a <= b)) return a;
        return c;
    }

    public static void main(String[] args) {
        int[] vetor = {20, 10, 30, 50, 45, 35, 5};

        System.out.println("Vetor original:");
        for (int i : vetor) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Testar QuickSort com o primeiro elemento como pivô
        int[] vetor1 = vetor.clone();
        quickSortFirstPivot(vetor1, 0, TAM - 1);
        System.out.println("Ordenado (Primeiro Pivô):");
        for (int i : vetor1) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Testar QuickSort com o último elemento como pivô
        int[] vetor2 = vetor.clone();
        quickSortLastPivot(vetor2, 0, TAM - 1);
        System.out.println("Ordenado (Último Pivô):");
        for (int i : vetor2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Testar QuickSort com pivô aleatório
        int[] vetor3 = vetor.clone();
        quickSortRandomPivot(vetor3, 0, TAM - 1);
        System.out.println("Ordenado (Pivô Aleatório):");
        for (int i : vetor3) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Testar QuickSort com a mediana de três como pivô
        int[] vetor4 = vetor.clone();
        quickSortMedianOfThree(vetor4, 0, TAM - 1);
        System.out.println("Ordenado (Mediana de Três):");
        for (int i : vetor4) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
