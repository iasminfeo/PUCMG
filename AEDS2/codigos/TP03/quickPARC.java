import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Definição da classe para Nó de uma Lista Duplamente Encadeada
class Node {
    quickPARC pokemon;
    Node next;
    Node prev;

    public Node(quickPARC pokemon) {
        this.pokemon = pokemon;
        this.next = null;
        this.prev = null;
    }
}

// Classe para lista duplamente encadeada
class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Adiciona um Pokémon no final da lista
    public void add(quickPARC pokemon) {
        Node newNode = new Node(pokemon);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Remove um Pokémon pela sua ID
    public void removeById(int id) {
        Node current = head;
        while (current != null) {
            if (current.pokemon.getId() == id) {
                if (current.prev != null) current.prev.next = current.next;
                if (current.next != null) current.next.prev = current.prev;
                if (current == head) head = current.next;
                if (current == tail) tail = current.prev;
                size--;
                return;
            }
            current = current.next;
        }
    }

    // Retorna o tamanho da lista
    public int size() {
        return size;
    }

    // Acessa o Pokémon na posição 'index'
    public quickPARC get(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.pokemon;
    }

    // Imprime todos os Pokémons da lista
    public void printAll() {
        Node current = head;
        while (current != null) {
            current.pokemon.imprimirPokemon();
            current = current.next;
        }
    }
}

public class quickPARC {
    private int id;
    private int generation;
    private String name;
    private String description;
    private String type1;
    private String type2;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    public quickPARC() {
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.type1 = "";
        this.type2 = "";
        this.abilities = new ArrayList<>();
        this.weight = 0;
        this.height = 0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = null;
    }

    public quickPARC(int id, int generation, String name, String description, String type1, String type2,
                     ArrayList<String> abilities, double weight, double height, int captureRate,
                     boolean isLegendary, LocalDate captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.type1 = type1;
        this.type2 = type2;
        this.abilities = new ArrayList<>(abilities);
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    // Getters e Setters omitidos para brevidade

    public void lerFromCsv(String line) {
        String[] atributos = parseCsvLine(line);
        set(
            Integer.parseInt(atributos[0].trim()), 
            Integer.parseInt(atributos[1].trim()), 
            atributos[2].trim(), 
            atributos[3].trim(), 
            atributos[4].trim(), 
            atributos[5].trim(), 
            parseAbilities(atributos[6].trim()), 
            parseDouble(atributos[7]), 
            parseDouble(atributos[8]), 
            parseInt(atributos[9]), 
            parseBoolean(atributos[10]), 
            parseLocalDate(atributos[11])
        );
        if (this.weight < 0) {
            setWeight(0.0);
        }
        if (this.height < 0) {
            setHeight(0.0);
        }
    }

    private String[] parseCsvLine(String line) {
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        return line.split(regex);
    }

    private static ArrayList<String> parseAbilities(String abilitiesString) {
        abilitiesString = abilitiesString.replace("\"", "").replace("[", "").replace("]", "").trim();
        String[] abilitiesArray = abilitiesString.split(",\\s*");
        ArrayList<String> abilitiesList = new ArrayList<>();
        for (String ability : abilitiesArray) {
            abilitiesList.add(ability.trim());
        }
        return abilitiesList;
    }

    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private int parseInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value.trim());
    }

    private boolean parseBoolean(String value) {
        return value != null && Integer.parseInt(value.trim()) > 0;
    }

    private LocalDate parseLocalDate(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(value.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return null;
        }
    }

    public void imprimirPokemon() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = captureDate != null ? captureDate.format(formatter) : "N/A";
        System.out.println("[#" + id + " -> " + name + ": " + description + " - " + 
            "['" + type1  + (type2.isEmpty() ? "" : "', '" + type2) + "']" +" - "  + abilities + " - " +
            weight + "kg" + " - " + height + "m" + " - " + captureRate + "% - " + 
            isLegendary + " - " + generation +" gen] - " + formattedDate);
    }

    public int getId() {
        return id;
    }

    public int getGeneration() {
        return generation;
    }

    public String getNameForComparison() {
        return name.toLowerCase();
    }

    // Função de quicksort atualizada para ordenar a lista completa
    public static void quickSort(DoublyLinkedList pokemons, int low, int high, int comparisons[], int movements[]) {
        if (low < high) {
            int pi = partition(pokemons, low, high, comparisons, movements);
            quickSort(pokemons, low, pi - 1, comparisons, movements);
            quickSort(pokemons, pi + 1, high, comparisons, movements);
        }
    }

    private static int partition(DoublyLinkedList pokemons, int low, int high, int comparisons[], int movements[]) {
        quickPARC pivot = pokemons.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            comparisons[0]++;
            if (pokemons.get(j).getGeneration() < pivot.getGeneration() ||
                (pokemons.get(j).getGeneration() == pivot.getGeneration() &&
                 pokemons.get(j).getNameForComparison().compareTo(pivot.getNameForComparison()) < 0)) {
                i++;
                movements[0]++;
                quickPARC temp = pokemons.get(i);
                pokemons.add(i, pokemons.get(j)); // Adicionando método de adicionar em uma posição específica
                pokemons.add(j, temp);
            }
        }
        movements[0]++;
        quickPARC temp = pokemons.get(i + 1);
        pokemons.add(i + 1, pokemons.get(high));
        pokemons.add(high, temp);
        return i + 1;
    }

    public static void main(String[] args) {
        DoublyLinkedList pokemonList = new DoublyLinkedList();
        ArrayList<Integer> idsToPrint = new ArrayList<>();
        Scanner scanf = new Scanner(System.in);
        String input;

        // Lê os IDs dos Pokémons
        while (true) {
            input = scanf.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            idsToPrint.add(Integer.parseInt(input));
        }

        // Lê todos os Pokémons do CSV
        String path = "/tmp/pokemon.csv"; // Caminho do arquivo CSV
        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            scan.nextLine(); // Ignorar o cabeçalho do arquivo

            while (scan.hasNextLine()) {
                input = scan.nextLine();
                quickPARC pokemon = new quickPARC();
                pokemon.lerFromCsv(input);
                pokemonList.add(pokemon);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File Not Found.");
        }

        // Filtra a lista de Pokémons para incluir apenas os IDs desejados
        DoublyLinkedList filteredList = new DoublyLinkedList();
        for (int i = 0; i < pokemonList.size(); i++) {
            quickPARC pokemon = pokemonList.get(i);
            if (idsToPrint.contains(pokemon.getId())) {
                filteredList.add(pokemon);
            }
        }

        // Variáveis para contagem de comparações e movimentações
        int[] comparisons = {0};
        int[] movements = {0};

        // Ordena a lista filtrada em ordem de geração
        long startTime = System.nanoTime();
        quickSort(filteredList, 0, filteredList.size() - 1, comparisons, movements);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Imprime a lista completa
        filteredList.printAll();

        // Criação do log
        String logFileName = "854946.txt"; // Substitua "matricula" pela sua matrícula
        try (FileWriter writer = new FileWriter(logFileName)) {
            writer.write("854946\t" + comparisons[0] + "\t" + movements[0] + "\t" + (duration / 1e6) + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo de log: " + e.getMessage());
        }

        scanf.close(); // Fecha o scanner
    }
}
