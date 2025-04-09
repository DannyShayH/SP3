import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Media {

    //media info
    String title;
    String year;
    String genre;
    String rating;
    //user info


    public Media(String title, String rating, String year, String genre){
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        }

        protected void playMedia(String title){

        }

        public void hasWatched(){

            }
        protected void showMediaInfo(){}
        protected void addToFavorites(){}
        public String getTitle() {
        return title;
    }

        public String getYear() {
        return year;
    }

        public String getGenre() {
        return genre;
    }

        @Override
        public String toString() {
        return "Media{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}


