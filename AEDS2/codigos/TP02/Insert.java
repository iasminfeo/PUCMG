import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Insert {
    private static class Pokemon {
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

        private static int comparacoes = 0; // Contador de comparações

        public Pokemon() {
            // Inicializa atributos
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

        // Método para ler
        public void ler(String id) {
            String input;
            String path = "/tmp/pokemon.csv"; // Caminho do arquivo CSV
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
                "['" + type1  + (type2 != null && !type2.isEmpty() ? "', '" + type2 : "") + "']" + " - "  + abilities + " - " +
                weight + "kg" + " - " + height + "m" + " - " + captureRate + "% - " + 
                isLegendary + " - " + generation +" gen] - " + formattedDate);
        }

        // Método para comparar dois Pokémons
        public static int compareByDateAndName(Pokemon p1, Pokemon p2) {
            comparacoes++; // Incrementa o contador de comparações
            if (p1.captureDate != null && p2.captureDate != null) {
                int dateComparison = p1.captureDate.compareTo(p2.captureDate);
                if (dateComparison != 0) {
                    return dateComparison;
                }
                return p1.name.compareTo(p2.name);
            }
            return p1.captureDate == null ? 1 : -1; // Se p1 é nulo, p1 vem depois
        }

        // Método de Insertion Sort
        public static void insertionSort(List<Pokemon> pokemons) {
            for (int i = 1; i < pokemons.size(); i++) {
                Pokemon key = pokemons.get(i);
                int j = i - 1;

                while (j >= 0 && compareByDateAndName(pokemons.get(j), key) > 0) {
                    pokemons.set(j + 1, pokemons.get(j));
                    j--;
                }
                pokemons.set(j + 1, key);
            }
        }

        public void set(int id, int generation, String name, String description, String type1, String type2, ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary, LocalDate captureDate) {
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
    }

    public static void main(String[] args) {
        List<Pokemon> pokemons = new ArrayList<>();
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
                Pokemon pokemon = new Pokemon();
                pokemon.ler(String.valueOf(idParaBuscar)); // Lê os dados do Pokémon
                pokemons.add(pokemon); // Adiciona à lista
            } catch (NumberFormatException e) {
            }
        }
        scanf.close(); // Fecha o scanner

        long startTime = System.currentTimeMillis(); // Marcar o tempo de início
        Pokemon.insertionSort(pokemons); // Ordena os Pokémons
        long executionTime = System.currentTimeMillis() - startTime; // Calcular tempo de execução

        // Imprime os Pokémons ordenados
        for (Pokemon pokemon : pokemons) {
            pokemon.imprimirPokemon();
        }

        // Grava o log
        try (PrintWriter writer = new PrintWriter("854946_insercao.txt")) {
            writer.println("854946\t" + executionTime + "ms\t" + Pokemon.comparacoes);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Unable to create log file.");
        }
    }
}
