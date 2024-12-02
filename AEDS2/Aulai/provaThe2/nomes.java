package provaThe2;

class ArvoreArvore{
    No raiz;
}

class No{
    char letra;
    No esq, dir;
    No2 raiz;
}

class No2{
    String palavra;
    No2 esq, dir;


int contarpalavras(char primeiro, char ultimo, No raiz){
    procuraNo(primeiro, raiz, ultimo);
    return 0;
}

void procuraNo(char primeiro, No raiz, char ultimo){
    procuraNoRec(primeiro, raiz, raiz.raiz, ultimo);
}

void procuraNoRec(char primeiro, No i, No2 raiz, char ultimo){
    if(i.letra==primeiro){
        procuraIguais(palavra, raiz, ultimo);
    }
    if(i!=null){
        procuraNoRec (primeiro, i.esq, i.raiz, ultimo);
        procuraNoRec (primeiro, i.dir, i.raiz, ultimo);
    }
}
void procuraIguais(String palavra, No2 raiz,char ultimo ){
    procuraIguaisRec(ultimo, raiz);
}
public int contador=0;
void procuraIguaisRec(char ultimo , No2 i){
    int tamanho = i.palavra.length();
    tamanho-=1;

    if(i.palavra.charAt(tamanho)==ultimo){
        contador++;
    }

    
}

}

public class nomes {
    
}
