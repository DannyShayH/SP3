import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import util.TextUI;
import java.io.File;
import java.util.Arrays;

public class Movies extends Media{
    TextUI ui = new TextUI();
    Scanner scan = new Scanner(System.in);
    private String currentUser;
    private String FinalPath = "data/userData/" + currentUser + ".csv";
    
    public Movies(String title, String year, String genre, String rating) {
        super(title, rating, year, genre);
        this.currentUser = User.getUsername();
    }
    
    public Movies(String title, String year, String genre, String rating, String currentUser) {
        super(title, rating, year, genre);
        this.currentUser = currentUser;
    }
    @Override
    public void action(){
        ui.displayMessage("1: watch movie"+ "\n" + "2: Return to main menu");
        int input = ui.promptNumeric("What action would you like to take?");
        if(input == 1){
         ui.displayMessage("You are now watching " + title);
         playMedia(title);
        }else if(input == 2){
            ui.displayMessage("You have returned to main menu");
            MainMenu menu = new MainMenu();
            menu.promptChoice();
        }else{
            ui.displayMessage("Invalid input, try again");
            action();
        }
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

        ui.displayMessage(username + " is now watching: " + title);
        hasWatched();
        returnExit();
    }
    public void returnExit(){
        int input = ui.promptNumeric("1: Go to main menu" + "\n" + "2: End program" + "\n" + "3: Add to favorite");
        if(input == 1){
            MainMenu menu = new MainMenu();
            menu.promptChoice();
        }else if(input == 2) {
            ui.displayMessage("Goodbye");
        }else if(input == 3){
            ui.displayMessage(title + " has now been added to favorites");
            MainMenu menu = new MainMenu();
            menu.promptChoice();
        }else{
            ui.displayMessage("Please enter a valid number");
            returnExit();
        }
    }


    @Override
    public void addToFavorites(){
       // MainMenu menu = new MainMenu();
        //menu.addToFavorites(title);
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
    MainMenu menu5 = new MainMenu();
        menu5.promptChoice();
    }
}
