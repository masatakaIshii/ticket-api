package fr.esgi.ticketapi.usecase.users;

import fr.esgi.ticketapi.core.entity.User;

import java.util.ArrayList;
import java.util.List;

public class GetUsers {

    public List<User> execute() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "James", "BERTHO", "jbertho@myges.fr", 1));
        users.add(new User(2, "Masataka", "ISHII", "mishii@myges.fr", 1));
        return users;
    }
}
