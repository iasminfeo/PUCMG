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
            celula tmp=new celula(x);
            tmp.prox=topo;
            topo=tmp;
            tmp=null;
        }
        
        public int remover(){
            celula tmp=topo;
            int elemento=topo.elemento;
            topo=topo.prox;
            tmp.prox=null;
            tmp=null;
            return elemento;

        }

    }
}