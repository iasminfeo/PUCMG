import java.*;

class No{
    int valor;
    No esq;
    No dir;

    public No(int valor){
        this.valor=valor;
        this.esq=this.dir=null;
    }
}

 class Arv{
    No raiz;
    public Arv(){
        this.raiz=null;
       }
    

       int calculaAlt(No i){
        if(i==null){
            return -1;
        }else{
            return 1+ Math.max(calculaAlt(i.esq), calculaAlt(i.dir));
        }
       }

       int calculaNo(No no){
        if(no==null){
            return 0;
        }else{
            return 1+ calculaNo(no.esq) + calculaNo(no.dir);
        }

       }
}

