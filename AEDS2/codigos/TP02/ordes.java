import java.util.Scanner;
 
public class ordes{

    public static void swap( int[] array, int j, int i){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }


    public static void Bubble( int[] array){


        for(int i=0; i<10; i++){
            for(int j=0; j<9; j++){
                if(array[j]>array[j+1]){
                    swap(array,j, j+1);
                }
            }
           }
    }


    public static void Selection(int[] array){
        for(int i=0; i<10-1; i++){
            int menor=i;
            for(int j=i+1; j<10; j++){
                if(array[j]<array[menor]){
                    menor=j;
                }
            }
            int temp=array[menor];
                array[menor]=array[i];
                array[i]=temp;
        }
    }

    public static void Insertion(int[] array){
    
        for (int i = 1; i < 10; i++) { // Começamos com o segundo elemento
            int key = array[i]; // O elemento a ser inserido
            int j = i - 1;

            // Move os elementos que são maiores que 'key' para uma posição à frente
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key; // Insere o elemento na posição correta
        }
    }

    private static void quickSort(int esq, int dir, int[] array) {
        int i = esq, j = dir;
        int pivo = array[(dir + esq) / 2];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(array, j, i);
                i++;
                j--;
            }
        }
        if (esq < j) quickSort(esq, j, array);
        if (i < dir) quickSort(i, dir, array);
    }

    public static void main(String[] args){

   int array[]= new int [10];
   Scanner scanf =  new Scanner(System.in);
   for(int i=0; i<10; i++){
   array[i]=scanf.nextInt();
   }
//    Bubble(array);
//    Selection(array);
//    Insertion(array);
    quickSort(0, 9, array);

   
  
    for(int j=0; j<10; j++){
        System.out.println(array[j]);
    }


   scanf.close();
    }
}