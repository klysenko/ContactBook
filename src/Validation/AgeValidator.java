package Validation;

import exceptions.InvalidAgeException;

public class AgeValidator {
    private static final int MAX_ALLOWED_AGE = 100;
    private static final int MIN_ALLOWED_AGE = 0;

    public void validate(int age) throws InvalidAgeException {
        if (age > MAX_ALLOWED_AGE || age < MIN_ALLOWED_AGE) {
            throw new InvalidAgeException("Invalid age: " + age
                    + " (Age should be from " + MIN_ALLOWED_AGE + " to " + MAX_ALLOWED_AGE + " years)");
        }
    }
}
