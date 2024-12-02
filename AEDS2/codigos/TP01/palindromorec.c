#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
void especiais(char* word,int size, int i) {
   if(i==size){
    return;
   } else{
    // Verifica se o caractere é um dígito ou uma letra
    if ((word[i] >= 'A' && word[i] <= 'Z') || (word[i] >= 'a' && word[i] <= 'z') || (word[i] >= '0' && word[i] <= '9')) {
         especiais(word, size, i+1);
    } else {
       word[i]='1';
        especiais(word, size, i+1);
    }
    }
    return;
}
bool pali(char* word, int i, int j){
     if(i>j){
        return true;
     }else{  // Só percorre metade do tamanho
            if (word[i] != word[j]) {
                return false;
              // Sai do loop se encontrar diferença
            }else{
               return pali(word, i+1, j-1);
            }
     }
}
int main() {
    char* word;
    word = (char*) malloc(130 * sizeof(char));  // Aloca memória para 150 caracteres
    int size, cont = 0, j, fim = 0;

    while ( scanf("%[^\n]%*c", word)) {
        if (word[0] == 'F' && word[1] == 'I' && word[2] == 'M' && word[3] == '\0') {
            break;
        }
        size = strlen(word);
        especiais(word, size, 0);


        if (pali(word, 0, size-1)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    free(word);  // Libera a memória alocada
    return 0;
}
