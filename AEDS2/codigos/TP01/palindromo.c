#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void especiais(char* word,int size) {
    for (int i = 0; i < size; i++) {
    // Verifica se o caractere é um dígito ou uma letra
    if ((word[i] >= 'A' && word[i] <= 'Z') || (word[i] >= 'a' && word[i] <= 'z') || (word[i] >= '0' && word[i] <= '9')) {
         // Não é um caractere especial
    } else {
       word[i]='1';
    }
    }
    return;
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
        especiais(word, size);

        j = size - 1;

        for (int i = 0; i < size / 2; i++) {  // Só percorre metade do tamanho
            if (word[i] != word[j - i]) {
                cont++;
                break;  // Sai do loop se encontrar diferença
            }
        }

        if (cont > 0 && fim == 0) {
            printf("NAO\n");
        } else if (fim == 0) {
            printf("SIM\n");
        }
        cont = 0;
    }

    free(word);  // Libera a memória alocada
    return 0;
}
