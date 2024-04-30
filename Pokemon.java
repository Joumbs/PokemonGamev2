public class Pokemon {
    private int pokedexNumber;
    private String name;
    private String[] types;
    private String region;
    private String imagePath;
    private String generation;

    // Constructor
    public Pokemon(int pokedexNumber, String name, String[] types, String region, String imagePath, String generation) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.types = types;
        this.region = region;
        this.imagePath = imagePath;
        this.generation = generation;
    }

    // Getters
    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public String[] getTypes() {
        return types;
    }

    public String getRegion() {
        return region;
    }

    public String getImagePath() {
        return imagePath;
    }
    public String getGeneration() {
        return generation;
    }
}
