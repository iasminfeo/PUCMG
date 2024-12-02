package provaThe2;

class Matriz{
    CelulaMatriz inicio;
    int linha, coluna;

    void CaminhaMat(CelulaMatriz inicio){
        int i=0, j=0;
        for(CelulaMatriz tmp=inicio; tmp!=null;tmp=tmp.dir){
            i++;
            for(CelulaMatriz tmp2=inicio; tmp2!=null;tmp2=tmp2.inf){
                j++;
                if(i==j){
                    ListaConca(i.inicio. j.inicio);
                }
            
            }
        }
    }

    void ListaConca(Celula i.inicio, Celula j.inicio){

    }
    CelulaDupla diagUnificada(CelulaMatriz i){
        diagUnificadaRec(i)
    }
    CelulaDupla diagUnificadaRec(CelulaMatriz i){
    }
    class CelulaMatriz{
    CelulaMatriz esq, dir, inf, sup;
    Celula inicio, fim;
    }
    class Celula{
    int elemento;
    Celula prox;
    }
    class CelulaDupla{
    int elemento;
    CelulaDupla prox, ant;
    }
}


public class matrizcabeça {

}7
