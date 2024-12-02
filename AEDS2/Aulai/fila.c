#include <stdio.h>
#include <stdlib.h>

typedef struct celula
{
   int elemento;
   celula *prox;
}celula;

    celula *novacelula(int x){
       celula *nova = (celula*)malloc(sizeof (celula));
       nova->elemento=x;
       nova->prox=NULL;
       return nova;
    }


typedef struct fila
{
   celula *inicio;
   celula *fim;
}fila;
   
    void start(fila *f){
        f->inicio =novacelula(-1);
        f->fim=f->inicio;
    }

    void inserir(fila *f, int x){
        f->fim->prox= novacelula(x);
        f->fim=f->fim->prox;
    }
    int remover(fila *f){
        celula *tmp= f->inicio;
        int elemento= f->inicio->elemento;
        f->inicio= f->inicio->prox;
        tmp->prox=NULL;
        tmp=NULL;
        free(tmp);
        return elemento;
    }
    void mostrar(fila *f){
        celula *i;
        for(i=f->inicio; i!=NULL; i=i->prox){
            printf("%d", i->elemento );

        }
    }


