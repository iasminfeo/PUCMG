package provaThe2;


class No {
    public int elemento;
    public No esq, dir;
    public int contador;

    public No(int x){
        this.elemento = x;
        this.contador = 1;
        this.esq = this.dir = null;
    }
}



class Arvore {
    private No raiz;

    public void inserir(int x) {
       raiz= inserir(raiz, x);
    }

    private No inserir(No i, int x){
        if(i == null){
            i = new No(x);
        }else if(x < i.elemento){
            i.esq = inserir(i.esq, x);
        }else if(x > i.elemento){
            i.dir = inserir(i.dir, x);
        }else{
            i.contador++;
        }


        return i;
    }
}


    
public class repetidos {
    
}
