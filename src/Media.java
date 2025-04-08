public class Media {
    private String title;
    private String genre;
    private String year;

    public Media(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

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
