package Aulai.estudos;

import java.lang.reflect.Array;

class No{
    int elemento;
    No dir;
    No esq;
    public No(int x){
        this.elemento=x;
        this.dir=null;
        this.esq=null;
    }
}
class arvb{
    No raiz;
}
No inserir(int x, No i, Celula p1, CelulaDupla p2){
    i=raiz;
    while (( p1)!=null ||( p2)!=null ) {
        if(p1.prox!=null){
            p1=p1.prox;
           x=p1.elemento;
          i= inserirRec( x, i);
        }if(p2.prox!=null){
            p2=p2.prox;
           x=p2.elemento;
          i= inserirRec( x, i);
        }
        
    }
    return raiz;
}
No inserirRec(int x, No i) throws Exception{
   if(i==null){
     No tmp= new No(x);
   }else if(x>i.elemento){
    i.dir=inserirRec(x, i.dir);
   }else if(x<i.elemento){
    i.esq=inserirRec(x, i.esq);
   }else{
    throw new Exception("erro");
   }
   return i;

}

class Celula{
    int elemento;
    Celula prox;
}

class CelulaDupla{
    int elemento;
    CelulaDupla prox;
    CelulaDupla ant;
}

void NotoAB(Celula p1, CelulaDupla p2) {
    
}
