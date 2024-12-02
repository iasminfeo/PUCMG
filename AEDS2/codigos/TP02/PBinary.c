#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

// Atributos de um Pokemon
typedef struct {
    int id;
    int generation;
    char name[80];
    char description[80];
    char type1[80];
    char type2[80];
    char abilities[200];
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    char captureDate[12];
} Pokemon;

// Declaração das funções
void formatarString(char *str);
void adicionarPokemon(char *linha, Pokemon *pokemon);
void lerArquivo(const char *nomeArquivo, Pokemon pokemons[], int *totalPokemons);
int buscarPokemonID(int id, Pokemon pokemons[], int totalPokemons);

void formatarString(char *str) {
    int dentroColchetes = 0;  
    int j = 0;  

    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] == '[') {
            dentroColchetes = 1;  
        } else if (str[i] == ']') {
            dentroColchetes = 0; 
        }

        if (str[i] == ',' && dentroColchetes == 0) {
            str[j++] = ';';
        } else if (str[i] != '"') {
            str[j++] = str[i];
        }
    }

    str[j] = '\0'; 
}

void adicionarPokemon(char *linha, Pokemon *pokemon) {
    char *token;
    token = strsep(&linha, ";");
    pokemon->id = atoi(token);

    token = strsep(&linha, ";");
    pokemon->generation = atoi(token);

    token = strsep(&linha, ";");
    strcpy(pokemon->name, token);

    token = strsep(&linha, ";");
    strcpy(pokemon->description, token);

    token = strsep(&linha, ";");
    strcpy(pokemon->type1, token);

    token = strsep(&linha, ";");
    if (token[0] != 0) strcpy(pokemon->type2, token);

    token = strsep(&linha, ";");
    strcpy(pokemon->abilities, token);

    token = strsep(&linha, ";");
    pokemon->weight = atof(token);  

    token = strsep(&linha, ";");
    pokemon->height = atof(token);

    token = strsep(&linha, ";");
    pokemon->captureRate = atoi(token);

    token = strsep(&linha, ";");
    pokemon->isLegendary = atoi(token);  

    token = strsep(&linha, ";");
    strcpy(pokemon->captureDate, token);
}

void lerArquivo(const char *nomeArquivo, Pokemon pokemons[], int *totalPokemons) {
    FILE *arquivo = fopen(nomeArquivo, "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo %s\n", nomeArquivo);
        return;
    }

    char linha[512];
    *totalPokemons = 0;

    while (fgets(linha, sizeof(linha), arquivo)) {
        formatarString(linha);  
        adicionarPokemon(linha, &pokemons[*totalPokemons]);
        (*totalPokemons)++;
    }

    fclose(arquivo);
}

int buscarPokemonID(int id, Pokemon pokemons[], int totalPokemons) {
    for (int i = 0; i < totalPokemons; i++) {
        if (pokemons[i].id == id) {
            return i;
        }
    }

    return -1;
}

int main(void) {
    Pokemon pokemons[1000];
    int totalPokemons;

    lerArquivo("/tmp/pokemon.csv", pokemons, &totalPokemons);

    char input[10];
    int ids[1000]; // Armazenar os IDs
    int idsCount = 0;
    int comparacoes = 0;

    // Tempo de execução
    clock_t start = clock();

    // Leitura de IDs até "FIM"
    while (true) {
        scanf(" %s", input);
        if (strcmp(input, "FIM") == 0) {
            break;
        }

        ids[idsCount++] = atoi(input); // Armazena os IDs lidos
    }

    // Leitura de nomes até "FIM"
    while (true) {
        scanf(" %s", input);
        if (strcmp(input, "FIM") == 0) {
            break;
        }

        bool encontrado = false;
        for (int i = 0; i < idsCount; i++) {
            if (strcmp(pokemons[buscarPokemonID(ids[i], pokemons, totalPokemons)].name, input) == 0) {
                encontrado = true;
                break;
            }
            comparacoes++; // Incrementa a contagem de comparações
        }
        
        if (encontrado) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    clock_t end = clock();
    double executionTime = (double)(end - start) / CLOCKS_PER_SEC * 1000; // Tempo em milissegundos

    // Criação do arquivo de log
    FILE *logFile = fopen("matrícula_binaria.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "854946\t%.2f ms\t%d\n", executionTime, comparacoes);
        fclose(logFile);
    } else {
        printf("Erro ao criar o arquivo de log.\n");
    }

    return 0;
}
