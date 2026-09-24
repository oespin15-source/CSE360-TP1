package inputValidation;

public class PasswordEvaluator {
	/*******
	 * <p> Title: PasswordEvaluator Class. </p>
	 *
	 * <p> Description: Evaluates a password to determine whether it
	 * satisfies the required password rules, including uppercase and
	 * lowercase letters, numeric digits, special characters, minimum
	 * length, and maximum length. </p>
	 *
	 * @author Obed Espinoza
	 *
	 * @version 1.00 2026-09-23 Updated for TP1 input validation
	 */

    public static String passwordErrorMessage = ""; 	// stores the error message produced during password validation
    public static String passwordInput = "";			// stores the password currently being evaluated
    public static int passwordIndexofError = -1;		// stores the index where the invalid charecter was found

    public static boolean foundUpperCase = false;		// indicates whether an uppercase character was found
    public static boolean foundLowerCase = false;		// indicates whether a lowerase character was found
    public static boolean foundNumericDigit = false;	// indicates whether a numeric digit was found
    public static boolean foundSpecialChar = false;		// indicates whether a special character was found
    public static boolean foundLongEnough = false;		// indicates whether the password is atleast a 

    private static String inputLine = "";				// stores the input being processed by the password evaluator
    private static char currentChar;					// stores the current character being evaluated 
    private static int currentCharNdx;					// stores the current index of the charecter being evaluated
    private static boolean running;						

    private static final int MAX_PASSWORD_LENGTH = 64;	// max number of characters allowed in password
    
    
    /**
    *
    * <p> Method: displayInputState
    * 
    * <p> Description: Displays the current state of the password evaluation process.
    * This includes the password input, the current character position,
    * the password length, and the character currently being evaluated.
    *
    */

    private static void displayInputState() {
        System.out.println(inputLine);
        System.out.println(inputLine.substring(0, currentCharNdx) + "?");
        System.out.println(
                "The password size: " + inputLine.length()
                + " | The currentCharNdx: " + currentCharNdx
                + " | The currentChar: \"" + currentChar + "\""
        );
    }
    
    /**
     * 
     * <p> Method: evaluatePassword
     *
     * <p> Description: Evaluates the supplied password to determine whether it satisfies
     * all required password rules.
     *
     * A valid password must contain at least one uppercase letter,
     * one lowercase letter, one numeric digit, and one special character.
     * The password must also contain at least eight characters and cannot
     * exceed the maximum password length of 64 characters.
     *
     *
     * @param input the password to be evaluated
     * @return an empty string if the password satisfies all requirements;
     *         otherwise, a message describing the password requirements
     *         that were not satisfied
     */

    public static String evaluatePassword(String input) {

        passwordErrorMessage = "";
        passwordIndexofError = 0;
        inputLine = input;
        currentCharNdx = 0;

        if (input.length() <= 0) {
            return "*** Error *** The password is empty!";
        }

        // TP1 maximum textual input check.
        if (input.length() > MAX_PASSWORD_LENGTH) {
            return "*** Error *** The password must be no more than "
                    + MAX_PASSWORD_LENGTH + " characters!";
        }

        currentChar = input.charAt(0);
        passwordInput = input;

        foundUpperCase = false;
        foundLowerCase = false;
        foundNumericDigit = false;
        foundSpecialChar = false;
        foundLongEnough = false;

        running = true;

        while (running) {

            displayInputState();

            if (currentChar >= 'A' && currentChar <= 'Z') {
                System.out.println("Upper case letter found");
                foundUpperCase = true;

            } else if (currentChar >= 'a' && currentChar <= 'z') {
                System.out.println("Lower case letter found");
                foundLowerCase = true;

            } else if (currentChar >= '0' && currentChar <= '9') {
                System.out.println("Digit found");
                foundNumericDigit = true;

            } else if ("~`!@#$%^&*()_-+={}[]|\\:;\"'<>,.?/"
                    .indexOf(currentChar) >= 0) {

                System.out.println("Special character found");
                foundSpecialChar = true;

            } else {
                passwordIndexofError = currentCharNdx;
                return "*** Error *** An invalid character has been found!";
            }

            if (currentCharNdx >= 7) {
                System.out.println("At least 8 characters found");
                foundLongEnough = true;
            }

            currentCharNdx++;

            if (currentCharNdx >= inputLine.length()) {
                running = false;
            } else {
                currentChar = input.charAt(currentCharNdx);
            }

            System.out.println();
        }

        String errMessage = "";

        if (!foundUpperCase)
            errMessage += "Upper case; ";

        if (!foundLowerCase)
            errMessage += "Lower case; ";

        if (!foundNumericDigit)
            errMessage += "Numeric digits; ";

        if (!foundSpecialChar)
            errMessage += "Special character; ";

        if (!foundLongEnough)
            errMessage += "Long Enough; ";

        if (errMessage.isEmpty())
            return "";

        passwordIndexofError = currentCharNdx;
        return errMessage + "conditions were not satisfied";
    }
}
