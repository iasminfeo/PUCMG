import java.util.Scanner;
public class medalhas {
    public static void swapName(String paises[], int j){
        String temp= paises[j];
        paises[j]=paises[j+1];
        paises[j+1]=temp;
    }
    public static void swapMedals(int medal[], int j){
        int temp= medal[j];
        medal[j]=medal[j+1];
        medal[j+1]=temp;
    }
    public static void main(String[] args){
        Scanner scanf= new Scanner(System.in);
        int N= scanf.nextInt();
        String[] paises = new String[N];
        int[] golden= new int[N];
        int[] silver= new int[N];
        int[] bronze= new int[N];
        for(int i=0; i<N; i++){
            paises[i]=scanf.next();
            golden[i]=scanf.nextInt();
            silver[i]=scanf.nextInt();
            bronze[i]=scanf.nextInt();
            scanf.nextLine();
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N-1; j++){
                if(golden[j]<golden[j+1]){
                    swapName(paises, j);
                    swapMedals(golden, j);
                    swapMedals(bronze, j);
                    swapMedals(silver, j);
                }else if(golden[j]==golden[j+1]){
                    if (silver[j]<silver[j+1]) {
                    swapName(paises, j);
                    swapMedals(golden, j);
                    swapMedals(bronze, j);
                    swapMedals(silver, j);   
                    }else if(silver[j]==silver[j+1]){
                        if (bronze[j]<bronze[j+1]) {
                            swapName(paises, j);
                            swapMedals(golden, j);
                            swapMedals(bronze, j);
                            swapMedals(silver, j);   
                            }else if(bronze[j]==bronze[j+1]){
                                if(paises[j].charAt(0)>paises[j+1].charAt(0)){
                                    swapName(paises, j);
                            swapMedals(golden, j);
                            swapMedals(bronze, j);
                            swapMedals(silver, j); 
                                }
                            }
                    }
                }
            }
        }
        for(int i=0; i<N; i++){
           System.out.println( paises[i]+ " " + golden[i]+ " " + silver[i]+ " " + bronze[i]);
        }
    }
}
