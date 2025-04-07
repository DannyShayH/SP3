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

    public void createUser(String username, String password, int age) {
        try {
            String finalPath = path + username + ".csv";
            FileWriter writer = new FileWriter(finalPath);
            writer.write("username " + "password " + "age " + "\n");
            writer.write(username + ";" + password + ";" + age);
            writer.close();
        } catch (IOException e) {
            // Overvej at lave en text err i TextUI
            System.err.println("problem: " + e.getMessage());
        }
    }

    public void allUsernames(String username) {
        try {
            File file = new File("data/allUsers/allUsers.csv");
            FileWriter writer = new FileWriter("data/allUsers/allUsers.csv", true);
            //PrintWriter writer = new PrintWriter(file);

            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();

            }
            writer.write("\n" + username + ";");
            writer.close();
        } catch (IOException e){
            //Overvej at lave err i TextUI
            System.err.println("FileNotFound: " + e);
        }
    }
}