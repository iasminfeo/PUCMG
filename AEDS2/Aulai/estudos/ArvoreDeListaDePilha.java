package Aulai.estudos;

import TP01.bool;

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
    
void buscaAno(int x, No raiz){
     buscaAnoRec(x, raiz);
}
public String nomes="ana";

boolean buscaAnoRec(int x, No i) throws Exception{
    if(i==null){
        throw new Exception("nao existe o ano");
    }else if(i.elemento==x){
        return buscaInicial(nome, i);
    }else if(x>i.elemento){
        buscaAnoRec(x, i.dir);
    }else if(x<i.elemento){
        buscaAnoRec(x, i.esq);
    }
}
 
    boolean buscaInicial(String nome, No i){
        return buscaInicialRec(nome, i.sentinela.prox);
    }

boolean buscaInicialRec(String nome, CelulaLista i) throws Exception{    
    if ((nome.charAt(0))==(i.primeiraLetra)) {
        return buscaNome(nome, i.topo);
    }else if((nome.charAt(0))>(i.primeiraLetra)){
        buscaInicialRec(nome, i.prox);
    }else{
        throw new Exception("erro");
    }
    return false;
}

boolean buscaNome(String nome, CelulaPilha i){
    buscaNomeRec(nome, i.prox);
}

boolean buscaNomeRec(String nome, CelulaPilha i){
    if(i.nome.equals(nome)){
        return true;
    }else{
        buscaNomeRec(nome, i.prox);
    }
    return false;
}




