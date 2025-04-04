import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

   private String username;
    private String password;
    private String e_mail;
    private int age;
    protected ArrayList<Media> userMedia;

    public User(String username, String password, String e_mail, int age) {
        this.username = username;
        this.password = password;
        this.e_mail = e_mail;
        this.age = age;
        userMedia = new ArrayList<Media>();
    }

    public void setMedia(ArrayList <Media> userMedia){
        this.userMedia = userMedia;
    }

    public boolean getPassword(String tryPassword){
        if (tryPassword == this.password) {
            return true;
        }else{
            return false;
        }
    }
    public Media getFirstMovie(){
        return userMedia.getFirst();
    }

}


/*

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




 */