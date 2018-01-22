package hr.foi.air.optimix.core;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

/**
 * Class Input works with inputs.
 */
public class Input {

    EditText editText;
    Pattern pattern;
    String errorMessage;

    public static final String PASSWORD_PATTERN = "[a-zA-Z\\d!@#$%&*]{5,45}";
    public static final String TEXT_MAIN_PATTERN = "[A-Za-z]{3,45}";



    public Input(EditText editText, String pattern, String errorMessage) {
        this.editText = editText;
        this.pattern = Pattern.compile(pattern);
        this.errorMessage = errorMessage;
    }

    /**
     * Checking if input is valid
     * @return boolean with valid information.
     */
    public boolean isValid() {
        return this.pattern.matcher(editText.getText().toString()).matches();
    }

    /**
     * Sets error.
     */
    public void setError() {
        editText.setError(this.errorMessage);
    }

    /**
     * Gets EditText element
     * @return {@code EditText} that function gets.
     */
    public EditText getEditText() {
        return this.editText;
    }

    /**
     * Boolean function returns if input is equal with text.
     * @param input {@code Input} input class object.
     * @return {@code boolean} info of equality.
     */
    public boolean equals(Input input) {
        boolean equal = true;
        if(!TextUtils.equals(this.editText.getText().toString(),
                input.getEditText().getText().toString())) {
            input.setError();
            equal = false;
        }
        return equal;
    }

    /**
     * Validates inputs.
     * @param inputs{@code List<Input>} List of inputs of class Input.
     * @return {@code boolean} info of validity.
     */
    public static boolean validate(List<Input> inputs) {
        boolean valid = true;
        for (Input i: inputs) {
            if(!i.isValid()) {
                valid = false;
                i.setError();
            }
        }
        return valid;
    }
}
