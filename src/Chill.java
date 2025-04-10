import util.FileIO;
import util.TextUI;
import java.util.ArrayList;

public class Chill {
    private String username;
    private String password;
    private int age;
    private ArrayList<String> existingUser;
    private String currentUser;

    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    //Is called from Main
    public void startSequence(String msg){
        existingUser = io.readData("data/allUsers/allUsers.csv");
        //Welcome message to Chill
        ui.displayMessage(msg);
        //Gives the user 2 choices, input with numbers (promptNumeric)
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
            this.password = createPassword();
            existingUser.add(this.username);
            User user = new User(username, password, age);
            user.createUser();
            user.allUsernames();
            //Create a constructor that creates a new CSV-file that is ";" seperated.
            login();
        } else {
            ui.displayMessage("Username is already taken..." + "\n" + "Try again");
            createUsername();
        }
    }

    private String createPassword() {
        String password = ui.promptText("Please select a password");
        String confirmPassword = ui.promptText("Confirm the password");

        if (!password.equals(confirmPassword)) {
            //Overvej at lave en .err i TextUI
            System.err.println("Don't match...");
            return createPassword();
        }
        return password;
    }
    
    public void login() {
        String userUsername = ui.promptText("Enter your username");
        
        if (!existingUser.contains((userUsername))) {
            //Overvej at lave en .err i TextUI
            System.err.println(("Username does not exist!"));
            login();
            return;
        }
        
        User.setUsername(userUsername);
        
        String[] user = io.readUser("data/userData/" + userUsername + ".csv", 3);
        this.username = user[0];
        this.password = user[1];
        String tryPassword = ui.promptText("Enter your password");

        // Erstat med vores metode til at check password, ved ik om vi fik lavet den
        if (userUsername.equalsIgnoreCase(this.username) && getPassword(tryPassword)) {
            ui.displayMessage("Welcome " + userUsername + "!");
            this.currentUser = this.username;
        } else {
            ui.displayMessage("Wrong login data");
            login();
            return;
        }
        
        MainMenu menu = new MainMenu();
        menu.promptChoice();
    }
    
    public boolean getPassword(String tryPassword) {
        if (tryPassword.equals(this.password)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void endSession(){
        ui.displayMessage("You are now logged out!");
        User.setUsername(null);
    }
}