import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import util.TextUI;
import util.FileIO;

public class MainMenu {

    private static User user;
    private String choice;
    static TextUI ui = new TextUI();
    private ArrayList<Media> mediaList = new ArrayList<>();
    static FileIO io = new FileIO();

    public MainMenu() {
        mediaList.clear();
        io.allMovies();
        io.allSeries();

        Set<String> addedTitles = new HashSet<>();

        for (String movieData : io.getMovieList()) {
            try {
                String[] parts = movieData.split(";");

                if (parts.length >= 3) {
                    String title = parts[0].trim();
                    String year = parts[1].trim();
                    String genre = parts[2].trim();

                    String key = title + ":" + year;
                    if (!addedTitles.contains(key)) {
                        mediaList.add(new Media(title, genre, year));
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

                if (parts.length < 3) {
                    parts = seriesData.split(",");
                }

                if (parts.length >= 3) {
                    String title = parts[0].trim();
                    String year = parts[1].trim();
                    String genre = parts[2].trim();

                    String key = title + ":" + year;
                    if (!addedTitles.contains(key)) {
                        mediaList.add(new Movies(username, title, genre, year, "genre"));
                        addedTitles.add(key);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error processing series data: " + seriesData);
                e.printStackTrace();
            }
        }
    }

    public void choose(String choice) {
        System.out.println("You choose choice: " + choice);
    }

    public static void promptChoice() {
        ui.displayMessage("1. Search");
        ui.displayMessage("2. Recommended");
        ui.displayMessage("3. Watch Later");
        ui.displayMessage("4. Continue watching");
        ui.displayMessage("5. Go to settings");
        System.out.println("Hello, " + (user != null ? user : "Guest") + "!");
        int choice = ui.promptNumeric("Choose an option to continue (Numeric)");
        if (choice == 1) {
            String title = ui.promptText("Search for title of media ");
            String genre = ui.promptText("Search for genre of media ");
            String year = ui.promptText("Search for year");

            MainMenu menu = new MainMenu();
            menu.search(title, genre, year);
        }
    }

    public void search(String name, String genre, String year) {
        boolean found = false;
        for (Media media : mediaList) {
            boolean titleMatch = name.isEmpty() || media.getTitle().toLowerCase().contains(name.toLowerCase());
            boolean genreMatch = genre.isEmpty() || media.getGenre().toLowerCase().contains(genre.toLowerCase());
            boolean yearMatch = year.isEmpty() || media.getYear().equals(year);

            if (titleMatch && genreMatch && yearMatch) {
                System.out.printf("Found - Title: %s | Genre: %s | Year: %s%n",
                        media.getTitle(), media.getGenre(), media.getYear());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No media found matching your search criteria.");
        }
    }

    public static void main(String[] args) {
        promptChoice();
    }
}