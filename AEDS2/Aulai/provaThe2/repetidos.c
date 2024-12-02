#include <stdio.h>
#include <stdlib.h>


typedef struct{
    int numero;
    Celula* prox;
}Celula;

typedef struct{
    int numero;
    CelulaMatriz *dir, *esq, *sup, *inf;
}CelulaMatriz;


typedef struct 
{
   int numero;
   No *esq, *dir;
}No;

Celula * EncontrarRepetidos(No* raiz, CelulaMatriz* inicio){
    return EncontrarRepetidosRec( raiz, inicio, nova);
}
Celula* nova;

Celula* EncontrarRepetidosRec(No* raiz, CelulaMatriz* inicio,CelulaMatriz* nova ){
   inserirMatr(raiz);
}


  Celula* inserirMatrRec(CelulaMatriz* inicio){

        Celula* cabeca;
        for(CelulaMatriz* i=inicio; i!=NULL; i=i->inf){
            for(CelulaMatriz* j= i; j!=NULL; j=j->dir){
            cabeca = inserirNova(j->numero );
        }
    }
    
}    

Celula* inserirArv(No* raiz){
    return inserirArvRec( raiz);


}
 Celula* inserirArvRec(No* i){
    if(i != NULL){
        inserirArvRec(i->esq);
        inserirNova(i->numero );
        inserirArvRec(i->dir);
    }
}
        
Celula* cabeca;

Celula* inserirNova(int x ){
    Celula* tmp=(Celula*)malloc(sizeof(Celula));
    tmp->prox = cabeca->prox;
    cabeca->prox=tmp;
    return cabeca;
}

Celula* ordenas(){



}