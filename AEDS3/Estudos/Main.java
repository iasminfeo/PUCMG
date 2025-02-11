package AEDS3.Estudos;
 import java.io.RandomAccessFile;

public class Main {
 
    public static void main(String[] args) {
        //passando os atributos de dois livros criados (na main)

        Livro l1 = new Livro(1, "tudo é rio", "Carla Madeira", 33.5F);
        Livro l2 = new Livro(2, "Natureza da Mordida", "Carla Madeira", 40.2F);
        

        System.out.println("Diretório atual: " + System.getProperty("user.dir"));

        //criando o arquivo "arq" para printar os livros nele.

       RandomAccessFile arq;
        byte[] ba;

        // utilizando o dataoutputstring (que funciona como um processamento intermediario para escrita no arquivo)

        

        try{

        arq = new RandomAccessFile("AEDS3/Estudos/dados/livros.db", "rw");

        long p1 = arq.getFilePointer();

        ba = l1.toByteArray();
        arq.writeInt(ba.length);
        arq.write(ba);
        
        long p2 = arq.getFilePointer();
        ba = l2.toByteArray();
        arq.writeInt(ba.length);
        arq.write(ba);
        
       




        ///   LEITURA
        
        Livro l3 = new Livro();
        Livro l4 = new Livro();
        int tam;
        arq.seek(p1);

        tam = arq.readInt();
        ba = new byte[tam];
        arq.read(ba);
        l3.fromByteArray(ba);

        arq.seek(p2);
        tam = arq.readInt();
        ba = new byte[tam];
        arq.read(ba);
        l4.fromByteArray(ba);

        System.out.println(l3);

        // l4.idLivro = dis.readInt();
        // l4.titulo = dis.readUTF();
        // l4.autor = dis.readUTF();
        // l4.preco = dis.readFloat();

        System.out.println(l4);

            arq.close();
       
        } catch(Exception e){
            e.printStackTrace();
        }
       
    }
}
