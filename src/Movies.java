import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import util.TextUI;
import java.io.File;

public class Movies extends Media{
    TextUI ui = new TextUI();
    Scanner scan = new Scanner(System.in);
    private String currentUser;
    
    public Movies(String title, String year, String genre, String rating) {
        super(title, rating, year, genre);
        this.currentUser = User.getUsername();
    }
    
    public Movies(String title, String year, String genre, String rating, String currentUser) {
        super(title, rating, year, genre);
        this.currentUser = currentUser;
    }

    @Override
    public void hasWatched() {
        try {
            String path = "data/userData/";
            String username = User.getUsername();

            
            String finalPath = path + username + ".csv";
            
            File userFile = new File(finalPath);
//            if (!userFile.exists()) {
//                ui.displayMessage("Error: User data file not found: " + finalPath);
//                return;
//            }
        
            boolean alreadyWatched = false;
            Scanner scanner = new Scanner(userFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(title) && line.contains("true")) {
                    alreadyWatched = true;
                    break;
                }
            }
            scanner.close();
        
            if (!alreadyWatched) {
                FileWriter writer = new FileWriter(finalPath, true);
                writer.write("\n" + title + "; " + "false;" + " true; ");
                writer.close();
                ui.displayMessage("The movie: " + title + " has been added to your watched list");
            } else {
                ui.displayMessage("The movie: " + title + " is already in your watched list");
            }
        } catch (IOException e) {
            System.err.println("Problem updating watched list: " + e.getMessage());
        }
    }
    
    @Override
    public void playMedia(String title){
        String username = User.getUsername();
        if (username == null || username.isEmpty()) {
            ui.displayMessage("Error: No user is currently logged in");
            return;
        }
        
        ui.displayMessage(username + " is now watching: " + title);
        hasWatched();
    }

    @Override
    public void addToFavorites(){
        try {
            String path = "data/userData/";
            String username = User.getUsername();
            

            
            String finalPath = path + username + ".csv";
            
            File userFile = new File(finalPath);

            Scanner fileScanner = new Scanner(userFile);
            StringBuilder fileContent = new StringBuilder();
            boolean foundTitle = false;
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains(title)) {
                    String[] parts = line.split(";");
                    if (parts.length >= 3) {
                        line = title + "; true;" + parts[2] + ";";
                    }
                    foundTitle = true;
                }
                fileContent.append(line).append("\n");
            }
            fileScanner.close();
            
            FileWriter writer = new FileWriter(finalPath);
            writer.write(fileContent.toString());
            writer.close();
            
            if (foundTitle) {
                ui.displayMessage("Added " + title + " to favorites");
            } else {
                ui.displayMessage("Could not find " + title + " in your watched list");
            }
        } catch (IOException e) {
            System.err.println("Problem updating favorites: " + e.getMessage());
        }
    }
}
