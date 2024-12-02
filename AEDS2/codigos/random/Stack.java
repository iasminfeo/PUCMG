import java.util.Scanner;

public class Stack{

    public static void minimo(int[] value, int j ) {

            int menor=value[0];
            for(int i=1; i<j; i++){
                if(value[i]<menor){
                    menor= value[i];
                } 
            }
            System.out.println(menor);
        }

    public static void main(String[] args){


        Scanner scanf = new Scanner(System.in);
        int N= scanf.nextInt();
        scanf.nextLine();
        int j=0;
        int[] value= new int[N];
        String word;
        int cont=0;

        for(int i=0; i<N; i++){
            word=scanf.next();
            if(word.equals("PUSH")){
                value[j]=scanf.nextInt();
                scanf.nextLine();
                j++;
            }else if(word.equals("POP") && j>0){
                j--;
            }else if(word.equals("MIN")){
                minimo(value, j);
            }
        }

        scanf.close();
    }
}