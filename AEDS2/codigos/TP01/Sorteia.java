
    import java.util.Scanner;
    import java.util.Random;

    class Sorteia{
        public static void main(String[] args){
            Scanner scanf=new Scanner(System.in);
            String phrase;
            int size;
          Random gerador = new Random();
        gerador.setSeed(4);
            while(true){
                phrase= scanf.nextLine();
                size=phrase.length();
                  if(phrase.charAt(0)=='F' &&phrase.charAt(1)=='I'&&phrase.charAt(2)=='M'&&phrase.length()==3){
                    break;
                }
                        char[] news= new char[size];
              

   char a=((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
   char b=((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
    for(int i=0; i<size; i++){
        news[i]=phrase.charAt(i);
        if(news[i]==a){
            news[i]=b;
        }
        System.out.print(news[i]);
    }
        System.out.print("\n");

    }
    scanf.close();

        }
    }