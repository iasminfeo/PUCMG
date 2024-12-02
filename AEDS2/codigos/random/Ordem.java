import java.util.Scanner;
public class Ordem{
    public static void main(String[] args){
 Scanner scanf= new Scanner(System.in);
 int[] numeros= new int[100]; 
 int temp, cont=0;
 numeros[0]=1;
  for(int i=0; i<100; i++){
    cont++;
   
    numeros[i]=scanf.nextInt();
     if(numeros[i]==99){
        break;
    }
    for(int j=0; j<i; j++){
        if(numeros[j]>numeros[i]){
    temp=numeros[j];
    numeros[j]=numeros[i];
    numeros[i]=temp;
        }
    } 
    
  }
  for(int i=0; i<cont; i++){
    System.out.println(numeros[i]); 
  }



  scanf.close();
    }
}