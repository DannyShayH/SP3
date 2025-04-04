import java.util.ArrayList;

public class LoadDataFromFile implements LoadDataFromStorage{
    @Override
    public ArrayList<User> loadDataFromSource() {
        ArrayList <User> users = new ArrayList <User>();
        User niels = new User("Test", "Niels123",  3);
        User Poul = new User("Test", "Niels123",  3);
        User Rasmus = new User("Test", "Niels123",  3);
        users.add(niels);
        users.add(Poul);
        users.add(Rasmus);
        return users;
    }

}
