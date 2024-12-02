package Aulai.estudos;

class No{
    int elemento;
    No dir;
    No esq;
    CelulaLista sentinela;
    
    public No(int x){
        this.elemento=x;
        this.dir=null;
        this.esq=null;
        this.sentinela=null;
    }
}


class CelulaLista{
    char primeiraLetra;
    CelulaLista prox;
    CelulaPilha topo;
}
class CelulaPilha{
    String nome;
    CelulaPilha prox;
}

CelulaPilha maior;

CelulaPilha caminhaTudo(No raiz){
 
   return maior=caminhaTudoRec(raiz);
}

CelulaPilha caminhaTudoRec(No i){
        CelulaPilha aux;

        if(i!=null){
          
        aux = caminhaListaRec(i.sentinela);
        caminhaTudoRec(i.esq);
        caminhaTudoRec(i.dir);
          
        }

        return aux;
}

CelulaPilha caminhaListaRec(CelulaLista i){
        CelulaPilha aux;
        if(i!=null){
            aux = caminhaPilha(i.topo ,maiorNUM);
            caminhaListaRec(i.prox);
        }
        return aux;
    }

    int maiorNUM;

 CelulaPilha caminhaPilha(CelulaPilha i, int maiorNUM){
    CelulaPilha j;
    int tamanho=0;
    maiorNUM=0;
    for(j=i; j!=null; j=j.prox){
        tamanho++;
    }
    if(tamanho>maiorNUM){
        maior=j;
        return j;
    }
    return maior;
    
}