  
 



 class celula{
    public int elemento;
    public celula prox;
    public celula(){
        this(0);
    }
    public celula(int x){
        this.elemento=x;
        this.prox=null;
    }
}

    public class pilha{
        public celula topo;
        public pilha(){
            topo=null;
        }
        
        public void inserir(int x){
            celula tmp= new celula(x);
            tmp.prox=topo;
            topo=tmp;
            tmp=null;
        }


        public int remover(){
            celula tmp=topo;
            int elemento=tmp.elemento;
            topo=topo.prox;
            tmp.prox=null;
            tmp=null;
            return elemento;
        }


        public void mostrar(){
            for (celula i=topo; i!=null; i=i.prox){
            System.out.println(" "+i.elemento);
            }
        }


            // celula maiorpilha(){
            //     celula retorno= null;
            //     int contador, maior=0;
            //     for(celula i=this.topo; i!=null; i=i.prox){
            //         contador=0;
            //         for(celula j=i.topo; j!=null; j=j.prox){
            //             contador++;
            //         }
            //     }
            // }

        
        
    }











