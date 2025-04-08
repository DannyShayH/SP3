package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    String path = "data/userData/";

    public void saveData(ArrayList<String> list, String path, String header) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(header + "\n");
            for (String s : list) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
    }

    public ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();   //  "tess, 0"
                data.add(line);
            }
        } catch (FileNotFoundException e) {
        }
        return data;
    }

    public String[] readData(String path, int length) {
        String[] data = new String[length];
        File file = new File(path);
        try {
            //new scanner created
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header;

            int i = 0;  //counter
            while (scan.hasNextLine()) {
                String line = scan.nextLine();  //String line bliver instansieret som det scaneren har læst
                data[i] = line;                    //information tilføjes til et array
                i++;                             //counter går op
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return data;
    }
    public String[] readUser(String path, int length) {
        String[] data = new String[length];
        File file = new File(path);
        try {
            //new scanner created
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header;

         String text = scan.nextLine();
         String[] splits = text.split(";");
         data[0] = splits[0];
         data[1] = splits[1];
         data[2] = splits[2];

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return data;
    }
    public ArrayList<String> allMovies = new ArrayList<>();

    public void allMovies() {
        File file = new File("data/Media/film.csv");

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String movie = scan.nextLine();
                allMovies.add(movie);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public ArrayList<String> allSeries = new ArrayList<>();

    public void allSeries() {
        File file = new File("data/Media/series.csv");

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String movie = scan.nextLine();
                allSeries.add(movie);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public ArrayList<String> getMovieList() {
        return allMovies;
    }

    public ArrayList<String> getSeriesList() {
        return allSeries;
    }
}
