import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import util.TextUI;
import util.FileIO;

public class MainMenu {

    private static User user;
    private String choice;
    public static TextUI ui = new TextUI();
    private ArrayList<Media> mediaList = new ArrayList<>();
    static FileIO io = new FileIO();
    private ArrayList <String> options;
    private String currentUser;

    public MainMenu() {
        // Get current username and check if it's valid
        this.currentUser = User.getUsername();
        
        mediaList.clear();
        io.allMovies();
        io.allSeries();

        Set<String> addedTitles = new HashSet<>();

        for (String movieData : io.getMovieList()) {
            try {
                String[] parts = movieData.split(";");

                if (parts.length >= 4) {
                    String title = parts[0].trim();
                    String year = parts[1].trim();
                    String genre = parts[2].trim();
                    String rating = parts[3].trim();


                    String key = title + ": " + year;
                    if (!addedTitles.contains(key)) {
                        mediaList.add(new Movies(title, year, genre, rating));
                        addedTitles.add(key);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error processing movie data: " + movieData);
                e.printStackTrace();
            }
        }

        for (String seriesData : io.getSeriesList()) {
            try {
                String[] parts = seriesData.split(";");


                if (parts.length >= 4) {
                    String title = parts[0].trim();
                    String year = parts[1].trim();
                    String genre = parts[2].trim();
                    String rating = parts[3].trim();

                    String key = title + ":" + year;
                    if (!addedTitles.contains(key)) {
                        mediaList.add(new Series(title, rating, year, genre));
                        addedTitles.add(key);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error processing series data: " + seriesData);
                e.printStackTrace();
            }
        }
    }

    public Movies converToMovie(String media){
        String[] movie = media.split(";");
        String title = movie[0];
        String genre = movie[1];
        String year = movie[2];
      //  String rating = movie[3];
        Movies movieData = new Movies(title, genre, year, "Test", this.currentUser);
        return movieData;
    }

    public void choose(String choice) {
        System.out.println("You chose: " + choice);
    }

    public void promptChoice() {

        
        MainMenu menu = new MainMenu();
        ui.displayMessage("1. Search");
        ui.displayMessage("2. Recommended");
        ui.displayMessage("3. Watch Later");
        ui.displayMessage("4. Continue watching");
        ui.displayMessage("5. Go to settings");
        int choice = ui.promptNumeric("Choose an option to continue (Numeric)");
        switch(choice) {
            case 1:
                String title = ui.promptText("Search for title of media ");
                String genre = ui.promptText("Search for genre of media ");
                String year = ui.promptText("Search for year");

                this.options = menu.search(title, genre, year);
                 
                if (options != null && !options.isEmpty()) {
                    String moviePicked = menu.handleChoices(options);
                    if (moviePicked != null) {
                        Media movie = converToMovie(moviePicked);
                        movie.hasWatched();
                    }
                } else {
                    ui.displayMessage("No media found matching your search criteria.");
                    promptChoice();
                }
                break;
            case 2:
                ui.displayMessage("Recommended feature not implemented yet");
                promptChoice();
                break;
            case 3:
                ui.displayMessage("Watch Later feature not implemented yet");
                promptChoice();
                break;
            case 4:
                ui.displayMessage("Continue watching feature not implemented yet");
                promptChoice();
                break;
            case 5:
                Settings.settings();
                break;
            default:
                ui.displayMessage("Your input was incorrect, please type a number between 1 and 5");
                promptChoice();
        }
    }

    public ArrayList<String> search(String name, String genre, String year) {
        ArrayList<String> options = new ArrayList<>();
        boolean found = false;
        int counter = 0;

        for (Media media : mediaList) {
            boolean titleMatch = name.isEmpty() || media.getTitle().toLowerCase().contains(name.toLowerCase());
            boolean genreMatch = genre.isEmpty() || media.getGenre().toLowerCase().contains(genre.toLowerCase());
            boolean yearMatch = year.isEmpty() || media.getYear().equals(year);

            if (titleMatch && genreMatch && yearMatch) {
                counter++;
                System.out.printf(counter + ". Title: %s | Genre: %s | Year: %s | Rating: %s%n",
                        media.getTitle(), media.getGenre(), media.getYear(), media.getRating());

                String finalThread = media.getTitle() + ";" + media.getGenre() + ";" + media.getYear();
                options.add(finalThread);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No media found matching your search criteria.");
            return new ArrayList<>();
        }

        return options;
    }
    
    public String handleChoices(ArrayList<String> options) {
        
        int input = ui.promptNumeric("Which media would you like to see? please type number.");
        
        if (input < 1 || input > options.size()) {
            ui.displayMessage("Invalid selection. Please choose a number between 1 and " + options.size());
            return handleChoices(options);
        }
        
        String moviePicked = options.get(input-1);
        return moviePicked;
    }
}
