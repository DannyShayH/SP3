import util.FileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class User {
    String path = "data/userData/";
    private String username;
    private String password;
    private int age;
    protected ArrayList<Media> userMedia;
    FileIO io = new FileIO();

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
        userMedia = new ArrayList<Media>();


    }

    public void createUser() {
        try {
            String finalPath = path + username + ".csv";
            FileWriter writer = new FileWriter(finalPath);
            writer.write("username " + "password " + "age " + "\n");
            writer.write(username + ";" + password + ";" + age+"\n");
            writer.write("Title " + "isFavourite " + "hasWatched " + "\n");
            writer.close();
        } catch (IOException e) {
            // Overvej at lave en text err i TextUI
            System.err.println("problem: " + e.getMessage());
        }
    }
    public void allUsernames() {
        try {
            File file = new File("data/allUsers/allUsers.csv");
            FileWriter writer = new FileWriter("data/allUsers/allUsers.csv", true);

            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();

            }
            writer.write("\n" + username);
            scan.close();
            writer.close();

        } catch (IOException e){
            //Overvej at lave err i TextUI
            System.err.println("FileNotFound: " + e);
        }
    }
    public  String getUsername(){
        return this.username;
    }
    }

