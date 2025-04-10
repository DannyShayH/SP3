import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import util.TextUI;
import util.FileIO;

public class MainMenu {

    private static User user;
    private String choice;
    public static TextUI ui = new TextUI();
    private ArrayList<Media> mediaList = new ArrayList<>();
    static FileIO io = new FileIO();
    private ArrayList<String> options;
    private String currentUser;
    private Random randomNum;
    private String FinalPath = "data/userData/" + currentUser + ".csv";
    private ArrayList<String> finalList = new ArrayList<>();

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

    public Movies convertToMovie(String media) {
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
        ui.displayMessage("3. Favorites");
        ui.displayMessage("4. Continue watching");
        ui.displayMessage("5. Go to settings");
        int choice = ui.promptNumeric("Choose an option to continue (Numeric)");
        switch (choice) {
            case 1:
                String title = ui.promptText("Search for title of media ");
                String genre = ui.promptText("Search for genre of media ");
                String year = ui.promptText("Search for year");

                this.options = menu.search(title, genre, year);

                if (options != null && !options.isEmpty()) {
                    String moviePicked = menu.handleChoices(options);
                    if (moviePicked != null) {
                        Media movie = convertToMovie(moviePicked);
                        movie.action();
                    }
                } else {
                    ui.displayMessage("No media found matching your search criteria.");
                    promptChoice();
                }

                break;
            case 2:
                ArrayList<String> recommendations = recommneded();
                String moviePicked = handleChoices(recommendations);
                Movies recMovie = convertToMovie(moviePicked);
                recMovie.action();
                break;
            case 3:
                ArrayList<String> favorite = findFavorites();
                finalList = favoriteCSV(favorite);
                String favPicked = handleChoices(finalList);
                Movies favMovie = convertToMovie(favPicked);
                favMovie.action();
                break;
            case 4:
                ArrayList<String> continueWatching = findAlreadySeen();
                ArrayList<String> watchedList = watchedCSV(continueWatching);
                String watchedPick = handleChoices(watchedList);
                Movies watchedMovie = convertToMovie(watchedPick);
                watchedMovie.action();

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

        String moviePicked = options.get(input - 1);
        ui.displayMessage("You chose " + moviePicked);
        return moviePicked;
    }

    public ArrayList<String> recommneded() {
        HashSet<Integer> recommended = new HashSet<>();

        while (recommended.size() < 5) {
            int random = (int) (Math.random() * mediaList.size());
            recommended.add(random);


        }
        ArrayList<String> movieDisplay = new ArrayList<>();
        int counter = 0;
        for (int n : recommended) {
            counter++;
            ui.displayMessage(counter + ": " + mediaList.get(n).getTitle() + ", " + (mediaList.get(n).getGenre() + ", " + (mediaList.get(n).getYear())));
            movieDisplay.add(mediaList.get(n).getTitle() + ";" + (mediaList.get(n).getGenre() + ";" + (mediaList.get(n).getYear())));
        }
        return movieDisplay;
    }

    public ArrayList<String> findFavorites()  {
        String path = "data/userData/";
        String username = currentUser;
        String finalPath = path + username + ".csv";
        String[] data = new String[3];
        ArrayList<String> isFavorites = new ArrayList<>();

        try {
            // Ensure correct file reading
            Scanner scan = new Scanner(new File(finalPath));

            // Skip header lines if necessary
            // scan.nextLine(); // Uncomment if there is a header to skip

            while (scan.hasNextLine()) {
                String text = scan.nextLine();
                String[] splits = text.split(";");
                if (splits.length < 3) {
                    System.out.println("Skipping invalid line: " + text);
                    continue;  // Skip lines that don't have the expected format
                }
                data[0] = splits[0].trim();
                data[1] = splits[1].trim();
                data[2] = splits[2].trim();

                // Normalize case for comparison
                if (data[1].equalsIgnoreCase("true")) {
                    isFavorites.add(data[0]);
                }
            }

            // Display the favorites
            for (String s : isFavorites) {
                ui.displayMessage(s);
            }

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + finalPath);
            e.printStackTrace();
        }
        return isFavorites;
    }
    /*public void favoriteCSV(ArrayList<String> fav){
        ArrayList<Media> movieTitle = new ArrayList<>();

        for(String f : fav){
            int counter = 0;
            for(Media m : mediaList){
                counter++;
                if(mediaList.contains(f)){
                    ui.displayMessage(mediaList.get(counter).getTitle());
                }
            }
        }
*/
    public ArrayList<String> favoriteCSV(ArrayList<String> fav) {
        ArrayList<String> movieTitle = new ArrayList<>();
        // Iterate over the list of favorite titles
        int counter = 0;
        for (String favoriteTitle : fav) {
            counter++;
            // Loop through the media list to find the corresponding media by title
            for (Media media : mediaList) {
                // Check if the media's title matches the favorite title
                if (media.getTitle().equalsIgnoreCase(favoriteTitle)) {
                    ui.displayMessage( counter + ". " + media.getTitle() + ";" + media.getGenre() + ";" + media.getYear());
                    movieTitle.add(media.getTitle() + ";" + media.getGenre() + ";" + media.getYear());
                }
            }
        }
        return movieTitle;
    }

    public ArrayList<String> watchedCSV(ArrayList<String> fav) {
        ArrayList<String> movieTitle = new ArrayList<>();
        // Iterate over the list of favorite titles
        int counter = -1;
        for (String favoriteTitle : fav) {
            counter++;
            // Loop through the media list to find the corresponding media by title
            for (Media media : mediaList) {
                // Check if the media's title matches the favorite title
                if (media.getTitle().equalsIgnoreCase(favoriteTitle)) {
                    ui.displayMessage( counter + ". " + media.getTitle() + ";" + media.getGenre() + ";" + media.getYear());
                    movieTitle.add(media.getTitle() + ";" + media.getGenre() + ";" + media.getYear());
                }
            }
        }
        return movieTitle;
    }
    public void addToFavorites(String movie){
        finalList.add(movie);
    }
    public ArrayList<String> findAlreadySeen()  {
        String path = "data/userData/";
        String username = currentUser;
        String finalPath = path + username + ".csv";
        String[] data = new String[3];
        ArrayList<String> watched = new ArrayList<>();

        try {
            // Ensure correct file reading
            Scanner scan = new Scanner(new File(finalPath));

            // Skip header lines if necessary
            // scan.nextLine(); // Uncomment if there is a header to skip

            while (scan.hasNextLine()) {
                String text = scan.nextLine();
                String[] splits = text.split(";");
                if (splits.length < 3) {
                    System.out.println("Skipping invalid line: " + text);
                    continue;  // Skip lines that don't have the expected format
                }

                data[0] = splits[0].trim();
                data[1] = splits[1].trim();
                data[2] = splits[2].trim();

                    watched.add(data[0]);
                }

            // Display the favorites
            for (String s : watched) {
                ui.displayMessage(s);
            }

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + finalPath);
            e.printStackTrace();
        }
        return watched;
    }
            }







