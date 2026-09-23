package inputValidation;

/*******
 * <p> Title: InputValidationTestingAutomation Class. </p>
 *
 * <p> Description: A Java demonstration for automated testing
 * of the input validation used in TP1. </p>
 *
 * @author Obed Espinoza
 *
 * @version 1.00 2026-09-23 
 */
public class InputValidationTestingAutomation {

    /** Counter for the number of passed tests. */
    static int numPassed = 0;

    /** Counter for the number of failed tests. */
    static int numFailed = 0;

    /**
     * Runs the TP1 input validation test cases and displays
     * a summary of the results.
     */
    public static void main(String[] args) {

        System.out.println("________________________________________");
        System.out.println("\nTP1 Input Validation Testing Automation");

        // Test 1 - Valid password
        performPasswordTestCase(1, "Aa!15678", true);

        // Test 2 - Password too short
        performPasswordTestCase(2, "A!", false);

        // Test 3 - Missing required password character type
        performPasswordTestCase(3, "aa!15678", false);

        // Test 4 - Password too long
        performPasswordTestCase(
                4,
                "Aa!" + "1".repeat(62),
                false);

        // Test 5 - Valid username
        performUsernameTestCase(5, "Admin123", true);

        // Test 6 - Username too short
        performUsernameTestCase(6, "abc", false);

        // Test 7 - Invalid username format
        performUsernameTestCase(7, "1admin", false);

        // Test 8 - Valid email address
        performEmailTestCase(8, "user@example.com", true);

        // Test 9 - Invalid email address
        performEmailTestCase(9, "user@", false);

        // Test 10 - Email address too long
        performEmailTestCase(
                10,
                "a".repeat(250) + "@example.com",
                false);

        // Test 11 - Name field too long
        performLengthTestCase(
                11,
                "A".repeat(51),
                50,
                false);

        // Test 12 - Invalid invitation code length
        performExactLengthTestCase(
                12,
                "ABC12",
                6,
                false);

        // Test 13 - Valid one-time password
        performPasswordTestCase(13, "Aa!15678", true);

        // Test 14 - Invalid one-time password
        performPasswordTestCase(14, "A!", false);

        /*************** Final Report ***************/

        System.out.println(
                "____________________________________________________________");

        System.out.println();

        System.out.println(
                "Number of tests passed: " + numPassed);

        System.out.println(
                "Number of tests failed: " + numFailed);
    }

    /**
     * Executes a password validation test case using the
     * PasswordEvaluator used by the application.
     */
    private static void performPasswordTestCase(
            int testCase,
            String inputText,
            boolean expectedPass) {

        System.out.println(
                "____________________________________________________________");

        System.out.println(
                "\nPassword Test Case: " + testCase);

        System.out.println(
                "Input: \"" + inputText + "\"");

        String resultText =
                PasswordEvaluator.evaluatePassword(inputText);

        evaluateResult(
                resultText.isEmpty(),
                expectedPass);
    }

    /**
     * Executes a username validation test case using the
     * UserNameRecognizer used by the application.
     */
    private static void performUsernameTestCase(
            int testCase,
            String inputText,
            boolean expectedPass) {

        System.out.println(
                "____________________________________________________________");

        System.out.println(
                "\nUsername Test Case: " + testCase);

        System.out.println(
                "Input: \"" + inputText + "\"");

        String resultText =
                UserNameRecognizer.checkForValidUserName(inputText);

        evaluateResult(
                resultText.isEmpty(),
                expectedPass);
    }

    /**
     * Executes an email validation test case using the
     * EmailAddressRecognizer used by the application.
     */
    private static void performEmailTestCase(
            int testCase,
            String inputText,
            boolean expectedPass) {

        System.out.println(
                "____________________________________________________________");

        System.out.println(
                "\nEmail Test Case: " + testCase);

        System.out.println(
                "Input: \"" + inputText + "\"");

        String resultText =
                EmailAddressRecognizer.checkEmailAddress(inputText);

        evaluateResult(
                resultText.isEmpty(),
                expectedPass);
    }

   
     //Executes a maximum-length validation test case.
    private static void performLengthTestCase(
            int testCase,
            String inputText,
            int maximumLength,
            boolean expectedPass) {

        System.out.println(
                "____________________________________________________________");

        System.out.println(
                "\nLength Test Case: " + testCase);

        System.out.println(
                "Input length: " + inputText.length());

        boolean actualPass =
                inputText.length() <= maximumLength;

        evaluateResult(
                actualPass,
                expectedPass);
    }

    //Executes an exact-length validation test case
    private static void performExactLengthTestCase(
            int testCase,
            String inputText,
            int requiredLength,
            boolean expectedPass) {

        System.out.println(
                "____________________________________________________________");

        System.out.println(
                "\nExact Length Test Case: " + testCase);

        System.out.println(
                "Input: \"" + inputText + "\"");

        System.out.println(
                "Input length: " + inputText.length());

        boolean actualPass =
                inputText.length() == requiredLength;

        evaluateResult(
                actualPass,
                expectedPass);
    }

    /**
     * Compares the actual validation result with the expected
     * result and updates the test counters.
     */
    private static void evaluateResult(
            boolean actualPass,
            boolean expectedPass) {

        if (actualPass == expectedPass) {

            System.out.println(
                    "***Success*** Test case passed.");

            numPassed++;

        } else {

            System.out.println(
                    "***Failure*** Test case failed.");

            numFailed++;
        }
    }
}