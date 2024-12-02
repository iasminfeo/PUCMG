package Aulão.estudos;

 class celula {
    public int elemento;
    public celula prox;
    public celula(){
        this(0);
    }
    public celula(int x){
     this.elemento=x;
     this.prox=null;
    }


public class fila{
    public celula inicio;
    public celula fim;


    public fila(int x){
        fim=new celula (x);
        inicio=fim;
    }

    public void inserir(int x){
        fim.prox=new celula(x);
        fim= fim.prox;
    }

    public int remover() throws Exception{
        if(inicio==fim){
            throw new Exception("erro");
        celula tmp= inicio;
        int elemento=inicio.elemento;
        inicio=inicio.prox;
        tmp.prox=null;
        tmp=null;
        return elemento;
    }
        }
    }
}

