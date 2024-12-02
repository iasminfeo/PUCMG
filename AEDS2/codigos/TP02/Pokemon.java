import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pokemon {
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

    public Pokemon() {
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

    public Pokemon(int id, int generation, String name, String description, String type1, String type2, ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary, LocalDate captureDate) {
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

    // ---------------------------------- Métodos GET
    public int getId() { return this.id; }
    public int getGeneration() { return this.generation; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public String getType1() { return this.type1; }
    public String getType2() { return this.type2; }
    public ArrayList<String> getAbilities() { return this.abilities; }
    public double getWeight() { return this.weight; }
    public double getHeight() { return this.height; }
    public int getCaptureRate() { return this.captureRate; }
    public Boolean getIsLegendary() { return this.isLegendary; }
    public LocalDate getCaptureDate() { return this.captureDate; }

    // ---------------------------------- Métodos SET
    public void setId(int id) { this.id = id; }
    public void setGeneration(int generation) { this.generation = generation; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setType1(String type1) { this.type1 = type1; }
    public void setType2(String type2) { this.type2 = type2; }
    public void setAbilities(ArrayList<String> abilities) { this.abilities = abilities; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setHeight(double height) { this.height = height; }
    public void setCaptureRate(int captureRate) { this.captureRate = captureRate; }
    public void setIsLegendary(Boolean isLegendary) { this.isLegendary = isLegendary; }
    public void setCaptureDate(LocalDate captureDate) { this.captureDate = captureDate; }

    // Método set para definir múltiplos atributos de uma vez
    public void set(int id, int generation, String name, String description,
                    String type1, String type2, ArrayList<String> abilities,
                    double weight, double height, int captureRate,
                    Boolean isLegendary, LocalDate captureDate) {
        setId(id);
        setGeneration(generation);
        setName(name);
        setDescription(description);
        setType1(type1);
        setType2(type2);
        setAbilities(abilities);
        setWeight(weight);
        setHeight(height);
        setCaptureRate(captureRate);
        setIsLegendary(isLegendary);
        setCaptureDate(captureDate);
    }

    public Pokemon clone() {
        Pokemon clone = new Pokemon();
        clone.setId(getId());
        clone.setGeneration(getGeneration());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setType1(getType1());
        clone.setType2(getType2());
        clone.setAbilities(getAbilities());
        clone.setWeight(getWeight());
        clone.setHeight(getHeight());
        clone.setCaptureRate(getCaptureRate());
        clone.setIsLegendary(getIsLegendary());
        clone.setCaptureDate(getCaptureDate());
        return clone;
    }

    // Método para ler
    public void ler(String id) {
        String input;
        String path = "tmp/pokemon.csv"; // Caminho do arquivo CSV
        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);

            scan.nextLine(); // Ignorar o cabeçalho do arquivo

            if (scan.hasNextLine()) { // Testar se há mais alguma linha
                boolean found = false;
                while (scan.hasNextLine() && !found) {
                    input = scan.nextLine();
                    if (id.equals(input.substring(0, input.indexOf(",")))) { // Usar vírgula como separador
                        found = true;
                        String[] atributos = parseCsvLine(input);

                        // Chama o método set com os atributos lidos
                        set(
                            Integer.parseInt(atributos[0].trim()), 
                            Integer.parseInt(atributos[1].trim()), 
                            atributos[2].trim(), 
                            atributos[3].trim(), 
                            atributos[4].trim(), // type1
                            atributos[5].trim(), // type2
                            parseAbilities(atributos[6].trim()), 
                            parseDouble(atributos[7]), 
                            parseDouble(atributos[8]), 
                            parseInt(atributos[9]), 
                            parseBoolean(atributos[10]), 
                            parseLocalDate(atributos[11])
                        );

                        // Verifica se peso e altura são válidos
                        if (getWeight() < 0) {
                            setWeight(0.0);
                        }
                        if (getHeight() < 0) {
                            setHeight(0.0);
                        }
                    } // end if
                } // end while
                if (!found) {
                    System.out.println("Pokémon não encontrado.");
                } // end if
            } // end if
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File Not Found.");
        } // end catch
    } // end ler()

    private String[] parseCsvLine(String line) {
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        return line.split(regex);
    }

    private static ArrayList<String> parseAbilities(String abilitiesString) {
        abilitiesString = abilitiesString.replace("\"", "").replace("[", "").replace("]", "").trim(); // Remove aspas e colchetes
        String[] abilitiesArray = abilitiesString.split(",\\s*"); // Separa por vírgulas
        ArrayList<String> abilitiesList = new ArrayList<>();
        for (String ability : abilitiesArray) {
            abilitiesList.add(ability.trim());
        }
        return abilitiesList;
    }

    // Métodos auxiliares para tratamento de atributos
    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0; // Valor padrão se não fornecido
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
          
            return 0.0; // Valor padrão em caso de erro
        }
    }

    private int parseInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0; // Valor padrão se não fornecido
        }
        return Integer.parseInt(value.trim());
    }

    private boolean parseBoolean(String value) {
        return value != null && Integer.parseInt(value.trim()) > 0; // Considera 0 como false
    }

    private LocalDate parseLocalDate(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Retorna nulo se não fornecido
        }
        try {
            return LocalDate.parse(value.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
          
            return null; // Retorna nulo em caso de erro
        }
    }

    // Método para imprimir
    public void imprimirPokemon() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = captureDate != null ? captureDate.format(formatter) : "N/A"; // Formatar a data ou retornar "N/A" se for nula
    
        System.out.println("[#" + id + " -> " + name + ": " + description + " - " + 
            "['" + type1  + (type2.isEmpty() ? "" : "', '" + type2) + "']" +" - "  + abilities + " - " +
            weight + "kg" + " - " + height + "m" + " - " + captureRate + "% - " + 
            isLegendary + " - " + generation +" gen] - " + formattedDate);
    }

    public static void main(String[] args) {
        Pokemon pokemon = new Pokemon();
        Scanner scanf = new Scanner(System.in);
        
        while (true) {
            String input = scanf.nextLine();

            // Verifica se a entrada é "FIM"
            if (input.equalsIgnoreCase("FIM")) {

                break; // Sai do loop
            }

            // Tenta converter a entrada em número inteiro
            try {
                int idParaBuscar = Integer.parseInt(input); // Lê o ID do Pokémon que você quer buscar
                pokemon.ler(String.valueOf(idParaBuscar)); // Lê os dados do Pokémon
                pokemon.imprimirPokemon(); // Imprime os dados do Pokémon
            } catch (NumberFormatException e) {
            }
        }
        scanf.close(); // Fecha o scanner
    }
}
