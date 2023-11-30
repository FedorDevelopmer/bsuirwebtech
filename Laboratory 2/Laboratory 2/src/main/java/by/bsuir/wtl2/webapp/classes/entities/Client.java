package by.bsuir.wtl2.webapp.classes.entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Client class represents a client user.
 * It extends the User class and includes additional properties specific to clients.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class Client extends User {

    private int id;
    private boolean isBanned;

    public Client() {
        super();
    }

    public Client(int id,boolean isBanned){
        super();
        this.id = id;
        this.isBanned = isBanned;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
