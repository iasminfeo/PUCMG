import java.util.Scanner;

class Paises{
    String nome;
    int golden;
    int silver;
    int bronze;
    String genero;

     public Paises(String n, int g, int s, int b){
        nome=n;
        golden=g;
       silver=s;
       bronze=b;
     }
}

 public class medalsConst{
        public static void main(String[] args ){
            Scanner scanf = new Scanner(System.in);
            int N=scanf.nextInt();
            scanf.nextLine();
            Paises[] paisinho= new Paises[N]; 
            for(int i=0; i<N; i++){
                String n=scanf.next();
                int g= scanf.nextInt();
                int s= scanf.nextInt();
                int b= scanf.nextInt();
                scanf.nextLine();
                paisinho[i] = new Paises(n,g,s,b);
            }
        

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(paisinho[j].golden>paisinho[j+1].golden){
                    Paises temp= paisinho[j];
                    paisinho[j]= paisinho[j+1];
                    paisinho[j+1]= temp;
                }else if(paisinho[j].golden==paisinho[j+1].golden){
                    if(paisinho[j].silver>paisinho[j+1].silver){
                        Paises temp= paisinho[j];
                        paisinho[j]= paisinho[j+1];
                        paisinho[j+1]= temp;
                    }else if(paisinho[j].silver==paisinho[j+1].silver){
                        
                    }
                }
            }
        }
    }
        
    

 }


