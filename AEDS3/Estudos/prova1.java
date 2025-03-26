//CRIANDO UMA NOVA ENTIDADE COM ID (IDS NAO PODEM SE REPETIR)

Public int create(T obj){
    arquivo.seek(0);
    int id = arquivo.readInt()+1;
    arquivo.seek(0);
    arquivo.writeInt(id);
    obj.setId(id);
    arquivo.seek(arquivo.length());
    long endereco = arquivo.getFilePointer();
    byte[] b= obj.toByteArray();
    short tam = (short) b.length;
    arquivo.write(b);
    arquivo.writeShort(tam);
    indiceDireto.create(id, endereco);
    return id;
}

    //Função Create para CRUD de filmes
    Public int create(T filmes){
        arquivo.seek(0);
        int id = aqruivo.readInt() +1;
        arquivo.seek(0);
        aqruivo.writeInt(id); 
        filmes.setId(id); 
        arquivo.seek(arquivo.length()); 
         long endereco = arquivo.getFilePointer();
        byte [] b = filmes.tobytearray(); 
        short tam = (short)b.lenght;
        arquivo.write(tam);
        aqruivo.write(b);
        indiceDireto.create(id, endereco);
        return id;

        }
