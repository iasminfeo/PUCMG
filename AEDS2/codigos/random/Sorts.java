import java.util.Scanner;

public class Sorts {

        void swap(int array[], int j, int i){
            int temp=array[j];
            array[j]=array[j+1];
            array[j+1]=temp;
        }

        void Bubble(int array[], int size){
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    if(array[j]>array[j+1]){
                        swap(array, j, j+1);
                    }
                }
            }
        }

        void Selection(int array[], int size){
            for(int i=0; i<size; i++){
                int lower=i;
                for(int j=1; j<size; j++){
                    if(array[j]<lower){
                        lower=array[j];
                    }
                }
                swap(array, i, lower);
            }
        }
        

        public static void insertionSort(int[] array) {
            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                int j = i - 1;
    
                // Move elements of array[0..i-1], that are greater than key,
                // to one position ahead of their current position
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
                array[j + 1] = key;
            }
        }

        void merge(int[] array, int left, int mid, int right) {
            int n1 = mid - left + 1;
            int n2 = right - mid;
    
            int[] L = new int[n1];
            int[] R = new int[n2];
    
            for (int i = 0; i < n1; i++) {
                L[i] = array[left + i];
            }
            for (int j = 0; j < n2; j++) {
                R[j] = array[mid + 1 + j];
            }
    
            int i = 0, j = 0;
            int k = left;
    
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    array[k] = L[i];
                    i++;
                } else {
                    array[k] = R[j];
                    j++;
                }
                k++;
            }
    
            while (i < n1) {
                array[k] = L[i];
                i++;
                k++;
            }
    
            while (j < n2) {
                array[k] = R[j];
                j++;
                k++;
            }
        }
    
        void mergeSort(int[] array, int left, int right) {
            if (left < right) {
                int mid = (left + right) / 2;
    
                mergeSort(array, left, mid);
                mergeSort(array, mid + 1, right);
                merge(array, left, mid, right);
            }
        }

     
    public static void main(String[] args){
        Scanner scanf = new Scanner(System.in);
        int[] array= new int[100];
        int size=0;
        while (scanf.hasNextInt()) {
            array[size++] = scanf.nextInt();
        }
        
        
        Sorts metodos = new Sorts();
        
        // metodos.Bubble(array, size);
        // metodos.Bubble(array, size);


        for(int i=0; i<=size; i++){
            System.out.println(array[i]);
        }
      





        scanf.close();
    }
}
