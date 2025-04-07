import util.FileIO;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class User {

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
        io.createUser(username, password, age);
        io.allUsernames(username);
    }

    public void setMedia(ArrayList<Media> userMedia) {
        this.userMedia = userMedia;
    }

    private void createUserData(String username, String password, int age){

    }


}
