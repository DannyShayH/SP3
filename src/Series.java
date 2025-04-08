import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import util.TextUI;

public class Series extends Media{
int episode;
int season;
    TextUI ui = new TextUI();
    Scanner scan = new Scanner(System.in);


    public Series (String username, String title, double rating, String year, String genre, int season, int episode) {
        super(username,title,rating,year,genre);
        this.episode = episode;
        this.season = season;

    }


}