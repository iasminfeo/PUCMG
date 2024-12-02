import java.util.Scanner;

class celula{
     int elemento;
     celula inf, esq, dir, sup;

    public celula(){
        this(0);
    }
    public celula(int x){
        this(x, null, null, null, null);
    }
    public celula(int x, celula esq, celula dir, celula sup, celula inf){
        this.elemento=x;
        this.inf =inf;
        this.esq =esq;
        this.dir =dir;
        this.sup =sup;
    }
}

 
    public class matriz{
        celula inicio;
        int colunas;
        int linhas;
        
        static Scanner scanf = new Scanner(System.in);

        public matriz(int linhas, int colunas){
            inicio = new celula(0);
            this.linhas=linhas;
            this.colunas=colunas;
            celula i = inicio;

            //for para criar a primeira linha!
            for(int j=1; j<colunas; j++){
                celula nova = new celula(0);
                i.dir=nova;
                nova.esq= i;
                i= nova;  //muda o parametro para mudar os apontadores!
            }
            celula linhaAnterior = inicio;
            for(int j=1; j<linhas; j=j+1){
                celula novaLinha = new celula(0);
                linhaAnterior.inf= novaLinha;
                novaLinha.sup=linhaAnterior;

                celula celulaAtualLinha = novaLinha;
                celula celulaAcima = linhaAnterior;

                for(int k=1; k<colunas; k++){
                    celula novaCelula = new celula(0);
                    celulaAtualLinha.dir = novaCelula;
                    novaCelula.esq = celulaAtualLinha;
                    celulaAcima= celulaAcima.dir;
                    celulaAcima.inf = novaCelula;
                    novaCelula.sup=celulaAcima;
                    celulaAtualLinha= novaCelula;
                }
                linhaAnterior= novaLinha;
            } //END matriz
            public void ler(){
                for( celula linha= inicio; linha!= null; linha= linha.inf){
                    for( celula coluna=linha; coluna!= null; coluna= coluna.dir){
                        if(scanf.hasNextInt()){
                            coluna.elemento= scanf.nextInt();
                        }
                    }
                }
            }
            
    public void mostrar ( )
    {
        for( celula linha = this.inicio; linha != null; linha = linha.inf )
        {
            for( celula coluna = linha; coluna != null; coluna = coluna.dir ) {
                System.out.print( coluna.elemento + " " );
            } // end for
            System.out.println( "" );
        } // end for
    } // end mostrar ( )

    public boolean eQuadrada ( )
    {
        return ( this.linhas == this.colunas );
    } // end eQuadrada ( )

    public matriz soma ( matriz other )
    {
        matriz resultado = null;
        if( this.linhas != other.linhas && this.colunas != other.colunas  ) {
            System.err.println( "ERRO: matrizes com dimensoes diferentes!" );
        }
        else
        {
            resultado = new matriz( this.linhas, this.colunas );
            // A = atual, O = outra, N = nova
            for( celula linA = this.inicio, linO = other.inicio, linN = resultado.inicio;
                        linA != null     && linO != null      && linN != null; 
                        linA = linA.inf   , linO = linO.inf    , linN = linN. inf )
            {
                for( celula colA = linA    , colO = linO    , colN = linN;
                            colA != null  && colO != null  && colN != null;
                            colA = colA.dir, colO = colO.dir, colN = colN.dir ) {
                    colN.elemento = colA.elemento + colO.elemento;
                } // end for
            } // end for
        } // end if
        return ( resultado );
    } // end soma ( )

    public matriz multiplicacao ( matriz other )
    {
        matriz resultado = null;
        if( this.colunas != other.linhas ) {
            System.err.println( "[MULTIPLICACAO] ERRO: atual.coluna != other.linha" );
        }
        else
        {
            resultado = new matriz( this.linhas, other.colunas );
            for( celula linA = this.inicio, linN = resultado.inicio; linA != null; linA = linA.inf, linN = linN.inf ) 
            {
                for( celula colO = other.inicio, colN = linN; colO != null; colO = colO.dir, colN = colN.dir ) 
                {
                    int soma = 0;
                    for( celula colA = linA, linO = colO; colA != null && linO != null; colA = colA.dir, linO = linO.inf ) {
                        soma = soma + colA.elemento * linO.elemento;
                    } // end for
                    colN.elemento = soma;
                } // end for
            } // end for
        } // end if
        return ( resultado );
    } // end multiplicacao ( )

    public void mostrarDiagonalPrincipal ( )
    {
        if( this.eQuadrada( ) == true )
        {
            celula temp = inicio;
            for( int i = 0; i < this.linhas; i = i + 1 )
            {
                System.out.print( temp.elemento + " " );
                if(  temp.inf != null && temp.dir != null ) {
                    temp = temp.inf.dir;
                } // end if
            } // end for
            System.out.println( );
        } // end ir
    } // end mostrarDiagonalPrincipal ( )

    public void mostrarDiagonalSecundaria ( )
    {
        if( this.eQuadrada( ) == true )
        {
            celula temp = inicio;
            while( temp.dir != null ) {
                temp = temp.dir;
            } // end while
            for( int i = 0; i < this.linhas; i = i + 1 )
            {
                System.out.print( temp.elemento + " " );
                if( temp.inf != null && temp.esq != null ) {
                    temp = temp.inf.esq;
                } // end if
            } // end for
            System.out.println( );
        } // end ir
    } // end mostrarDiagonalPrincipal ( )

    public static void main ( String [] args ) throws Exception
    {
        int numCasos = 0;
        int linha = 0;
        int coluna = 0;

        numCasos = scanf.nextInt( );
        for( int i = 0; i < numCasos; i = i + 1 )
        {
            linha = scanf.nextInt( );
            coluna = scanf.nextInt( );
            matriz m1 = new matriz( linha, coluna );
            m1.ler( );

            linha = scanf.nextInt( );
            coluna = scanf.nextInt( );
            matriz m2 = new matriz( linha, coluna );
            m2.ler( );

            m1.mostrarDiagonalPrincipal( );
            m1.mostrarDiagonalSecundaria( );

            matriz soma = m1.soma( m2 );
            soma.mostrar( );

            matriz mult = m1.multiplicacao( m2 );
            mult.mostrar( );
        } // end for
        scanf.close( );
    }
    }


               
      