public class Pokemon {
    private int pokedexNumber;
    private String name;
    private String[] types;
    private String region;
    private String imagePath;
    private String generation;

    public Pokemon(int pokedexNumber, String name, String[] types, String region, String imagePath, String generation) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.types = types;
        this.region = region;
        this.imagePath = imagePath;
        this.generation = generation;
    }

    public int getPokedexNumber() {
        return this.pokedexNumber;
    }

    public String getName() {
        return this.name;
    }

    public String[] getTypes() {
        return this.types;
    }

    public String getRegion() {
        return this.region;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getGeneration() {
        return this.generation;
    }
}
