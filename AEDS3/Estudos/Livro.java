package AEDS3.Estudos;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;


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
    public Livro() {
    idLivro = -1;
    titulo = "";
    autor = "";
    preco = 0F;
    }


    // usando o metodo toString para tranformar o objeto em uma string legivel.
    public String toString(){
       
        return "\nID:" + idLivro +
            "\nTítulo: " + titulo +
            "\nAutor: " + autor +
            "\nPreço: R$ " + df.format(preco);
    }

    //METODO QUE DESCREVE O LIVRO POR MEIO DE UM VETOR DE BYTES
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(idLivro);
        dos.writeUTF(titulo);
        dos.writeUTF(autor);
        dos.writeFloat(preco);
        return baos.toByteArray();
    }

    //METODO INVERSO: LE DO ARQUIVO O VETOR DE BYTES E CARREGA O OBJ

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        idLivro = dis.readInt();
        titulo = dis.readUTF();
        autor = dis.readUTF();
        preco = dis.readFloat();
    }
}
