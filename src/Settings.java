import util.TextUI;

public class Settings {
    static TextUI ui = new TextUI();

    public Settings() {
    }

    public static void settings(){
        ui.displayMessage("1. Switch User");
        ui.displayMessage("2. Log Out");
        int choice = ui.promptNumeric("Choose an option to continue (Numeric)");
        switch(choice) {
            case 1:
               switchAccount();
                break;
            case 2:
                endSession();
                break;
            default:
                ui.displayMessage("please type 1 or 2");
                settings();
        }
    }

    public static void switchAccount() {
        User.setUsername(null);
        Chill chill = new Chill();
        chill.startSequence("Welcome");
   }
    
    public static void endSession(){
        User.setUsername(null);
        ui.displayMessage("You are now logged out!");
    }
}