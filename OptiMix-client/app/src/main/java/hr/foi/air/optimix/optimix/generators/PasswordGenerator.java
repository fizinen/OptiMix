package hr.foi.air.optimix.optimix.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Gloria Babić on 1.12.2017..
 */

/**
 * PasswordGenerator generates password with all small & big letters, numbers and signs.
 */
 public class PasswordGenerator {private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*";

    /**
     * Generates password depending on entered name and surname.
     * @param length of {@code String} to make new password
     * @return password in
     */
    public static String generate(int length) {

        if (length <= 0) {
            return "";
        }

        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        List<String> charCategories = new ArrayList<>(4);
        charCategories.add(LOWER);
        charCategories.add(UPPER);
        charCategories.add(DIGITS);
        charCategories.add(PUNCTUATION);

        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }

}
