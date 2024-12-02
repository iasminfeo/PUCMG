package Aulai.estudos;

class No{
   int elemento;
   No dir;
   No esq;
   public No(int elemento){
      this.elemento=elemento;
      this.dir=null;
      this.esq=null;
   }
   public No(int elemento, No esq, No dir){
      this.elemento=elemento;
      this.dir=dir;
      this.esq=esq;
   }
}
class Btree{
   No raiz;
   public Btree(){
      this.raiz=null;
   }
   void inserir(int x) throws Exception{
      raiz =inserir(x, raiz);
   }
   No inserir(int x, No i) throws Exception{
      if(i==null){
         i=new No(x);
      }else if(x<i.elemento){
         i.esq= inserir(x, i.esq);
      }else if(x >i.elemento){
         i.dir= inserir(x, i.dir);
      } else{
         throw new Exception("Erro");
      }
      return i;
   }


   void inserirPai(int x) throws Exception{
      if(raiz==null){
         raiz=new No(x);
      }else if(x<raiz.elemento){
         inserirPai(x, raiz.esq, raiz);
      }else if(x >raiz.elemento){
         inserirPai(x, raiz.dir, raiz);
      }else{
         throw new Exception("erro");
      }
   }
   void inserirPai(int x, No i, No pai) throws Exception{
      if(i==null){
         if(x<pai.elemento){
            pai.esq= new No(x);
         }else{
            pai.dir= new No(x);
         }
      }else if(x< i.elemento){
         inserirPai(x, i.esq, i);
      }else if(x> i.elemento){
         inserirPai(x, i.dir, i);
      }else{
         throw new Exception("erro");
      }
   }

   boolean pesquisar(int x){
      return pesquisar(x, raiz);
   }

   boolean pesquisar(int x, No i){
      boolean resp;
      if(i==null){
         resp=false;
      }else if(x==i.elemento){
         resp = true;
      }else if(x<i.elemento){
         resp= pesquisar(x, i.esq);
      }else {
         resp= pesquisar(x, i.dir);
      }
      return resp;
   }

   void caminharCentral (No i){
      if(i!=null){
         caminharCentral(i.esq);
         System.out.println(i.elemento+" ");
         caminharCentral(i.dir);
      }
   }


   void caminharPos(No i){
      if(i!=null){
         caminharPos(i.esq);
         caminharPos(i.dir);
         System.out.println(i.elemento+" ");
      }
   }

   void caminharPre(No i){
      if(i!=null){
         System.out.println(i.elemento+" ");
         caminharPre(i.esq);
         caminharPre(i.dir);
      }

      //  int getAltura(No i, int getAltura){
      //    if(i==null){
      //       altura--;
      //    }else{
      //       int alturaEsq = getAltura(i.esq, altura +1);
      //       int alturaDir = getAltura(i.dir, altura+1);
      //        altura= (alturaDir > alturaEsq) ? alturaDir :alturaEsq;
      //    }
      //    return altura;
      // }

       int soma(){
         return soma(raiz);
      }
      public int soma(No i){
      int resp=0;
      if(i!=null){
         resp = i.elemento+ soma(i.esq) +soma(i.dir);
      }
      return resp;
      } 



      void remover(int x) throws Exception{
         raiz = remover(x, raiz);
      }
      No remover(int x, No i) throws Exception{
         if(i==null){
            throw new Exception("ERRO");
         }else if (x< i.elemento){
            i.esq=remover(x, i.esq);
         }else if (x> i.elemento){
            i.dir=remover(x, i.dir);
         }else if(i.dir==null){
            i=i.esq;
         }else if(i.esq==null){
           i= i.dir;
         } else{
            i.esq= maiorEsq(i, i.esq);
         }
         return i;
      }

      No maiorEsq(No i, No j){
         if(j.dir==null){
            i.elemento=j.elemento;
             j=j.esq;
            }else{
               j.dir=maiorEsq(i, j.dir);
            }
            return j;
         }
      }


      public class arvBina{
      public static void main(String[] args){
         int altura=0;
         getAltura(i, altura);
      }
      }



   }




}
