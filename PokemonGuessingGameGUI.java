import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PokemonGuessingGameGUI extends JFrame {

    private PokemonDatabase database;

    public PokemonGuessingGameGUI() {
        super("Pokemon Guessing Game");
        database = new PokemonDatabase();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Adjust the width and height as needed
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Using GridLayout for a vertical layout

        JLabel label = new JLabel("Welcome to the Pokémon Guessing Game!");
        panel.add(label);

        JButton guessByPokedexBtn = new JButton("Guess Pokémon by Pokedex number");
        guessByPokedexBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessPokemonByPokedexNumber();
            }
        });
        panel.add(guessByPokedexBtn);

        JButton guessByTypeBtn = new JButton("Guess Pokémon by type/types");
        guessByTypeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessPokemonByType();
            }
        });
        panel.add(guessByTypeBtn);

        JButton guessByNameBtn = new JButton("Guess Pokémon by image");
        guessByNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessPokemonByName();
            }
        });
        panel.add(guessByNameBtn);

        JButton guessByRegionBtn = new JButton("Guess Pokémon by region");
        guessByRegionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessPokemonByRegion();
            }
        });
        panel.add(guessByRegionBtn);

        JButton guessByGenBtn = new JButton("Guess Pokémon by generation");
        guessByGenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessPokemonByGeneration();
            }
        });
        panel.add(guessByGenBtn);

        JButton guessByRegionAndTypeBtn = new JButton("Guess Pokémon by region and type");
        guessByRegionAndTypeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessPokemonByRandomRegionAndType();
            }
        });
        panel.add(guessByRegionAndTypeBtn);

        JButton lookUpByTypeAndRegionBtn = new JButton("Look up Pokémon by type and region");
        lookUpByTypeAndRegionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookUpPokemonBasedOnTypeAndRegion();
            }
        });
        panel.add(lookUpByTypeAndRegionBtn);

        JButton lookUpByTwoTypesBtn = new JButton("Look up Pokémon by two types");
        lookUpByTwoTypesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookUpTwoTypes();
            }
        });
        panel.add(lookUpByTwoTypesBtn);

        JButton lookUpByNameBtn = new JButton("Look up Pokémon by name");
        lookUpByNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookUpPokemonByName();
            }
        });
        panel.add(lookUpByNameBtn);

        JButton lookUpByPokedexBtn = new JButton("Look up Pokémon by Pokedex number");
        lookUpByPokedexBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookUpPokemonByPokedexNumber();
            }
        });
        panel.add(lookUpByPokedexBtn);

        JButton lookUpAllByRegionBtn = new JButton("Look up all Pokémon from region");
        lookUpAllByRegionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPokemonByRegion();
            }
        });
        panel.add(lookUpAllByRegionBtn); // Changed the action to showPokemonByRegion()

        add(panel);
        setVisible(true);
    }
    private void guessPokemonByPokedexNumber() {
        Pokemon pokemon = database.getRandomPokemon();

        String guess = JOptionPane.showInputDialog(this, "Guess " + pokemon.getName() + "'s Pokedex number:");
        try {
            int guessedNumber = Integer.parseInt(guess);
            if (guessedNumber == pokemon.getPokedexNumber()) {
                JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right. It's " + pokemon.getName() + ".");
            } else {
                JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is " + pokemon.getName() + " with Pokedex number " + pokemon.getPokedexNumber() + ".");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
        }
    }
    private void guessPokemonByType() {
        Pokemon pokemon = database.getRandomPokemon();

        try {
            // Load the image from the file
            BufferedImage image = loadImage(pokemon.getImagePath());

            // Check if the image was loaded successfully
            if (image != null) {
                // Create a JLabel to display the image
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                // Show the image and input dialog
                String guess = JOptionPane.showInputDialog(this, "Guess " + pokemon.getName() + "'s type/types (separated by commas if more than one):", null, JOptionPane.PLAIN_MESSAGE, icon, null, "").toString();
                String[] guessedTypes = guess.split(", ");
                for (int i = 0; i < guessedTypes.length; i++) {
                    guessedTypes[i] = guessedTypes[i].trim().toLowerCase(); // Convert to lowercase and trim spaces
                }
                Arrays.sort(guessedTypes); // Sort guessed types alphabetically

                String[] types = pokemon.getTypes();
                for (int i = 0; i < types.length; i++) {
                    types[i] = types[i].toLowerCase(); // Convert to lowercase
                }
                Arrays.sort(types); // Sort actual types alphabetically

                String guessedTypesString = String.join(", ", guessedTypes);
                String actualTypesString = String.join(", ", types);

                if (Arrays.equals(guessedTypes, types)) {
                    correctGuess(pokemon);
                    //JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right. It's " + actualTypesString + ".", "Correct Guess", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is " + pokemon.getName() + " with type/types: " + actualTypesString + ".", "Incorrect Guess", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void guessPokemonByRegion() {
        Pokemon pokemon = database.getRandomPokemon();

        try {
            // Load the image from the file
            BufferedImage image = loadImage(pokemon.getImagePath());

            // Check if the image was loaded successfully
            if (image != null) {
                // Create a JLabel to display the image
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                // Show the image and input dialog
                String guess = JOptionPane.showInputDialog(this, "Guess the Pokémon's region of " + pokemon.getName() + ":", null, JOptionPane.PLAIN_MESSAGE, icon, null, "").toString();

                if (guess != null && guess.equalsIgnoreCase(pokemon.getRegion())) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right. " + pokemon.getName() + " is from " + pokemon.getRegion(), "Correct Guess", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is from " + pokemon.getRegion() + " region.", "Incorrect Guess", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void guessPokemonByGeneration() {
        Pokemon pokemon = database.getRandomPokemon();

        try {
            // Load the image from the file
            BufferedImage image = loadImage(pokemon.getImagePath());

            // Check if the image was loaded successfully
            if (image != null) {
                // Create a JLabel to display the image
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                // Show the image and input dialog
                String guess = JOptionPane.showInputDialog(this, "Guess the Pokémon's generation of " + pokemon.getName() + ":", null, JOptionPane.PLAIN_MESSAGE, icon, null, "").toString();

                if (guess != null && guess.equalsIgnoreCase(pokemon.getGeneration())) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right. " + pokemon.getName() + " is from " + pokemon.getGeneration(), "Correct Guess", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, wrong guess. The Pokémon is from " + pokemon.getGeneration() + ".", "Incorrect Guess", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void guessPokemonByRandomRegionAndType() {
        String randomRegion = database.getRandomRegion();
        String randomType = database.getRandomType();

        String guess = JOptionPane.showInputDialog(this, "Guess the Pokémon from the following information:\nRegion: " + randomRegion + "\nType: " + randomType);
        if (guess != null && database.isCorrectGuess(guess, randomRegion, randomType)) {
            JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right.");
        } else {
            JOptionPane.showMessageDialog(this, "Sorry, that's not correct.");
        }
    }
    private void lookUpPokemonBasedOnTypeAndRegion() {
        String regionLookUp = JOptionPane.showInputDialog(this, "Enter the region:");
        String typeLookUp = JOptionPane.showInputDialog(this, "Enter the type:");

        List<Pokemon> matchingPokemon = new ArrayList<>();

        // Find all Pokémon matching the given region and type
        for (Pokemon pokemon : database.getPokemons()) {
            if (pokemon.getRegion().equalsIgnoreCase(regionLookUp) && containsType(pokemon, typeLookUp)) {
                matchingPokemon.add(pokemon);
            }
        }

        displayPokemonInGrid(matchingPokemon);
    }
    private void lookUpTwoTypes() {
        String type1 = JOptionPane.showInputDialog(this, "Enter the first type:");
        String type2 = JOptionPane.showInputDialog(this, "Enter the second type:");

        List<Pokemon> matchingPokemon = database.getPokemonWithTypes(type1, type2);

        displayPokemonInGrid(matchingPokemon);
    }
    private void lookUpPokemonByName() {
        String pokemonName = JOptionPane.showInputDialog(this, "Enter the name of the Pokémon:");
        Pokemon pokemon = database.getPokemonByName(pokemonName);

        if (pokemon != null) {
            try {
                // Load the image from the file
                File file = new File("C:/PokemonImage/" + pokemon.getName() + ".png");
                BufferedImage image = null;

                // Check if the image file exists
                if (file.exists()) {
                    image = ImageIO.read(file);
                } else {
                    // If the image file doesn't exist, load a placeholder image
                    image = ImageIO.read(new File("C:/PokemonImage/placeholder.png"));
                }

                // Check if the image was loaded successfully
                if (image != null) {
                    // Create a JLabel to display the image
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);

                    // Create JLabels for displaying Pokémon details
                    JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                    JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                    JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                    JLabel genLabel = new JLabel(pokemon.getGeneration());

                    // Create a JPanel to hold all the components
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    panel.add(imageLabel);
                    panel.add(nameLabel);
                    panel.add(numberLabel);
                    panel.add(typesLabel);
                    panel.add(genLabel);

                    // Show the panel in a JOptionPane
                    JOptionPane.showMessageDialog(this, panel);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemonName);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemonName);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Pokémon found with the name " + pokemonName);
        }
    }
    private boolean containsType(Pokemon pokemon, String type) {
        for (String t : pokemon.getTypes()) {
            if (t.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
    private void showPokemonByRegion() {
        Set<String> regions = new HashSet<>();
        for (Pokemon pokemon : database.getPokemons()) {
            regions.add(pokemon.getRegion());
        }

        JPanel regionPanel = new JPanel();
        regionPanel.setLayout(new GridLayout(regions.size(), 1)); // One column, number of rows based on the number of regions

        for (String region : regions) {
            JButton regionButton = new JButton(region);
            regionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayPokemonByRegion(region);
                }
            });
            regionPanel.add(regionButton);
        }

        JScrollPane scrollPane = new JScrollPane(regionPanel);
        scrollPane.setPreferredSize(new Dimension(300, 400)); // Set preferred size for the scroll pane

        JOptionPane.showMessageDialog(this, scrollPane);
    }
    private void displayPokemonByRegion(String region) {
        List<Pokemon> pokemonByRegion = database.getPokemonByRegion(region);

        JPanel panel = new JPanel(new GridLayout(0, 3)); // 3 columns, dynamic rows

        for (Pokemon pokemon : pokemonByRegion) {
            try {
                // Load the image from the file
                BufferedImage image = loadImage(pokemon.getImagePath());

                // Resize the image to fit better
                Image scaledImage = image.getScaledInstance(125, 125, Image.SCALE_SMOOTH); // Adjust size here

                // Create a JLabel to display the resized image
                ImageIcon icon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(icon);

                // Create JLabels for displaying Pokémon details
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());

                // Create a JPanel to hold all the components
                JPanel pokemonPanel = new JPanel();
                pokemonPanel.setLayout(new BoxLayout(pokemonPanel, BoxLayout.Y_AXIS));
                pokemonPanel.add(imageLabel);
                pokemonPanel.add(nameLabel);
                pokemonPanel.add(numberLabel);
                pokemonPanel.add(typesLabel);
                pokemonPanel.add(genLabel);

                // Add the pokemonPanel to the main panel
                panel.add(pokemonPanel);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
            }
        }

        // Create a scroll pane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(800, 600)); // Set preferred size for the scroll pane

        // Show the scroll pane in a dialog
        JOptionPane.showMessageDialog(this, scrollPane);
    }
    private void lookUpPokemonByPokedexNumber() {
        String input = JOptionPane.showInputDialog(this, "Enter the Pokedex number:");
        try {
            int pokedexNumber = Integer.parseInt(input);
            Pokemon pokemon = database.getPokemonByPokedexNumber(pokedexNumber);
            if (pokemon != null) {
                displayPokemonDetails(pokemon);
            } else {
                JOptionPane.showMessageDialog(this, "No Pokémon found with Pokedex number: " + pokedexNumber);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
        }
    }
    private BufferedImage loadImage(String imagePath) throws IOException {
        File file = new File(imagePath);
        BufferedImage image = null;

        // Check if the image file exists
        if (file.exists()) {
            image = ImageIO.read(file);
        } else {
            // If the image file doesn't exist, load a placeholder image
            image = ImageIO.read(new File("C:/PokemonImage/placeholder.png"));
        }

        return image;
    }
    private void displayPokemonDetails(Pokemon pokemon) {
        try {
            // Load the image from the file
            BufferedImage image = loadImage("C:/PokemonImage/" + pokemon.getName() + ".png");

            // Check if the image was loaded successfully
            if (image != null) {
                // Create a JLabel to display the image
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                // Create JLabels for displaying Pokémon details
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());

                // Create a JPanel to hold all the components
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(imageLabel);
                panel.add(nameLabel);
                panel.add(numberLabel);
                panel.add(typesLabel);
                panel.add(genLabel);

                // Show the panel in a JOptionPane
                JOptionPane.showMessageDialog(this, panel);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
        }
    }
    private void displayPokemonInGrid(List<Pokemon> pokemonList) {
        JPanel panel = new JPanel(new GridLayout(0, 3)); // 3 columns, dynamic rows

        for (Pokemon pokemon : pokemonList) {
            try {
                // Load the image from the file
                BufferedImage image = loadImage("C:/PokemonImage/" + pokemon.getName() + ".png");

                // Resize the image to fit better
                Image scaledImage = image.getScaledInstance(125, 125, Image.SCALE_SMOOTH); // Adjust size here

                // Create a JLabel to display the resized image
                ImageIcon icon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(icon);

                // Create JLabels for displaying Pokémon details
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());

                // Create a JPanel to hold all the components
                JPanel pokemonPanel = new JPanel();
                pokemonPanel.setLayout(new BoxLayout(pokemonPanel, BoxLayout.Y_AXIS));
                pokemonPanel.add(imageLabel);
                pokemonPanel.add(nameLabel);
                pokemonPanel.add(numberLabel);
                pokemonPanel.add(typesLabel);
                pokemonPanel.add(genLabel);

                // Add the pokemonPanel to the main panel
                panel.add(pokemonPanel);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
            }
        }

        // Create a scroll pane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(800, 600)); // Set preferred size for the scroll pane

        // Show the scroll pane in a dialog
        JOptionPane.showMessageDialog(this, scrollPane);
    }
    private void guessPokemonByName() {
        Pokemon pokemon = database.getRandomPokemon();

        try {
            // Load the image from the file
            BufferedImage image = loadImage(pokemon.getImagePath());

            // Check if the image was loaded successfully
            if (image != null) {
                // Create a JLabel to display the image
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                // Show the image and input dialog
                String guess = JOptionPane.showInputDialog(this, "Guess the name of the Pokémon:", null, JOptionPane.PLAIN_MESSAGE, icon, null, "").toString();

                if (guess != null && guess.equalsIgnoreCase(pokemon.getName())) {
                    // If the guess is correct, display the details
                    correctGuess(pokemon);

                } else {
                    incorrectGuess(pokemon);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName());
        }
    }
    private void correctGuess(Pokemon pokemon) {
        try {
            // Load the image from the file
            BufferedImage image = loadImage("C:/PokemonImage/" + pokemon.getName() + ".png");

            // Check if the image was loaded successfully
            if (image != null) {
                // Create a JLabel to display the image
                int desiredWidth = 200; // Adjust the desired width
                int desiredHeight = 200; // Adjust the desired height
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                // Create a JPanel to hold the image label

                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.add(imageLabel, BorderLayout.CENTER);

                // Create a JPanel for displaying the congratulatory message
                JPanel congratsPanel = new JPanel();
                congratsPanel.setBorder(BorderFactory.createTitledBorder("Congratulations! You guessed it right."));
                congratsPanel.setPreferredSize(new Dimension(image.getWidth(), 100)); // Adjust the height as needed
                congratsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Create JLabels for displaying Pokémon details
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());

                // Create a JPanel to hold all the Pokémon details
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
                detailsPanel.add(nameLabel);
                detailsPanel.add(numberLabel);
                detailsPanel.add(typesLabel);
                detailsPanel.add(genLabel);

                // Create a JPanel to hold both the congratulatory message panel and Pokémon details panel
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(congratsPanel, BorderLayout.NORTH);
                panel.add(detailsPanel, BorderLayout.CENTER);
                // Create a JButton for the "Play again" option
                JButton playAgainButton = new JButton("Play again");
                playAgainButton.addActionListener(e -> {
                    // Dispose of the current window
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();

                    // Start a new game
                    guessPokemonByName();
                });


                // Create a JPanel to hold the "Play again" button
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(playAgainButton);

                // Add the button panel to the main panel
                panel.add(buttonPanel, BorderLayout.SOUTH);

                // Show the panel in a JOptionPane
                JOptionPane.showMessageDialog(this, panel, "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void incorrectGuess(Pokemon pokemon) {
        try {
            // Load the image from the file
            BufferedImage image = loadImage("C:/PokemonImage/" + pokemon.getName() + ".png");

            // Check if the image was loaded successfully
            if (image != null) {
                // Create a JLabel to display the image
                int desiredWidth = 200; // Adjust the desired width
                int desiredHeight = 200; // Adjust the desired height
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                // Create a JPanel to hold the image label

                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.add(imageLabel, BorderLayout.CENTER);

                // Create a JPanel for displaying the congratulatory message
                JPanel congratsPanel = new JPanel();
                congratsPanel.setBorder(BorderFactory.createTitledBorder("Sorry, wrong guess. The Pokémon is " + pokemon.getName() + "."));
                congratsPanel.setPreferredSize(new Dimension(image.getWidth(), 100)); // Adjust the height as needed
                congratsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Create JLabels for displaying Pokémon details
                JLabel nameLabel = new JLabel("Name: " + pokemon.getName());
                JLabel numberLabel = new JLabel("Pokedex Number: " + pokemon.getPokedexNumber());
                JLabel typesLabel = new JLabel("Types: " + String.join(", ", pokemon.getTypes()));
                JLabel genLabel = new JLabel(pokemon.getGeneration());

                // Create a JPanel to hold all the Pokémon details
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
                detailsPanel.add(nameLabel);
                detailsPanel.add(numberLabel);
                detailsPanel.add(typesLabel);
                detailsPanel.add(genLabel);

                // Create a JPanel to hold both the congratulatory message panel and Pokémon details panel
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(congratsPanel, BorderLayout.NORTH);
                panel.add(detailsPanel, BorderLayout.CENTER);

                // Create a JButton for the "Play again" option
                JButton playAgainButton = new JButton("Play again");
                playAgainButton.addActionListener(e -> {
                    // Dispose of the current window
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();

                    // Start a new game
                    guessPokemonByName();
                });
                // Create a JPanel to hold the "Play again" button
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(playAgainButton);

                // Add the button panel to the main panel
                panel.add(buttonPanel, BorderLayout.SOUTH);

                // Show the panel in a JOptionPane
                JOptionPane.showMessageDialog(this, panel, "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image for Pokémon: " + pokemon.getName(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
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
