package Lab6.Ex1.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Eveler on 28.05.2015.
 */
public class ActiveUsers implements Serializable {
    private static final long serialVersionUID = -1365528682717889170L;
    private ArrayList<User> users;

    public ActiveUsers() {
        users = new ArrayList<User>();
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public int size() {
        return users.size();
    }

    public boolean contains(User user) {
        return users.contains(user);
    }

    public User get(int index) {
        return users.get(index);
    }

    @Override
    public String toString() {
        String toString = "users:\n";
        for (User user : users) {
            toString = toString.concat(user.toString()+"\n");
        }
        return toString;
    }
}
