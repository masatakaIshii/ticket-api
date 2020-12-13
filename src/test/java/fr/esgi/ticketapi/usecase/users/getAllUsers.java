package fr.esgi.ticketapi.usecase.users;

import fr.esgi.ticketapi.core.entity.User;
import fr.esgi.ticketapi.core.usecase.users.GetUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class getAllUsers {

    GetUsers getAllUsers = new GetUsers();
    List<User> resultTest = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        resultTest = new ArrayList<>();
        resultTest.add(new User(1,"James","BERTHO","jbertho@myges.fr",1));
        resultTest.add(new User(2,"Masataka","ISHII","mishii@myges.fr",1));
    }

    @Test
    public void should_return_all_users() {
        List<User> allUsers = getAllUsers.getAllUsers();
        assertEquals(resultTest, allUsers);
    }
}
