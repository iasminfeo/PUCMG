package AEDS3.Estudos;

import java.text.DecimalFormat;


   // criando o objeto Livro com os atributos : id, títulp, autor, preço.
public class Livro {
    protected int idLivro;
    protected String titulo;
    protected String autor;
    protected float preco;
    

    // usando o DecimalFormat para formatar o preço do livro no modelo pradão global
    DecimalFormat df = new DecimalFormat("#,##0.00");


        //construtor para passar os valores dos atributos
    public Livro(int i, String t, String a , float p){
        idLivro = i;
        titulo = t;
        autor = a;
        preco = p;
    }


    // usando o metodo toString para tranformar o objeto em uma string legivel.
    public String toString(){
       
        return "\nID:" + idLivro +
            "\nTítulo: " + titulo +
            "\nAutor: " + autor +
            "\nPreço: R$ " + df.format(preco);
    }
}
