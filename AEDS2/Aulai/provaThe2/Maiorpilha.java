package provaThe2;

import Aulão.estudos.celula.lista;

 class Lista{
    static celulaLista inicio;
    celulaLista fim;
    
celulaLista maiorPilha(){
    celulaLista resp=  maiorpilhaRec(Lista.inicio, maiorPi);
    return resp;
}
celulapilha maiorPi;
int maior=0;
celulaLista maiorpilhaRec(celulaLista i, celulapilha maiorPi){
    int tamanho=0;
    celulapilha j=i.topo;
    for(j=i.topo; j!=null; j=j.prox){
        tamanho++;
    }
    if(tamanho>maior){
        maiorPi=j;
        maior=tamanho;
    }

    celulaLista resp=  maiorpilhaRec(i.prox, maiorPi );
    return resp;
}
}
class celulaLista{
    celulapilha topo;
    celulaLista prox;
}
class celulapilha{
    int elmento;
    celulapilha prox;
}

public class Maiorpilha {
    
}
