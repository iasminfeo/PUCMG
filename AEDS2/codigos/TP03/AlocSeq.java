import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

class celula {
    public Selection elemento;
    public celula prox;

    public celula() {
        this(null);
    }

    public celula(Selection x) {
        this.elemento = x;
        this.prox = null;
    }
}

class lista {
    public celula inicio;
    public celula fim;

    public lista() {
        inicio = new celula();
        fim = inicio;
    }

    public void inserirInicio(Selection pokemon) {
        celula tmp = new celula(pokemon);
        tmp.prox = inicio.prox;
        inicio.prox = tmp;
        if (fim == inicio) {
            fim = tmp;
        }
    }

    public void inserirFim(Selection pokemon) {
        celula novo = new celula(pokemon);
        fim.prox = novo;
        fim = novo;
    }

    public Selection removerInicio() {
        if (inicio.prox == null) {
            return null; // Lista vazia
        }
        celula tmp = inicio.prox;
        inicio.prox = tmp.prox;
        if (inicio.prox == null) {
            fim = inicio; // Lista ficou vazia
        }
        return tmp.elemento;
    }

    public Selection removerFim() {
        if (inicio.prox == null) {
            return null; // Lista vazia
        }
        celula penultimo = inicio;
        while (penultimo.prox != fim) {
            penultimo = penultimo.prox;
        }
        Selection elemento = fim.elemento;
        fim = penultimo;
        fim.prox = null;
        return elemento;
    }

    public void mostrar() {
        int index = 0; // Contador para a impressão
        for (celula i = inicio.prox; i != null; i = i.prox) {
            i.elemento.imprimirPokemon(index);
            index++;
        }
    }

    public int tamanho() {
        int tamanho = 0;
        for (celula i = inicio.prox; i != null; i = i.prox) {
            tamanho++;
        }
        return tamanho;
    }

    public void inserir(Selection pokemon, int pos) throws Exception {
        int tamanho = tamanho();
        if (pos > tamanho || pos < 0) {
            throw new Exception("Erro: Posição inválida");
        } else if (pos == 0) {
            inserirInicio(pokemon);
        } else if (pos == tamanho) {
            inserirFim(pokemon);
        } else {
            celula i = inicio;
            for (int j = 0; j < pos; j++, i = i.prox);
            celula novo = new celula(pokemon);
            novo.prox = i.prox;
            i.prox = novo;
        }
    }

    public Selection remover(int pos) throws Exception {
        int tamanho = tamanho();
        if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro: Posição inválida");
        } else if (pos == 0) {
            return removerInicio();
        } else if (pos == tamanho - 1) {
            return removerFim();
        } else {
            celula i = inicio;
            for (int j = 0; j < pos; j++, i = i.prox);
            celula tmp = i.prox;
            Selection elemento = tmp.elemento;
            i.prox = tmp.prox;
            return elemento;
        }
    }
}

public class AlocSeq {
    public static void main(String[] args) {
        lista pokemonList = new lista();
        Scanner scanf = new Scanner(System.in);
        String input;

        // Lê os IDs dos Pokémons
        while (true) {
            input = scanf.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            Selection pokemon = new Selection();
            pokemon.ler(input);
            pokemonList.inserirFim(pokemon);
        }

        // Lê os comandos de manipulação
        int n = Integer.parseInt(scanf.nextLine());
        for (int i = 0; i < n; i++) {
            String command = scanf.nextLine();
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "II": // Inserir no início
                    Selection pokemonInicio = new Selection();
                    pokemonInicio.ler(parts[1]);
                    pokemonList.inserirInicio(pokemonInicio);
                    break;

                case "I*": // Inserir em posição
                    int posI = Integer.parseInt(parts[1]);
                    Selection pokemonPos = new Selection();
                    pokemonPos.ler(parts[2]);
                    try {
                        pokemonList.inserir(pokemonPos, posI);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "IF": // Inserir no fim
                    Selection pokemonFim = new Selection();
                    pokemonFim.ler(parts[1]);
                    pokemonList.inserirFim(pokemonFim);
                    break;

                case "RI": // Remover no início
                    Selection removedStart = pokemonList.removerInicio();
                    if (removedStart != null) {
                        String name = removedStart.getNameForComparison();
                        System.out.println("(R) " + capitalizeFirstLetter(name));
                    }
                    break;

                case "R*": // Remover em posição
                    int positionR = Integer.parseInt(parts[1]);
                    try {
                        Selection removedPosition = pokemonList.remover(positionR);
                        if (removedPosition != null) {
                            String name = removedPosition.getNameForComparison();
                            System.out.println("(R) " + capitalizeFirstLetter(name));
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "RF": // Remover no fim
                    Selection removedEnd = pokemonList.removerFim();
                    if (removedEnd != null) {
                        String name = removedEnd.getNameForComparison();
                        System.out.println("(R) " + capitalizeFirstLetter(name));
                    }
                    break;

                default:
                    System.out.println("Comando inválido.");
            }
        }

        // Exibir os Pokémons restantes na lista
        pokemonList.mostrar();

        scanf.close(); // Fecha o scanner
    }

    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
