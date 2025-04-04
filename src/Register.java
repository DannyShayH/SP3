import java.util.ArrayList;
import util.FileIO;
import util.TextUI;

public class Register {
    private String username;
    private String password;
    private ArrayList<String> existingUser;
    private TextUI ui;

    public Register(){

    }
    private void CreateUser(){
        String potentialUsername = ui.promptText("Please select a username");

        if (!potentialUsername.equals(existingUser)) {
            potentialUsername = this.username;
            createPassword();
        } else {
            System.out.println("Username is already taken..." + "\n" + "Try again");
            CreateUser();
        }

    }

    private String createPassword() {
        String password = ui.promptText("Please select a password");
        String confirmPassword = ui.promptText("Confirm the password");

        if (!password.equals(confirmPassword)) {
            System.err.println("Don't match...");
            createPassword();
        }
        return password;
    }

}



