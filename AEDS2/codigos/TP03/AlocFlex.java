import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class Selection {
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

    public Selection() {
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

    public void set(int id, int generation, String name, String description, String type1, String type2,
                    ArrayList<String> abilities, double weight, double height, int captureRate,
                    boolean isLegendary, LocalDate captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.type1 = type1;
        this.type2 = type2;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    public void ler(String id) {
        String path = "/tmp/pokemon.csv"; // Caminho do arquivo CSV
        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            scan.nextLine(); // Ignorar o cabeçalho do arquivo

            boolean found = false;
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (id.equals(input.substring(0, input.indexOf(",")))) {
                    found = true;
                    String[] atributos = parseCsvLine(input);
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
                    break;
                }
            }
            if (!found) {
                System.out.println("Pokémon não encontrado.");
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File Not Found.");
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
        return (value == null || value.trim().isEmpty()) ? 0.0 : Double.parseDouble(value.trim());
    }

    private int parseInt(String value) {
        return (value == null || value.trim().isEmpty()) ? 0 : Integer.parseInt(value.trim());
    }

    private boolean parseBoolean(String value) {
        return value != null && Integer.parseInt(value.trim()) > 0;
    }

    private LocalDate parseLocalDate(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(value.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void imprimirPokemon(int index) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = captureDate != null ? captureDate.format(formatter) : "N/A";
        System.out.println("[" + index + "] [#" + id + " -> " + name + ": " + description + " - " + 
            "['" + type1 + (type2.isEmpty() ? "" : "', '" + type2) + "'] - " +
            abilities + " - " + weight + "kg - " + height + "m - " + 
            captureRate + "% - " + isLegendary + " - " + generation + " gen] - " + formattedDate);
    }

    public String getNameForComparison() {
        return name.toLowerCase();
    }

    public String getName() {
        return name;
    }
}

class PilhaPokemon {
    private Selection[] pilha;
    private int topo;

    public PilhaPokemon(int capacidade) {
        pilha = new Selection[capacidade];
        topo = -1;
    }

    public void empilhar(Selection pokemon) {
        if (topo < pilha.length - 1) {
            pilha[++topo] = pokemon;
        } else {
            System.out.println("Pilha cheia!");
        }
    }

    public Selection desempilhar() {
        if (topo == -1) {
            System.out.println("Pilha vazia!");
            return null;
        }
        return pilha[topo--];
    }

    public void mostrar() {
        // Exibindo a pilha na ordem correta (de índice 0 até topo)
        for (int i = 0; i <= topo; i++) {
            pilha[i].imprimirPokemon(i);
        }
    }
}

public class AlocFlex {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        PilhaPokemon pilha = new PilhaPokemon(100); // Definindo a capacidade da pilha para 100 elementos

        // Lê os IDs dos Pokémons
        while (true) {
            String input = scanf.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            Selection pokemon = new Selection();
            pokemon.ler(input);
            pilha.empilhar(pokemon);
        }

        // Lê os comandos de manipulação
        int n = Integer.parseInt(scanf.nextLine());
        for (int i = 0; i < n; i++) {
            String command = scanf.nextLine();
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "I": // Inserir na pilha (empilhar)
                    Selection pokemonEmpilhar = new Selection();
                    pokemonEmpilhar.ler(parts[1]);
                    pilha.empilhar(pokemonEmpilhar);
                    break;

                case "R": // Remover da pilha (desempilhar)
                    Selection pokemonDesempilhar = pilha.desempilhar();
                    if (pokemonDesempilhar != null) {
                        String name = pokemonDesempilhar.getNameForComparison();
                        System.out.println("(R) " + capitalizeFirstLetter(name));
                    }
                    break;

                default:
                    System.out.println("Comando inválido.");
            }
        }

        // Exibir os Pokémons restantes na pilha
        pilha.mostrar();

        scanf.close(); // Fecha o scanner
    }

    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
