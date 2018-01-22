package hr.foi.air.optimix.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 22.1.2018..
 */

/**
 * Test for person authority level.
 * Sets strings to different authority levels.
 * After creating person, it returns authority level in string.
 * Test is passed if strings are equal.
 */
public class PersonTest {

    private String expectedA = "Admin";
    private String expectedT = "Tehničar";
    private String expectedK = "Kemičar";
    private String expectedG = "Greška.";
    Person personA = new Person("Jura", "Baksa", "jurbaksa", "lozinka", 0);
    Person personT = new Person("Jura", "Baksa", "jurbaksa", "lozinka", 1);
    Person personK = new Person("Jura", "Baksa", "jurbaksa", "lozinka", 2);
    Person personG = new Person("Jura", "Baksa", "jurbaksa", "lozinka", 3);

    @Test
    public void getAuthorityString() throws Exception {
        String outputA = personA.getAuthorityString();
        assertEquals(outputA,expectedA);

        String outputT = personT.getAuthorityString();
        assertEquals(outputT,expectedT);

        String outputK = personK.getAuthorityString();
        assertEquals(outputK,expectedK);

        String outputG = personG.getAuthorityString();
        assertEquals(outputG,expectedG);
    }
}