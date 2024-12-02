#include <stdio.h>
#include <stdlib.h>

typedef struct  celula
{
    int elemento;
    celula *prox;

}celula;
 
 celula *novacelula(int elemento){
    celula *nova = (celula *)malloc(sizeof (celula));
    nova->elemento= elemento;
    nova->prox= NULL;
    return nova;
 }

 typedef struct pilha{
    celula *topo;

    void start(){
        topo=NULL;
    }
    void inserir(pilha *pilhao, int x){
        celula *tmp= novacelula(x);
        tmp-> prox= pilhao->topo;
        pilhao->topo=tmp;
        tmp=NULL;
    
    }

    int remover(int x){
        celula *tmp=topo;
        int elemento= topo->elemento;
        topo=topo->prox;
        tmp->prox=NULL;
        free(tmp);
        tmp=NULL;
        return elemento;
    }
    void mostrar(){
        celula *i;
        for(*i=topo; i!=NULL; i=i->prox){
            printf("%d", i->elemento);
        }
    }




 }pilha;
