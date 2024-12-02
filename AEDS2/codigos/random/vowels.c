#include <stdio.h>
#include <string.h>

int main(){
    char alfa[50];
    char novo[100];
    int iguais;
    int sizea, sizeb;
    while ( scanf("%[^\n]%*c", alfa)) {
    scanf("%[^\n]%*c", novo);
    sizeb=strlen(alfa);
    sizea=strlen(novo);
    iguais=0;
    for(int i=0; i<sizea; i++){
        for(int j=0; j<sizeb; j++){
            if(novo[i]==alfa[j]){
                iguais++;
            }
     }
    }
    printf("%d\n", iguais);
    }
    















    return 0;
}