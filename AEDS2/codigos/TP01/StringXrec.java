
import java.util.Scanner;
class StringXrec{
     public static boolean vogal (String phrase, int size, int i) {
               if(i==size){
                return true;
               } 
                if(i<size){
                    if(phrase.charAt(i)!='a'&&phrase.charAt(i)!='e'&&phrase.charAt(i)!='i'&&phrase.charAt(i)!='o'&&phrase.charAt(i)!='u' && phrase.charAt(i)!='A'&&phrase.charAt(i)!='E'&&phrase.charAt(i)!='I'&&phrase.charAt(i)!='O'&&phrase.charAt(i)!='U'&&phrase.charAt(i)!=' '){
        return false;
            }
            }
             
             return vogal (phrase, size, i+1);
                }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
             
                 public static boolean conso (String phrase, int size, int i) {
               if(i==size){
                return true;
               }
               if(i<size){
            if (vogal(phrase,size,0)) {
                return false;
            }else if(phrase.charAt(i)!=' '&&(phrase.charAt(i)<65||phrase.charAt(i)>123||phrase.charAt(i)<97&&phrase.charAt(i)>90)){
                 return false;
            }else if(phrase.charAt(i)=='a'||phrase.charAt(i)=='e'||phrase.charAt(i)=='i'||phrase.charAt(i)=='o'||phrase.charAt(i)=='u' || phrase.charAt(i)=='A'||phrase.charAt(i)=='E'||phrase.charAt(i)=='I'||phrase.charAt(i)=='O'||phrase.charAt(i)=='U'){
        return false;
            }
                 }
        
        return conso(phrase, size, i+1);
                }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

                 public static boolean intX(String phrase, int size, int i){
                   if(i==size){
                    return true;
                   }
                  else{
                if(phrase.charAt(i)>'9'||phrase.charAt(i)<'0'){
                    return false;
                }
                }
              return  intX(phrase, size, i+1);

                 }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                  public static boolean realX(String phrase, int size, int i, int cont){
                      if (intX(phrase,size,0)) {
                return true;
                      }
                    if(i==size){
                        return true;
                        }else {
                if(((phrase.charAt(i)>'9'||phrase.charAt(i)<'0'))&&(phrase.charAt(i)!=','&&phrase.charAt(i)!='.')){
                    return false;
                }else if((phrase.charAt(i)==',')||(phrase.charAt(i)=='.')){
                    cont++;
                }
                }
                if(cont>1){
                    return false;
                }

              return realX(phrase, size, i+1, cont);
    
                  }
    public static void main(String [] args){
        Scanner scanf= new Scanner(System.in);
        while(true){
            String phrase= scanf.nextLine();
            int size= phrase.length();
            if(phrase.equals("FIM")){
                break;
            }else{
         boolean x1 = StringXrec.vogal(phrase, size, 0);   
        boolean x2 = StringXrec.conso(phrase, size, 0);   
        boolean x3 = StringXrec.intX(phrase, size, 0);   
         boolean x4 = StringXrec.realX(phrase, size, 0, 0);   
          System.out.println((x1 ? "SIM" : "NAO") + " " +
                   (x2 ? "SIM" : "NAO") + " " +
                   (x3 ? "SIM" : "NAO") + " " +
                   (x4 ? "SIM" : "NAO"));   
            }
        }

     scanf.close();   
    }
}