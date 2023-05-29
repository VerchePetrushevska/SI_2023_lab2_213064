import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    private final SILab2 pom = new SILab2();

    @Test
    void everyBranchTesting1()
    {
        //Test 1
        User user = null;
        List<User> allUsers = new ArrayList<User>();
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> pom.function(user, allUsers));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));



    }

    @Test
    void everyBranchTesting2()
    {
        //Test 2
        User user = new User(null, "bubamaraa", "user@gmail.com");
        List<User> allUsers = new ArrayList<User>();
        for(int i = 0; i<3; i++)
        {
            User user1 = new User("User"+i, "password"+i, "user"+i+"@gmail.com");
            allUsers.add(user1);
        }
        allUsers.add(user);
        //Test for whether the program returns false as expected
        assertEquals(false, pom.function(user, allUsers));
    }
    @Test
    void everyBranchTesting3()
    {
        //Test 3
        //user.getUsername()!=null, Email does not contain '@' or '.', password contains empty spaces
        User user = new User("LoudPickle", "buba maraa", "emaildoesnotcontainspecialcharacters");
        List<User> allUsers = new ArrayList<User>();
        for(int i = 0; i<3; i++)
        {
            User user1 = new User("User"+i, "password"+i, "user"+i+"@gmail.com");
            allUsers.add(user1);
        }
        allUsers.add(user);
        //Test for whether the program returns false as expected
        assertEquals(false, pom.function(user, allUsers));
    }

    @Test
    void everyBranchTesting4()
    {
        //Test 4
        //Test 4: Password length < 8, the user does not belong to the list of users
        User user = new User("LoudPickle", "length", "user@gmail.com");
        List<User> allUsers = new ArrayList<User>();
        for(int i = 0; i<3; i++)
        {
            User user1 = new User("User"+i, "password"+i, "user"+i+"@gmail.com");
            allUsers.add(user1);
        }

        //Test for whether the program returns false as expected
        assertEquals(false, pom.function(user, allUsers));
    }

    @Test
    void everyBranchTesting5()
    {
        //Test 5
        // user.getUsername() == null, email="user@gmail.com"
        // allUsers does not contain the User,
        // password.length() > 8 && password="bubama#raa" meaning it contains a special character
        User user = new User(null, "bubama#raa", "user@gmail.com");
        List<User> allUsers = new ArrayList<User>();
        for(int i = 0; i<3; i++)
        {
            User user1 = new User("User"+i, "password"+i, "user"+i+"@gmail.com");
            allUsers.add(user1);
        }


        //Test for whether the program returns false as expected
        //returns true since same == 0, because the user is not contained inside the list
        assertEquals(true, pom.function(user, allUsers));
    }

    @Test
    void MultipleConditionTest1()
    {
        //User equals null, the first condition is satisfied  ( T || x || x )
        User user = null;
        List<User> allUsers = new ArrayList<User>();
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> pom.function(user, allUsers));
        assertEquals(ex.getMessage(), "Mandatory information missing!");

        //User does not equal null, but his password does  (F || T || x )
        User user1 = new User("Username", null, null);
        ex = assertThrows(RuntimeException.class,() -> pom.function(user1,allUsers));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //Only the user's email equals null (F || F || T)
        User user2 = new User("Username", "password", null);
        ex = assertThrows(RuntimeException.class,() -> pom.function(user2,allUsers));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //None of the conditions are met (F || F || F)
        User user3 = new User("Username", "password", "user@gmail.com");
        assertEquals(false, pom.function(user3, allUsers));
    }

}
