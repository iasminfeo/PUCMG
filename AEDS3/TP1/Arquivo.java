package AEDS3.TP1;

import java.io.*;

public class Arquivo {

    public String nomeEntidade;
    RandomAccessFile arquivo;
    final int TAMANHO_CABECALHO = 6;

    public Arquivo(String ne) throws Exception {
        this.nomeEntidade = ne;
        File f = new File(".//TP1//dados");
        if(!f.exists())
            f.mkdir();
        f = new File(".//TP1//dados//"+nomeEntidade);
        if(!f.exists())
            f.mkdir();
        arquivo = new RandomAccessFile(".//TP1//dados//"+nomeEntidade+"//"+nomeEntidade +".db", "rw");
        if(arquivo.length()<TAMANHO_CABECALHO) {
            arquivo.writeByte(1);  // versão do Arquivo
            arquivo.writeInt(0);   // último ID
        }
    }
    
    public int create ( Registro obj ) throws Exception {
        arquivo.seek(0);
        int proximoID = arquivo.readInt() + 1;
        arquivo.seek( 0 );
        arquivo.writeInt(proximoID);
        obj.setId(proximoID);
        arquivo.seek(arquivo.length());

        long endereco = arquivo.getFilePointer();
        byte[] b = obj.toByteArray();

        arquivo.writeByte(' ');
        arquivo.writeShort(b.length);
        arquivo.write(b);

        indiceDireto.create(new ParIDEndereco(proximoID, endereco));
        return obj.getId( );
    } // end create ( )

    /**
     *  Le um registro do arquivo
     *  @param id id do registro a ser lido
     *  @return objeto lido
     *  @throws Exception
     */
    public Registro read ( int id ) throws Exception {
       Registro obj;
        short tam;
        byte[] b;
        byte lapide;
        System.out.println("ID: " + id);
        ParIDEndereco pid = indiceDireto.read(id);
        if ( pid != null ) {
            arquivo.seek(pid.getEndereco());
            arquivo.seek(TAMANHO_CABECALHO);
            while ( arquivo.getFilePointer() < arquivo.length() ) {
                obj = construtor.newInstance();
                lapide = arquivo.readByte();
                tam = arquivo.readShort();
                b = new byte[tam];
                arquivo.read(b);
                
                if ( lapide == ' ' ) {
                    obj.fromByteArray(b);
                    if ( obj.getId() == id) {
                        return obj;
                    } // end if
                } // end if
            } // end while
        } // end if
        return null;
    } // end read ( )

}

