import java.util.ArrayList;

public class Register {
    private String username;
    private String password;
    private ArrayList<String> existingUser;
    Register(){

    }
    private void CreateUser(){
        String potentialUsername = promptText("Please select a username");

        if (potentialUsername != existingUser) {
            potentialUsername = this.username;
            createPassword();
        } else {
            System.out.println("Username is already taken..." + "\n" + "Try again");
            CreateUser();
        }

    }

    private String createPassword() {
        String password = promptText("Please select a password");
        String confirmPassword = promptText("Confirm the password");

        if (!password.equals(confirmPassword)) {
            System.err.println("Don't match...");
            createPassword();
        }
        return password;
    }

}



