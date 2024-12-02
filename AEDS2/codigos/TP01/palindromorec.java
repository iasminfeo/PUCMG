
import java.util.Scanner;
 class palindromorec{
    public static boolean pal(String word, int i, int j){
if(i>=j){
    System.out.println("SIM");
     return  true;
}
else{
    if(word.charAt(i)!=word.charAt(j)){
        System.out.println("NAO");
        return false;
    }else{
    return pal(word, i+1, j-1);
}
}
    }
 public static void main(String[] args){
String word;
  Scanner scanf= new Scanner(System.in);
int size, j;
word=scanf.nextLine();
while(true){
     size=word.length();
    if (word.equals("FIM")) {
                break; // Sai do loop se a palavra for "FIM"
            }
            palindromorec.pal(word, 0, size-1);   
word=scanf.nextLine();
}
scanf.close();
 }
 }
 
