#include <stdio.h>
#include <stdlib.h>

typedef struct celula{
    int elemento; 
    celula *prox;
}celula;

celula *novacelula(int x){
    celula *nova =(celula *)malloc(sizeof(celula));
    nova->elemento= x;
    nova->prox=NULL;
    return nova;
}
typedef struct listasimples
{
    celula *inicio, *fim;
    }listasimples;

   
    void start(listasimples *f){
        f->inicio =novacelula(-1);
        f->fim=f->inicio;
    }

    void inseririnicio(listasimples *f, int x){
        celula *tmp= novacelula(x);
        tmp->prox= f->inicio;
        f->inicio= tmp;
        tmp->prox= NULL;
        tmp=NULL;
        free(tmp);
    }

    void inserirFim(listasimples *f, int x){
         f->fim->prox= novacelula(x);
        f->fim= f->fim->prox;
    }

    int removerInicio(listasimples *f){
        celula *tmp= f->inicio;
        int elemento =f->inicio->elemento;
        f->inicio=f->inicio->prox;
        tmp->prox=NULL;
        tmp= NULL;
        return elemento;
        free(tmp);
    }

    void inserirInicio(listasimples *f, int x){
        celula *tmp= novacelula(x);
        tmp->prox=f->inicio->prox;
        f->inicio->prox=tmp;
        if(f->inicio==f->fim){
            f->fim=tmp;
        }
        tmp=NULL;
        free(tmp);
    }

    int removerFim(listasimples *f){
        celula *i;
        for(i=f->inicio; i->prox!=f->fim; i=i->prox){
            int elemento=f->fim->elemento;
            f->fim=i;
            i=f->fim->prox=NULL;
            // free(fim->prox);
            return elemento;
        }

    }

    int tamanho(listasimples *f){
        celula *i;
        int tamanho=0;
        for(i=f->inicio; i!=NULL; i=i->prox){
            tamanho++;
        }
        return tamanho;
    }

    void inserir(listasimples *f, int x, int pos){
        int tamanhos= tamanho(f);
        if(pos<0 || pos>tamanhos){
            errx("erro");
        }else if(pos==0){
            inserirFim(f, x);
        }else if(pos==tamanhos){
            inserirInicio(f, x);
    }else{
        celula *i;
        for(int j=0; j<pos; j++, i=i->prox);
        celula *tmp= novacelula(x);
        tmp->prox=i->prox;
        i->prox=tmp;
        tmp=i=NULL;
    }
    }
    

    int remover(listasimples *f, int pos){
          int elemento, tamanhos=tamanho(f);
        if(pos<0 || pos>tamanhos){
            errx(1, "erro");
        }else if(pos==tamanhos){
            removerFim(f);
        }else if(pos==0){
            removerInicio(f);
}else{
        celula *i;
        for(int j=0; j<pos; j++, i=i->prox);
        celula *tmp= i->prox;
        i->prox=tmp->prox;
        elemento= tmp->elemento;
        tmp->prox=NULL;
        tmp=i=NULL;
        free(tmp);
    }
     return elemento;
    }



