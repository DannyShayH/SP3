

public class RegisterUser {
    private String username;
    private String password;

    RegisterUser(){

    }
    private void CreateUser(){
        String PotentialUsername = promptText("Please select a username");

        if (PotentialUsername != existingUser) {
            createPassword();
        } else CreateUser();
        }


}


