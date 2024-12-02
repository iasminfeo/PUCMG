package Aulão;

 class celulaDupla {
    public int elemento;
    public celulaDupla prox, ant;

    public celulaDupla(){
        this(0);
    }
    public celulaDupla(int x){
        this.elemento=x;
        this.prox=this.ant=null;
    }


    public class listadupla{
        public celulaDupla inicio, fim;
        public listadupla(){
            inicio= new celulaDupla();
            fim=inicio;
        }

        public void inserirInicio(int x){
            celulaDupla tmp= new celulaDupla(x);
            tmp.ant=inicio;
            tmp.prox= inicio.prox;
            if(inicio==fim){
                fim=tmp;
            }else{
                tmp.prox.ant=tmp;
            }
            tmp=null;
        }

        public void inserirFim(int x){
            fim.prox= new celulaDupla(x);
            fim.prox.ant=fim;
            fim= fim.prox;   
        }

        public int removerInicio(){
            celulaDupla tmp=inicio;
            elemento=inicio.elemento;
            inicio=inicio.prox;
            tmp.prox=inicio.ant=null;
            tmp=null;
            return elemento;
        }

        public int removerFim(){
            celulaDupla tmp=fim;
            int elemento= fim.elemento;
            fim=fim.ant;
            fim.ant.prox=null;
            tmp.prox=null;
            tmp=null;
            return elemento; 
            
        }

         public int tamanho(){
            int tamanho=0;
            for(celulaDupla i=inicio; i.prox!=null; i=i.prox){
                 tamanho++;
            }
            return tamanho;
        }

        public void inserir(int x, int pos) throws Exception{
            int tamanho= tamanho();
            if(pos<0 || pos>=tamanho){
                throw new Exception("erro");
            }else if(pos==0){
                inserirInicio(x);
            }else if(pos==tamanho){
                inserirFim(x);
            }else{
                celulaDupla i=inicio;
                for(int j=0; j<pos; j++, i=i.prox);

                celulaDupla tmp= new celulaDupla(x);
                tmp.prox=i.prox;
                tmp.ant=i;
                tmp.ant.prox=tmp.prox.ant=tmp;
                tmp=i=null;
            }
        }

        public int remover(int pos) throws Exception{
            int tamanho=tamanho();
            if(pos<0 || pos>=tamanho){
                throw new Exception("erro");
            }else if(pos==tamanho){
                removerFim();
            }else if(pos==0){
                removerInicio();
            }else{
                celulaDupla i=inicio;
                for(int j=0; j<pos; j++, i=i.prox);
                elemento= i.elemento;
                i.ant.prox=i.prox;
                i.prox.ant=i.ant;
                i.prox=i.ant=null;
                i=null;
            }
            return elemento;

        }








    }

}
