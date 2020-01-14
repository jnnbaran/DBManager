package dbservlet.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    // must contains one digit from 0-9, lowercase characters, uppercase characters, special symbols in the list "@#$%", length at least 6 characters and maximum of 20

    public PasswordValidator(){
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }


    public boolean validate(final String password){

        matcher = pattern.matcher(password);
        return matcher.matches();

    }



}
