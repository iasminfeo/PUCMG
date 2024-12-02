
import java.util.*;

public class Ciframento {
    public static void main(String[] args)
    {
        Scanner scanf = new Scanner(System.in);
        boolean fim = false;
        while(fim==false){
            String cifra = new String();
            cifra = scanf.nextLine();
            if (cifra.equals("FIM")) {
                break;
            }
            char[] array = cifra.toCharArray();
            for(int i=0; i<cifra.length(); i++){
                if(array[i]<=128){
                    array[i]+=3;
                }
            }
            String nova = new String(array);
            System.out.println(nova);
        }
        scanf.close();
    }
}
