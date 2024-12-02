package Aulão.estudos;

 class celula {
    public int elemento;
    public celula prox;
    public celula(){
        this(0);
    }
    public celula(int x){
        this.elemento=x;
        this.prox=null;
    }
    
    public class lista{
        public celula inicio;
        public celula fim;
        public lista(){
            inicio=new celula();
            fim=inicio;
        }



        public void inserirInicio(int x){
            celula tmp=inicio;
            inicio.prox=inicio;
            inicio.elemento=x;
            tmp.prox=null;
            tmp=null;

        }

        public void inserirFim(int x){
            fim.prox=new celula(x);
            fim= fim.prox;
         }


         public int removerInicio(){
            celula tmp=inicio;
            int elemento=tmp.elemento;
            inicio=inicio.prox;
            tmp.prox=null;
            tmp=null;
            return elemento;
         }
         


        public int removerFim(){
           for(celula i=inicio; i.prox!=fim; i=i.prox){
            int elemento= fim.elemento;
            fim=i;
            i=fim.prox= null;
            return elemento;
           }
        }
        public int tamanho(){
            int tamanho=0;
            for(celula i=inicio; i.prox!=null; i=i.prox){
                 tamanho++;
            }
            return tamanho;
        }

        public void inserir(int x, int pos) throws Exception{
            int tamanho= tamanho();
            if(pos>tamanho || pos<0){
                new Exception("erro");
            }else if(pos==0){
                inserirInicio(x);
            }else if(pos==tamanho){
                inserirFim(x);
            }else{
                celula i=inicio;
                for(int j=0; j<pos; j++, i=i.prox);
                    celula tmp= new celula(x);
                    tmp.prox=i.prox;
                    i.prox=tmp;
                    tmp=i=null;
            }
        }
        
        public int remover(int x, int pos) throws Exception{
            int tamanho= tamanho();
            if(pos<0 || pos>tamanho){
                throw new Exception("erro");
            }else if(pos==0){
                removerInicio();
            }else if(pos==tamanho){
                removerFim();
            }else{
                celula i=inicio;
                for(int j=0; j<tamanho; j++, i=i.prox);
                celula tmp=i.prox;
                int elemento=tmp.elemento;
                i.prox=tmp.prox;
                tmp.prox=null;
                i=tmp=null;
            }
            return elemento;
        }


    }
}

