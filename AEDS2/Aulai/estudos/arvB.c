#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


typedef struct{
    int elemento;
    No *esq;
    No *dir;
}No;

  No *raiz; 

    No* novoNo(int x){
        No *novo=(No*)malloc(sizeof(No));
        novo->elemento=x;
        novo->dir=  NULL;
        novo->esq=  NULL;
    }


void start(){
    raiz=NULL;
}

void inserir(int x){
    raiz=inserirRec(x, raiz);
}

    No* inserirRec(int x, No* i){
        if(i==NULL){
            i=novoNo(x);
        }else if(x<i->elemento){
            i->esq=inserirRec(x, i->esq);
        }else if(x>i->elemento){
            i->dir= inserirRec(x, i->dir);
        }else{
            errx(1, "erro");
        }
        return i;
    }


void pesquisar(int x){
    return pesquisarRec( x, raiz);
}

    bool pesquisarRec(int x, No* i){
        bool resp;
        if(i==NULL){
            resp=false;
        }else if(i==x->elemento){
            resp=true;
            }else if(x>i->elemento){
            resp= pesquisarRec(x, i->dir);
        }else {
            resp= pesquisarRec(x, i->esq);
        }
        return resp;
    }

void remover(int x){
    removerRec(x, &raiz);
}

    void removerRec(int x, No** i){
        if(*i==NULL){
            errx(1, "erro");
        }else if(x< (*i)->elemento){
            removerRec(x, &((*i)->dir));
        }else if(x<((*i)->elemento)){
            removerRec(x, &(*i)->esq);
        }else if((*i)->dir==NULL){
            No* del = *i;
            *i = (*i)->esq;
            free(del);
        } else if ((*i)->esq == NULL) {
            No* del = *i;
            *i = (*i)->dir;
            free(del);

        } else {
            maiorEsq(i, &((*i)->esq));
        }
    }
