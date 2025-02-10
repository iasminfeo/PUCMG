package AEDS3.Estudos;


import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
 
    public static void main(String[] args) {
        //passando os atributos de dois livros criados (na main)

        Livro l1 = new Livro(1, "tudo é rio", "Carla Madeira", 33.5F);
        Livro l2 = new Livro(2, "Natureza da Mordida", "Carla Madeira", 40.2F);
        


        // printano os dois livros no terminal

        System.out.println(l1);
        System.out.println(l2);

        System.out.println("Diretório atual: " + System.getProperty("user.dir"));

        //criando o arquivo "arq" para printar os livros nele.

        FileOutputStream arq;

        // utilizando o dataoutputstring (que funciona como um processamento intermediario para escrita no arquivo)

        DataOutputStream dos;
        
        try{

        arq = new FileOutputStream("AEDS3/Estudos/dados/livros.db");
       
        dos = new DataOutputStream(arq);
         // "dos" pega o int e escreve no "arq"
        dos.writeInt(l1.idLivro);
        dos.writeUTF(l1.titulo); //string no padrao utf8
        dos.writeUTF(l1.autor);
        dos.writeFloat(l1.preco);

        dos.writeInt(l2.idLivro);
        dos.writeUTF(l2.titulo); //string no padrao utf8
        dos.writeUTF(l2.autor);
        dos.writeFloat(l2.preco);

        
        } catch(IOException e){
            e.printStackTrace();
        }
       
    }
}
