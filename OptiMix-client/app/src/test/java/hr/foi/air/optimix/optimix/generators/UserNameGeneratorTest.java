package hr.foi.air.optimix.optimix.generators;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 22.1.2018..
 */

/**
 * Test for username.
 * Set string to usernameGenerator that returns generated username.
 * If returned string is expected, test is passed.
 */
public class UserNameGeneratorTest {

    UserNameGenerator novi = new UserNameGenerator ("Jura","Baksa");

    @Test
    public void generateUsername() throws Exception {
        String expected = "jurbaksa";
        String output = novi.generateUsername();
        assertEquals(output,expected);
    }

    @Test
    public void removeSpecialCharacters() throws Exception {
        String original = "ćaća";
        String expected = "caca";
        String output = "";
        output = novi.removeSpecialCharacters(original);
        assertEquals(output,expected);
    }
}