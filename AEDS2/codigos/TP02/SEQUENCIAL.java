import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class SEQUENCIAL {
    private int id;
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

    public SEQUENCIAL() {
        this.id = 0;
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

    public void ler(String id) {
        String input;
        String path = "/tmp/pokemon.csv"; // Caminho do arquivo CSV
        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            scan.nextLine(); // Ignorar o cabeçalho do arquivo

            boolean found = false;
            while (scan.hasNextLine()) {
                input = scan.nextLine();
                if (id.equals(input.substring(0, input.indexOf(",")))) {
                    found = true;
                    String[] atributos = parseCsvLine(input);
                    set(Integer.parseInt(atributos[0].trim()), 
                        atributos[2].trim(), // name
                        atributos[3].trim(), // description
                        atributos[4].trim(), // type1
                        atributos[5].trim(), // type2
                        parseAbilities(atributos[6].trim()), 
                        parseDouble(atributos[7]), 
                        parseDouble(atributos[8]), 
                        parseInt(atributos[9]), 
                        parseBoolean(atributos[10]), 
                        parseLocalDate(atributos[11]));
                    break;
                }
            }
            scan.close();
            if (!found) {
                System.out.println("Pokémon não encontrado com ID: " + id);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File Not Found.");
        }
    }

    private void set(int id, String name, String description, String type1, String type2,
                     ArrayList<String> abilities, double weight, double height,
                     int captureRate, boolean isLegendary, LocalDate captureDate) {
        this.id = id;
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

    public String getName() {
        return this.name;
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

    private String[] parseCsvLine(String line) {
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        return line.split(regex);
    }

    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value.trim());
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

    public static void main(String[] args) {
        ArrayList<SEQUENCIAL> pokemons = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        long startTime = System.currentTimeMillis();
        int comparacoes = 0;

        // Leitura de IDs até "FIM"
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            try {
                SEQUENCIAL pokemon = new SEQUENCIAL();
                pokemon.ler(input); // Lê os dados do Pokémon pelo ID
                pokemons.add(pokemon); // Adiciona o Pokémon ao ArrayList
            } catch (Exception e) {
                System.out.println("Erro ao ler Pokémon com ID: " + input);
            }
        }

        // Leitura de nomes até "FIM"
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            boolean found = false;
            for (SEQUENCIAL pokemon : pokemons) {
                comparacoes++; // Incrementa a contagem de comparações
                if (pokemon.getName().equalsIgnoreCase(input)) {
                    found = true;
                    break;
                }
            }
            System.out.println(found ? "SIM" : "NAO");
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Criação do arquivo de log
        String matricula = "854946"; // Sua matrícula
        String logFileName = "matricula_sequencial.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName))) {
            writer.println(matricula + "\t" + executionTime + " ms" + "\t" + comparacoes);
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo de log: " + e.getMessage());
        }

        scanner.close(); // Fecha o scanner
    }
}
