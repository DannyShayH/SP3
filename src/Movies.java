import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import util.TextUI;

public class Movies extends Media{
    TextUI ui = new TextUI();
    Scanner scan = new Scanner(System.in);
    public Movies(String title, String year, String genre, String rating,String currentUser) {
        super(title,rating,year,genre);
    }


    @Override
    public void hasWatched() {
        try {
            String path = "data/userData/";
            System.out.println(User.getUsername());
            String finalPath = path + User.getUsername() + ".csv";
            FileWriter writer = new FileWriter(finalPath, true);
            Scanner scan = new Scanner(finalPath);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
            }


            writer.write("\n" + title + "; " + "false;" + " true; ");
            writer.close();
        } catch (IOException e) {
            // Overvej at lave en text err i TextUI
            System.err.println("problem: " + e.getMessage());
         }
    }
        @Override
        public void playMedia(String title){
        ui.displayMessage(User.getUsername() + " is now watching: " + title);
        hasWatched();
        ui.displayMessage("the movie:" + title + " has been added to your has watched");
        }

        @Override
    public void addToFavorites(){
        String[] data = new String[3];
        String sample = null;
        while(!sample.contains(title)); {
            sample = scan.nextLine();
        }
        String[] splits = sample.split(";");
        data[0] = splits [0];
        data[1] = "true;";
        data[2] = splits [2];
        String finalText = data[0]+data[1]+data[2];
        //writer.write(finaltext);
        }


}


//The Godfather; 1972; Crime, Drama; 9,2;

