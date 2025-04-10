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
    public MainMenu() {
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
                        mediaList.add(new Series(title, rating, year, genre));
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
                        mediaList.add(new Movies(title, rating, year, genre));
                        addedTitles.add(key);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error processing series data: " + seriesData);
                e.printStackTrace();
            }
        }
    }

    public void converToMovie(String media){
        String[] movie = media.split(";");
        String title = movie[0];
        String rating = movie[1];
        String year = movie[2];
        String genre = movie[3];
        Media movieData = new Movies(title,rating, year, genre);
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
                String moviePicked = menu.handleChoices(options);
                converToMovie(moviePicked);

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                Settings.settings();
                break;
            default:
                ui.displayMessage("Your input was incorrect, please type a number between 1 and 5");
                promptChoice();
        }



    }

          /*  ArrayList <Media> found = menu.search2(title, genre,year);
           if (found.isEmpty()){
                ui.displayMessage("No media found matching your search criteria.");*/
            //promptChoice();




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
               System.out.printf(counter + ". Title: %s | Genre: %s | Year: %s%n",
                        media.getTitle(), media.getGenre(), media.getYear());

                String finalThread = media.getTitle() + ";" + media.getGenre() + ";" + media.getYear();
                options.add(finalThread);
                found = true;
            }
            if (!found) {
                System.out.println("No media found matching your search criteria.");
                promptChoice();
            }

        }
        return options;

    }
        public String handleChoices (ArrayList <String> options) {
        int input = ui.promptNumeric("Which media would you like to see? please type number.");

        String moviePicked = options.get(input-1);

            return moviePicked;



    }

        }


            /*public void movieChoices(int choice){
                ui.displayMessage("press 1 to watch now");
                ui.displayMessage("press 2 to add to favorites");
                ui.displayMessage("press 3 to return to main menu");
                int choice = ui.promptNumeric("Choose an option to continue");
                switch(choice){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        search(name, genre, year);
                }
            }

*/



   /* public ArrayList<Media> search2(String name, String genre, String year) {
        ArrayList <Media> foundList = new ArrayList<>();
        boolean found = false;
        for (Media media : mediaList) {
            boolean titleMatch = name.isEmpty() || media.getTitle().toLowerCase().contains(name.toLowerCase());
            boolean genreMatch = genre.isEmpty() || media.getGenre().toLowerCase().contains(genre.toLowerCase());
            boolean yearMatch = year.isEmpty() || media.getYear().equals(year);

            if (titleMatch && genreMatch && yearMatch) {
                foundList.add(media);
                found = true;
            }
        }
        return foundList;
       /* if (!found) {
            System.out.println("No media found matching your search criteria.");
        }*/



