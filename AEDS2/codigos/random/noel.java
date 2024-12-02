import java.util.Scanner;

public class noel{
    public static void main(String[] args){
        Scanner scanf= new Scanner(System.in);
        int N= scanf.nextInt();
        scanf.nextLine();
        String[] feliz = new String[N];
        String[] linguas= new String[N];
        for(int i=0; i<N; i++){
            linguas[i]= scanf.nextLine();
            feliz[i]= scanf.nextLine();
        }

        int M= scanf.nextInt(); 
        scanf.nextLine();
        String[] nomes = new String[M];
        String[] linguas2= new String[M];
        for(int i=0; i<M; i++){
            nomes[i]= scanf.nextLine();
            linguas2[i]= scanf.nextLine();
        }

        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(linguas2[i].equals(linguas[j])){
                    System.out.println(nomes[i]+"\n"+feliz[j]+"\n");
                }
            }
        }

        scanf.close();
    }
}