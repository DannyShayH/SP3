package util;
public class Chill {
    TextUI ui = new TextUI();
    Chill(){}

    public void startSequence(String msg){
        //welcome to site msg
        ui.displayMessage(msg);

        int input = ui.promptNumeric("1: register as new user." + "\n" + "2: Login as existing user.");

        if (input = 1){
            registerUser();
        } else if (input = 2){
            login();
        } else {
            ui.displayMessage("invalid input, please try again");
            startSequence("");

        }

    }
}
