package Aulão.estudos;
import java.util.Scanner;

class celula{
    int elemento;
    celula prox;

    public celula(){
        this(0);
    }

    public celula(int x){
        this.elemento=x;
        this.prox=null;
    } 
}

public class pilha1{
    public celula topo;
    public pilha1(){
        topo=null;
    }
    
    public void inserir(int x){
        celula tmp= new celula(x);
        topo.prox=tmp;
        topo=tmp;
        tmp.prox=null;
        tmp=null;
    }

    public int remover(){
        celula tmp= topo;
        int elemento= topo.elemento;
        topo=topo.prox;
        tmp.prox=null;
        tmp=null;
        return elemento;
    }


    public int tamanhos(){
        celula i= topo;
        int tamanho=0;

        while (i!=null) {
            tamanho++;
            i=i.prox;
        }
        return tamanho;
    }

    public void mostrar(){
        celula i=topo;
        for(int j=0; j<tamanhos(); j++){
            System.out.println(i.elemento);
        }
    }

    public static void main(String[] args){
        Scanner scanf= new Scanner(System.in);
        pilha1 pilha = new pilha1();
        int x;
        String casos;
        while (true) {
            if(((casos=scanf.nextLine()).equals("FIM"))){
                break;
            }
            
        switch (casos) {
            case "R":
              pilha.remover();
                break;

            case "I":
            x=scanf.nextInt();
            scanf.nextLine(); 
             pilha.inserir(x);
                break;

            case "M":
            pilha.mostrar();
            default:
                break;
        }

    }
    scanf.close();
}

}