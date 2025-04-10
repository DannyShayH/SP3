import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import util.TextUI;

public class Series extends Media{
int episode;
int season;
    TextUI ui = new TextUI();
    Scanner scan = new Scanner(System.in);


    public Series (String title, String rating, String year, String genre) {
        super(title,rating,year,genre);
        this.episode = episode;
        this.season = season;

    }
}