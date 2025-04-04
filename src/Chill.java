import util.FileIO;
import util.TextUI;
import java.util.ArrayList;

public class Chill {
    private String username;
    private String password;
    private int age;
    private ArrayList<String> existingUser;
    
    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    //Bliver kaldt fra Main

    public void startSequence(String msg){
        //welcome to site msg Måske hej velkommen til Chill
        ui.displayMessage(msg);
        //Giver to valgmuligheder, hvor brugeren kan svare med en int.
        int input = ui.promptNumeric("1: register as new user." + "\n" + "2: Login as existing user.");
        //
        if (input == 1){
            createUsername();
        } else if (input == 2){
            login();
        } else {
            ui.displayMessage("invalid input, please try again");
            startSequence("");

        }

    }
    private void createUsername(){
        this.age = ui.promptNumeric("Please enter your age");
        String potentialUsername = ui.promptText("Please select a username");

        if (!existingUser.contains(potentialUsername)) {
            this.username = potentialUsername;
            password = createPassword();
            this.password = password;
            User user = new User(username,password,age);
            //Lav en konstruktor der laver en ny CSV-fil som er ";" seperaret.
            login();
        } else {
            System.out.println("Username is already taken..." + "\n" + "Try again");
            createUsername();
        }

    }

    private String createPassword() {
        String password = ui.promptText("Please select a password");
        String confirmPassword = ui.promptText("Confirm the password");

        if (!password.equals(confirmPassword)) {
            //Overvej at lave en .err i TextUI
            System.err.println("Don't match...");
            createPassword();
        }
        return password;
    }
    
    
    private void login() {
        String userUsername = ui.promptText("Enter your username");

        if (!existingUser.contains((userUsername))) {
            System.err.println(("Username does not exist!"));
            login();
        }

        String userPassword = ui.promptText("Enter your password");

        // Erstat med vores metode til at check password, ved ik om vi fik lavet den
        if (userUsername.equalsIgnoreCase(this.username) && userPassword.equals(this.password)) {
            ui.displayMessage("Welcome " + userUsername + "!");
        } else {
            ui.displayMessage("Wrong logindata");
            startSequence("");
        }
    }

}

/*

   
   
}





 */
