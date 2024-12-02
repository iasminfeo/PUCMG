package provaThe2;

class Matriz{
    CelulaMat inicio;
}

class CelulaMat{
    CelulaMat prox, ant, sup, inf;
    Celula primeiro, ultimo;
    public CelulaMat(){
        prox=ant=sup=inf =null;
        primeiro = ultimo = new Celula();
       }
}
class Celula{
    int numero;
    Celula prox;
    public Celula(){ 
        this.numero=0;
        this.prox=null;
    }
    public Celula(int x){
        this.numero =x; prox=null;
    }
}

void remove(CelulaMat inicio){
    removeRec(inicio);
}

void removeRec(CelulaMat i){
    CelulaMat p, q;
    for(p=i; p!=null; p=p.prox){
        for(q=i; q!=null; q=q.inf){
            removelista(q.primeiro);
   }
}
}

void removelista(Celula j){
    removelistaRec(j);
}


void removelistaRec(Celula i){
    Celula k;
    for(k=i; k!=null; k=k.prox){
        if((k.numero)%2==1){
             remverElemento(k, k.numero);
        }
    }
}

void remverElemento(Celula k,int l){
        for(Celula tmp=k; tmp.numero!=l; tmp=tmp.prox){
            if(tmp.prox==k){
                tmp.prox=k.prox;
                k.prox=null;
                k=null;
            }

        }
}
public class tiraimpar {
    
}
