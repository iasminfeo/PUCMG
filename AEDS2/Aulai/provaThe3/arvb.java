
class No{
    int elemento;
    No esq;
    No dir;

    public No(int elemento){
        this.elemento=elemento;
        this.esq=null;
        this.dir=null;
    }
    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Arvore{
    No raiz;
    public Arvore(){
        this.raiz=null;
    }

    public void caminharCentral(){
        caminharCentral(raiz);
    }
    
    public void caminharCentral(No i){
        if(i!=null){
        caminharCentral(i.esq);
        System.out.println(i.elemento);
        caminharCentral(i.dir);
    }
}
    public void inserir(int x){
        raiz=inserir(x, raiz);
    }
    public No inserir(int x, No i){
        if(i==null){
            i=new No(x);
        }else if(x>i.elemento){
            i.dir=inserir(x, i.dir);
        }else if(x<i.elemento){
            i.esq=inserir(x, i.esq);
        }
        return i;
    }
}