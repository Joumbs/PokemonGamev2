//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class PokemonGuessingGameGUI extends JFrame {
    private PokemonDatabase database = new PokemonDatabase();

    public PokemonGuessingGameGUI() {
        super("Pokemon Guessing Game");
        this.setDefaultCloseOperation(3);
        this.setSize(600, 400);
        this.setLocationRelativeTo((Component)null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JLabel label = new JLabel("Welcome to the Pokémon Guessing Game!");
        panel.add(label);
        JButton guessByPokedexBtn = new JButton("Guess Pokémon by Pokedex number");
        guessByPokedexBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.guessPokemonByPokedexNumber();
            }
        });
        panel.add(guessByPokedexBtn);
        JButton guessByTypeBtn = new JButton("Guess Pokémon by type/types");
        guessByTypeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.guessPokemonByType();
            }
        });
        panel.add(guessByTypeBtn);
        JButton guessByNameBtn = new JButton("Guess Pokémon by image");
        guessByNameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.guessPokemonByName();
            }
        });
        panel.add(guessByNameBtn);
        JButton guessByRegionBtn = new JButton("Guess Pokémon by region");
        guessByRegionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.guessPokemonByRegion();
            }
        });
        panel.add(guessByRegionBtn);
        JButton guessByGenBtn = new JButton("Guess Pokémon by generation");
        guessByGenBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.guessPokemonByGeneration();
            }
        });
        panel.add(guessByGenBtn);
        JButton guessByRegionAndTypeBtn = new JButton("Guess Pokémon by region and type");
        guessByRegionAndTypeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.guessPokemonByRandomRegionAndType();
            }
        });
        panel.add(guessByRegionAndTypeBtn);
        JButton lookUpByTypeAndRegionBtn = new JButton("Look up Pokémon by type and region");
        lookUpByTypeAndRegionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.lookUpPokemonBasedOnTypeAndRegion();
            }
        });
        panel.add(lookUpByTypeAndRegionBtn);
        JButton lookUpByTwoTypesBtn = new JButton("Look up Pokémon by two types");
        lookUpByTwoTypesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.lookUpTwoTypes();
            }
        });
        panel.add(lookUpByTwoTypesBtn);
        JButton lookUpByType = new JButton("Look up Pokémon type");
        lookUpByType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.lookUpType();
            }
        });
        panel.add(lookUpByType);
        JButton lookUpByNameBtn = new JButton("Look up Pokémon by name");
        lookUpByNameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.lookUpPokemonByName();
            }
        });
        panel.add(lookUpByNameBtn);
        JButton lookUpByPokedexBtn = new JButton("Look up Pokémon by Pokedex number");
        lookUpByPokedexBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.lookUpPokemonByPokedexNumber();
            }
        });
        panel.add(lookUpByPokedexBtn);
        JButton lookUpAllByRegionBtn = new JButton("Look up all Pokémon from region");
        lookUpAllByRegionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PokemonGuessingGameGUI.this.showPokemonByRegion();
            }
        });
        panel.add(lookUpAllByRegionBtn);
        JButton lookUpByKeywordBtn = new JButton("Look up Pokémon by keyword");
        lookUpByKeywordBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = JOptionPane.showInputDialog("Enter keyword:");
                if (keyword != null && !keyword.isEmpty()) {
                    List<Pokemon> pokemonList = PokemonGuessingGameGUI.this.database.getPokemonByNameKeyword(keyword);
                    PokemonGuessingGameGUI.this.displayPokemonInGrid(pokemonList);
                }

            }
        });
        panel.add(lookUpByKeywordBtn);
        this.add(panel);
        this.setVisible(true);
    }

    private void guessPokemonByPokedexNumber() {
        Pokemon pokemon = this.database.getRandomPokemon();
        String guess = JOptionPane.showInputDialog(this, "Guess " + pokemon.getName() + "'s Pokedex number:");

        try {
            int guessedNumber = Integer.parseInt(guess);
            if (guessedNumber == pokemon.getPokedexNumber()) {
                JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right. It's " + pokemon.getName() + ".");
            } else {
                String var10001 = pokemon.getName();
                JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is " + var10001 + " with Pokedex number " + pokemon.getPokedexNumber() + ".");
            }
        } catch (NumberFormatException var4) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
        }

    }

    private void guessPokemonByType() {
        Pokemon pokemon = this.database.getRandomPokemon();

        try {
            BufferedImage image = this.loadImage(pokemon.getImagePath());
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                new JLabel(icon);
                String guess = JOptionPane.showInputDialog(this, "Guess " + pokemon.getName() + "'s type/types (separated by commas if more than one):", (String)null, -1, icon, (Object[])null, "").toString();
                String[] guessedTypes = guess.split(", ");

                for(int i = 0; i < guessedTypes.length; ++i) {
                    guessedTypes[i] = guessedTypes[i].trim().toLowerCase();
                }

                Arrays.sort(guessedTypes);
                String[] types = pokemon.getTypes();

                for(int i = 0; i < types.length; ++i) {
                    types[i] = types[i].toLowerCase();
                }

                Arrays.sort(types);
                String guessedTypesString = String.join(", ", guessedTypes);
                String actualTypesString = String.join(", ", types);
                if (Arrays.equals(guessedTypes, types)) {
                    this.correctGuessType(pokemon);
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is " + pokemon.getName() + " with type/types: " + actualTypesString + ".", "Incorrect Guess", 0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
            }
        } catch (IOException var10) {
            var10.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
        }

    }

    private void guessPokemonByRegion() {
        Pokemon pokemon = this.database.getRandomPokemon();

        try {
            BufferedImage image = this.loadImage(pokemon.getImagePath());
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                new JLabel(icon);
                String guess = JOptionPane.showInputDialog(this, "Guess the Pokémon's region of " + pokemon.getName() + ":", (String)null, -1, icon, (Object[])null, "").toString();
                if (guess != null && guess.equalsIgnoreCase(pokemon.getRegion())) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right. " + pokemon.getName() + " is from " + pokemon.getRegion(), "Correct Guess", 1);
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is from " + pokemon.getRegion() + " region.", "Incorrect Guess", 0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
            }
        } catch (IOException var6) {
            var6.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
        }

    }

    private void guessPokemonByGeneration() {
        Pokemon pokemon = this.database.getRandomPokemon();

        try {
            BufferedImage image = this.loadImage(pokemon.getImagePath());
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                new JLabel(icon);
                String guess = JOptionPane.showInputDialog(this, "Guess the Pokémon's generation of " + pokemon.getName() + ":", (String)null, -1, icon, (Object[])null, "").toString();
                if (guess != null && guess.equalsIgnoreCase(pokemon.getGeneration())) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right. " + pokemon.getName() + " is from " + pokemon.getGeneration(), "Correct Guess", 1);
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is from " + pokemon.getGeneration() + ".", "Incorrect Guess", 0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
            }
        } catch (IOException var6) {
            var6.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
        }

    }

    private void guessPokemonByRandomRegionAndType() {
        String randomRegion = this.database.getRandomRegion();
        String randomType = this.database.getRandomType();
        String guess = JOptionPane.showInputDialog(this, "Guess the Pokémon from the following information:\nRegion: " + randomRegion + "\nType: " + randomType);
        if (guess != null && this.database.isCorrectGuess(guess, randomRegion, randomType)) {
            JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right.");
        } else {
            JOptionPane.showMessageDialog(this, "Sorry, that's not correct.");
        }

    }

    private void lookUpPokemonBasedOnTypeAndRegion() {
        String regionLookUp = JOptionPane.showInputDialog(this, "Enter the region:");
        String typeLookUp = JOptionPane.showInputDialog(this, "Enter the type:");
        List<Pokemon> matchingPokemon = new ArrayList();
        Iterator var4 = this.database.getPokemons().iterator();

        while(var4.hasNext()) {
            Pokemon pokemon = (Pokemon)var4.next();
            if (pokemon.getRegion().equalsIgnoreCase(regionLookUp) && this.containsType(pokemon, typeLookUp)) {
                matchingPokemon.add(pokemon);
            }
        }

        this.displayPokemonInGrid(matchingPokemon);
    }

    private void lookUpTwoTypes() {
        String type1 = JOptionPane.showInputDialog(this, "Enter the first type:");
        String type2 = JOptionPane.showInputDialog(this, "Enter the second type:");
        List<Pokemon> matchingPokemon = this.database.getPokemonWithTypes(type1, type2);
        this.displayPokemonInGrid(matchingPokemon);
    }

    private void lookUpType() {
        String type1 = JOptionPane.showInputDialog(this, "Enter the type:");
        List<Pokemon> matchingPokemon = this.database.getPokemonWithType(type1);
        this.displayPokemonInGrid(matchingPokemon);
    }

    private void lookUpPokemonByName() {
        String pokemonName = JOptionPane.showInputDialog(this, "Enter the name of the Pokémon:");
        Pokemon pokemon = this.database.getPokemonByName(pokemonName);
        if (pokemon != null) {
            try {
                File file = new File("C:/PokemonImage/" + pokemon.getName() + ".png");
                BufferedImage image = null;
                if (file.exists()) {
                    image = ImageIO.read(file);
                } else {
                    image = ImageIO.read(new File("C:/PokemonImage/placeholder.png"));
                }

                if (image != null) {
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);
                    JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                    JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                    JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                    JLabel genLabel = new JLabel(pokemon.getGeneration());
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, 1));
                    panel.add(imageLabel);
                    panel.add(nameLabel);
                    panel.add(numberLabel);
                    panel.add(typesLabel);
                    panel.add(genLabel);
                    JOptionPane.showMessageDialog(this, panel);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemonName);
                }
            } catch (IOException var12) {
                var12.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemonName);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Pokémon found with the name " + pokemonName);
        }

    }

    private boolean containsType(Pokemon pokemon, String type) {
        String[] var3 = pokemon.getTypes();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String t = var3[var5];
            if (t.equalsIgnoreCase(type)) {
                return true;
            }
        }

        return false;
    }

    private void showPokemonByRegion() {
        Set<String> regions = new HashSet();
        Iterator var2 = this.database.getPokemons().iterator();

        while(var2.hasNext()) {
            Pokemon pokemon = (Pokemon)var2.next();
            regions.add(pokemon.getRegion());
        }

        JPanel regionPanel = new JPanel();
        regionPanel.setLayout(new GridLayout(regions.size(), 1));
        Iterator var7 = regions.iterator();

        while(var7.hasNext()) {
            final String region = (String)var7.next();
            JButton regionButton = new JButton(region);
            regionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PokemonGuessingGameGUI.this.displayPokemonByRegion(region);
                }
            });
            regionPanel.add(regionButton);
        }

        JScrollPane scrollPane = new JScrollPane(regionPanel);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        JOptionPane.showMessageDialog(this, scrollPane);
    }

    private void displayPokemonByRegion(String region) {
        List<Pokemon> pokemonByRegion = this.database.getPokemonByRegion(region);
        JPanel panel = new JPanel(new GridLayout(0, 3));
        Iterator var4 = pokemonByRegion.iterator();

        while(var4.hasNext()) {
            Pokemon pokemon = (Pokemon)var4.next();

            try {
                BufferedImage image = this.loadImage(pokemon.getImagePath());
                Image scaledImage = image.getScaledInstance(125, 125, 4);
                ImageIcon icon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(icon);
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());
                JPanel pokemonPanel = new JPanel();
                pokemonPanel.setLayout(new BoxLayout(pokemonPanel, 1));
                pokemonPanel.add(imageLabel);
                pokemonPanel.add(nameLabel);
                pokemonPanel.add(numberLabel);
                pokemonPanel.add(typesLabel);
                pokemonPanel.add(genLabel);
                panel.add(pokemonPanel);
            } catch (IOException var15) {
                var15.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        JOptionPane.showMessageDialog(this, scrollPane);
    }

    private void lookUpPokemonByPokedexNumber() {
        String input = JOptionPane.showInputDialog(this, "Enter the Pokedex number:");

        try {
            int pokedexNumber = Integer.parseInt(input);
            Pokemon pokemon = this.database.getPokemonByPokedexNumber(pokedexNumber);
            if (pokemon != null) {
                this.displayPokemonDetails(pokemon);
            } else {
                JOptionPane.showMessageDialog(this, "No Pokémon found with Pokedex number: " + pokedexNumber);
            }
        } catch (NumberFormatException var4) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
        }

    }

    private BufferedImage loadImage(String imagePath) throws IOException {
        File file = new File(imagePath);
        BufferedImage image = null;
        if (file.exists()) {
            image = ImageIO.read(file);
        } else {
            image = ImageIO.read(new File("C:/PokemonImage/placeholder.png"));
        }

        return image;
    }

    private void displayPokemonDetails(Pokemon pokemon) {
        try {
            BufferedImage image = this.loadImage(pokemon.getImagePath());
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, 1));
                panel.add(imageLabel);
                panel.add(nameLabel);
                panel.add(numberLabel);
                panel.add(typesLabel);
                panel.add(genLabel);
                JOptionPane.showMessageDialog(this, panel);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName());
            }
        } catch (IOException var10) {
            var10.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
        }

    }

    private void displayPokemonInGrid(List<Pokemon> pokemonList) {
        JPanel panel = new JPanel(new GridLayout(0, 3));
        Iterator var3 = pokemonList.iterator();

        while(var3.hasNext()) {
            Pokemon pokemon = (Pokemon)var3.next();

            try {
                BufferedImage image = this.loadImage(pokemon.getImagePath());
                Image scaledImage = image.getScaledInstance(125, 125, 4);
                ImageIcon icon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(icon);
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());
                JPanel pokemonPanel = new JPanel();
                pokemonPanel.setLayout(new BoxLayout(pokemonPanel, 1));
                pokemonPanel.add(imageLabel);
                pokemonPanel.add(nameLabel);
                pokemonPanel.add(numberLabel);
                pokemonPanel.add(typesLabel);
                pokemonPanel.add(genLabel);
                panel.add(pokemonPanel);
            } catch (IOException var14) {
                var14.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        JOptionPane.showMessageDialog(this, scrollPane);
    }

    private void guessPokemonByName() {
        Pokemon pokemon = this.database.getRandomPokemon();

        try {
            BufferedImage image = this.loadImage(pokemon.getImagePath());
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                new JLabel(icon);
                String guess = JOptionPane.showInputDialog(this, "Guess the name of the Pokémon:", (String)null, -1, icon, (Object[])null, "").toString();
                if (guess != null && guess.equalsIgnoreCase(pokemon.getName())) {
                    this.correctGuess(pokemon);
                } else {
                    this.incorrectGuess(pokemon);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName());
            }
        } catch (IOException var6) {
            var6.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
        }

    }

    private void correctGuess(Pokemon pokemon) {
        try {
            BufferedImage image = this.loadImage(pokemon.getImagePath());
            if (image != null) {
                int desiredWidth = 200;
                int desiredHeight = 200;
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.add(imageLabel, "Center");
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(imagePanel, "North");
                JPanel congratsPanel = new JPanel();
                congratsPanel.setBorder(BorderFactory.createTitledBorder("Congratulations! You guessed it right."));
                congratsPanel.setPreferredSize(new Dimension(image.getWidth(), 50));
                congratsPanel.setAlignmentX(0.5F);
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));
                detailsPanel.add(nameLabel);
                detailsPanel.add(numberLabel);
                detailsPanel.add(typesLabel);
                detailsPanel.add(genLabel);
                panel.add(congratsPanel, "North");
                panel.add(detailsPanel, "Center");
                JButton playAgainButton = new JButton("Play again");
                playAgainButton.addActionListener((e) -> {
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();
                    this.guessPokemonByName();
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(playAgainButton);
                panel.add(buttonPanel, "South");
                JOptionPane.showMessageDialog(this, panel, "", 1);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
            }
        } catch (IOException var17) {
            var17.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
        }

    }

    private void incorrectGuess(Pokemon pokemon) {
        try {
            BufferedImage image = this.loadImage("C:/PokemonImage/" + pokemon.getName() + ".png");
            if (image != null) {
                int desiredWidth = 200;
                int desiredHeight = 200;
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.add(imageLabel, "Center");
                JPanel congratsPanel = new JPanel();
                congratsPanel.setBorder(BorderFactory.createTitledBorder("Sorry, wrong guess. The Pokémon is " + pokemon.getName() + "."));
                congratsPanel.setPreferredSize(new Dimension(image.getWidth(), 100));
                congratsPanel.setAlignmentX(0.5F);
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));
                detailsPanel.add(nameLabel);
                detailsPanel.add(numberLabel);
                detailsPanel.add(typesLabel);
                detailsPanel.add(genLabel);
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(congratsPanel, "North");
                panel.add(detailsPanel, "Center");
                JButton playAgainButton = new JButton("Play again");
                playAgainButton.addActionListener((e) -> {
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();
                    this.guessPokemonByName();
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(playAgainButton);
                panel.add(buttonPanel, "South");
                JOptionPane.showMessageDialog(this, panel, "", 1);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
            }
        } catch (IOException var17) {
            var17.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
        }

    }

    private void correctGuessType(Pokemon pokemon) {
        try {
            BufferedImage image = this.loadImage(pokemon.getImagePath());
            if (image != null) {
                int desiredWidth = 200;
                int desiredHeight = 200;
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.add(imageLabel, "Center");
                JPanel congratsPanel = new JPanel();
                congratsPanel.setBorder(BorderFactory.createTitledBorder("Congratulations! You guessed it right."));
                congratsPanel.setPreferredSize(new Dimension(image.getWidth(), 100));
                congratsPanel.setAlignmentX(0.5F);
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));
                detailsPanel.add(nameLabel);
                detailsPanel.add(numberLabel);
                detailsPanel.add(typesLabel);
                detailsPanel.add(genLabel);
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(congratsPanel, "North");
                panel.add(detailsPanel, "Center");
                JButton playAgainButton = new JButton("Play again");
                playAgainButton.addActionListener((e) -> {
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();
                    this.guessPokemonByType();
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(playAgainButton);
                panel.add(buttonPanel, "South");
                JOptionPane.showMessageDialog(this, panel, "", 1);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
            }
        } catch (IOException var17) {
            var17.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", 0);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PokemonGuessingGameGUI();
            }
        });
    }
}
