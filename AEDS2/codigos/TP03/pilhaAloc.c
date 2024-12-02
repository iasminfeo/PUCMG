#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_STRING_LENGTH 1000

// Estrutura para armazenar informações de um Pokémon
typedef struct {
    int id;
    char name[MAX_STRING_LENGTH];
} Pokemon;

// Estrutura de célula para a pilha
typedef struct Cell {
    Pokemon pokemon;
    struct Cell* next;
} Cell;

// Estrutura da pilha
typedef struct {
    Cell* top;
} Stack;

// Funções principais da pilha
void initStack(Stack* stack);
void push(Stack* stack, Pokemon pokemon);
Pokemon pop(Stack* stack);
void printStack(Stack* stack);
int getSize(Stack* stack);
char* capitalizeFirstLetter(char* str);

// Função de inicialização da pilha
void initStack(Stack* stack) {
    stack->top = NULL;
}

// Função para empilhar um Pokémon na pilha
void push(Stack* stack, Pokemon pokemon) {
    Cell* newCell = (Cell*)malloc(sizeof(Cell));
    newCell->pokemon = pokemon;
    newCell->next = stack->top;
    stack->top = newCell;
}

// Função para desempilhar um Pokémon da pilha
Pokemon pop(Stack* stack) {
    Pokemon empty = {0};
    if (stack->top == NULL) return empty; // Se a pilha estiver vazia, retorna um Pokémon vazio
    
    Cell* temp = stack->top;
    Pokemon removed = temp->pokemon;
    stack->top = temp->next;
    free(temp); // Libera a memória da célula removida
    return removed;
}

// Função para imprimir a pilha de Pokémon
void printStack(Stack* stack) {
    int index = 0;
    for (Cell* current = stack->top; current != NULL; current = current->next) {
        printf("[%d] #%d -> %s\n", index++, current->pokemon.id, current->pokemon.name);
    }
}

// Função para capitalizar a primeira letra de uma string
char* capitalizeFirstLetter(char* str) {
    if (str != NULL && strlen(str) > 0) {
        str[0] = toupper(str[0]);
    }
    return str;
}

// Função principal
int main() {
    Stack stack;
    initStack(&stack); // Inicializa a pilha
    char input[MAX_STRING_LENGTH];
    
    // Ler Pokémon e empilhar
    while (1) {
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remover newline
        
        if (strcmp(input, "FIM") == 0) break; // Se a entrada for "FIM", finaliza a leitura de Pokémon
        
        // Leitura do ID e nome do Pokémon
        Pokemon pokemon;
        pokemon.id = atoi(input);  // Lê o ID do Pokémon
        fgets(input, sizeof(input), stdin);  // Lê o nome do Pokémon
        input[strcspn(input, "\n")] = 0;  // Remover newline
        strcpy(pokemon.name, input);  // Armazena o nome do Pokémon

        // Empilha o Pokémon na pilha
        push(&stack, pokemon);
    }
    
    // Ler número de comandos
    fgets(input, sizeof(input), stdin);
    int n = atoi(input); // Lê o número de comandos
    
    // Processar comandos
    for (int i = 0; i < n; i++) {
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remover newline
        
        if (input[0] == 'I') {
            // Inserir Pokémon na pilha
            int id;
            char name[MAX_STRING_LENGTH];
            sscanf(input + 2, "%d %s", &id, name);  // Exemplo: "I 2 Pikachu"
            
            Pokemon pokemon;
            pokemon.id = id;
            strcpy(pokemon.name, name);
            push(&stack, pokemon);
        } 
        else if (input[0] == 'R') {
            // Remover Pokémon da pilha
            Pokemon removed = pop(&stack);
            if (removed.id != 0) {
                printf("(R) %s\n", capitalizeFirstLetter(removed.name));
            }
        }
    }
    
    // Imprimir pilha final
    printStack(&stack);
    
    // Liberar memória
    Cell* current = stack.top;
    while (current != NULL) {
        Cell* next = current->next;
        free(current);
        current = next;
    }
    
    return 0;
}
