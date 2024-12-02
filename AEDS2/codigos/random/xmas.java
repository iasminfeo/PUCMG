import java.util.Scanner;

public class xmas{
    public static void swap(String[] names, int N, int j){
        String temp=names[j];
        names[j]=names[j+1];
        names[j+1]=temp;

    }
    public static void display(String[] names, int N, int contador){
        for(int i=0; i<N; i++){
            System.out.println(names[i]);
        }
        System.out.println("Se comportaram: "+contador+" | Nao se comportaram: "+ (N-contador));
    }
    public static void main(String[] args){
        Scanner scanf = new Scanner(System.in);
        int N= scanf.nextInt();
        scanf.nextLine();
        String[] names = new String[N];
        int contador=0;
        String sinal;
        for(int i=0; i<N; i++){
            sinal=scanf.next();
            if(sinal.charAt(0)=='+'){
                contador++;

            }
            names[i]=scanf.nextLine().trim();
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N-1; j++){
                if(names[j].charAt(0)>names[j+1].charAt(0)){
                    swap(names, N, j);
                }else if(names[j].charAt(0)==names[j+1].charAt(0)){
                    if(names[j].charAt(1)>names[j+1].charAt(1)){
                        swap(names, N, j);
                    }
                }
            }
        }
        display(names, N, contador);
        




        scanf.close();
    }
}