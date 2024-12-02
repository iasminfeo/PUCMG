
import java.util.Scanner;
 class palindromo{
 public static void main(String[] args){
String word;
  Scanner scanf= new Scanner(System.in);
int size, j, cont=0;
word=scanf.nextLine();
while(true){
     size=word.length();
    if (word.equals("FIM")) {
                break; // Sai do loop se a palavra for "FIM"
            }
            j=size;
for(int i=0; i<size; i++){
j--;
    if(word.charAt(i)!=word.charAt(j)){
        cont++;
    }
}
if(cont==0){
    System.out.println("SIM");
}
else{
      System.out.println("NAO");
}
cont=0;
word=scanf.nextLine();
}
scanf.close();
}
}