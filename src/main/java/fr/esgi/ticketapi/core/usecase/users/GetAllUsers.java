package fr.esgi.ticketapi.core.usecase.users;

import fr.esgi.ticketapi.core.entity.User;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsers {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"James","BERTHO","jbertho@myges.fr"));
        users.add(new User(2,"Masataka","ISHII","mishii@myges.fr"));

        return users;
    }
}
