
        import java.util.Scanner;
        class StringX{
        public static boolean vogal (String phrase, int size) {
                for(int i=0; i<size; i++){
            if(phrase.charAt(i)!='a'&&phrase.charAt(i)!='e'&&phrase.charAt(i)!='i'&&phrase.charAt(i)!='o'&&phrase.charAt(i)!='u' && phrase.charAt(i)!='A'&&phrase.charAt(i)!='E'&&phrase.charAt(i)!='I'&&phrase.charAt(i)!='O'&&phrase.charAt(i)!='U'&&phrase.charAt(i)!=' '){
        return false;
            }
            }
             return true;
        }
             public static boolean conso (String phrase, int size) {
                for(int i=0; i<size; i++){
            if (vogal(phrase,size)) {
                return false;
            }else if(phrase.charAt(i)!=' '&&(phrase.charAt(i)<65||phrase.charAt(i)>123||phrase.charAt(i)<97&&phrase.charAt(i)>90)){
                 return false;
            }else if(phrase.charAt(i)=='a'||phrase.charAt(i)=='e'||phrase.charAt(i)=='i'||phrase.charAt(i)=='o'||phrase.charAt(i)=='u' || phrase.charAt(i)=='A'||phrase.charAt(i)=='E'||phrase.charAt(i)=='I'||phrase.charAt(i)=='O'||phrase.charAt(i)=='U'){
        return false;
            }
        }
        return true;
                }
                 public static boolean intX(String phrase, int size){
                    for(int i=0; i<size; i++){
                if(phrase.charAt(i)>'9'||phrase.charAt(i)<'0'){
                    return false;
                }
                }
                return true;
                 }
                  public static boolean realX(String phrase, int size, int cont){
                      if (intX(phrase,size)==true) {
                return true;
                      }
                    for(int i=0; i<size; i++){
                if(((phrase.charAt(i)>'9'||phrase.charAt(i)<'0'))&&(phrase.charAt(i)!=','&&phrase.charAt(i)!='.')){

                    return false;
                }else{
                    if((phrase.charAt(i)=='.')||(phrase.charAt(i)==',')){
                    cont++;
                }
                }
                }
                if(cont>1){
                    return false;
                }
                return true;
                 }
              
            public static void main(String[] args){  
        Scanner scanf= new Scanner(System.in);
        String phrase;
        int size;

        while(true){
             phrase= scanf.nextLine();
            
            if(phrase.charAt(0)=='F'&&phrase.charAt(1)=='I'&&phrase.charAt(2)=='M'){
            break;
        }
        else{
        size=phrase.length();
        
         boolean x1 = StringX.vogal(phrase, size);   
        boolean x2 = StringX.conso(phrase, size);   
        boolean x3 = StringX.intX(phrase, size);   
         boolean x4 = StringX.realX(phrase, size, 0);   

           System.out.println((x1 ? "SIM" : "NAO") + " " +
                   (x2 ? "SIM" : "NAO") + " " +
                   (x3 ? "SIM" : "NAO") + " " +
                   (x4 ? "SIM" : "NAO"));   
        }
            }
            }
            }
                     