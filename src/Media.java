import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Media {

    //media info
    String title;
    int year;
    String genre;
    double rating;
    //user info
    String username;


    public Media(String username, String title, double rating, int year, String genre){
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.username = username;
        }

        protected void playMedia(String title){

        }

        public void hasWatched(){

            }
        protected void showMediaInfo(){}
    protected void addToFavorites(){}

}


