import java.util.ArrayList;

public class LoadMediaFromFIle implements LoadMovies{
    @Override
    public ArrayList<Movies> loadMedia() {
        Movies movie = new Movies("test", "test", 3,3);
        Movies movie1 = new Movies("test", "test", 3,3);
        Movies movie2 = new Movies("test", "test", 3,3);

        ArrayList <Movies> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie2);
        movies.add(movie1);

        return movies;


    }

}
