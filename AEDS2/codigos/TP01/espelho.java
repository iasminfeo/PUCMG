
import java.util.Scanner;
class espelho{
    public static void main(String[] args){
Scanner scanf= new Scanner(System.in);
String numbers="";
int[] seq = new int[2];
int size=0;
while(scanf.hasNext()){
seq[0]=scanf.nextInt();
seq[1]=scanf.nextInt();
numbers="";
for(int i=seq[0]; i<=seq[1]; i++){
numbers+=i;
}
size= numbers.length();
System.out.print(numbers); 
    for(int i=size-1; i>=0; i--){ 
    System.out.print(numbers.charAt(i));
    }
    System.out.print("\n");
    }
    scanf.close();
    }
}