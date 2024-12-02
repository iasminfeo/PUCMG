#include <stdio.h>
#include <stdlib.h>


typedef struct Celula {
int elemento;
struct Celula* prox;
} Celula;

Celula* inicio;
Celula* fim;
Celula* inicio2;

void Meiose(Celula* inicio){
for(Celula* i=inicio; i!=NULL; i=i->prox){
   Celula* tmp=(Celula*)malloc(sizeof(Celula));
   tmp->elemento= (i->elemento)/2;
   i->elemento= tmp->elemento;
   tmp->prox= i->prox;
   i->prox=tmp;
    i=i->prox;
}

}
