package inputValidation;

/*******
 * <p> Title: InputValidationTestingAutomation Class. </p>
 *
 * <p> Description: A Java demonstration for automated testing
 * of the input validation used in TP1. uses test cases shown in testcases.pdf and displays
 * a summary of the results. </p>
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
     * <p> Method: main
     * 
     * <p> Descriptio: Runs the TP1 input validation test cases and displays
     * a summary of the test results.
     *
     * @param args command-line arguments
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
     * 
     * <p> Method: perfomrPasswordTestCase
     * 
     * <p> Descriptio:
     * Executes a password validation test case using the
     * PasswordEvaluator used by the application.
     *
     * @param testCase the number identifying the test case
     * @param inputText the password input to be tested
     * @param expectedPass the expected result of the validation
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
     * 
     * <p> Method: performUsernameTestCase
     * 
     * <p> Description
     * Executes a username validation test case using the
     * UserNameRecognizer used by the application.
     *
     * @param testCase the number identifying the test case
     * @param inputText the username input to be tested
     * @param expectedPass the expected result of the validation
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
     * 
     * <p> Method: performEmailTestCase
     * 
     * <p> Description
     * Executes an email validation test case using the
     * EmailAddressRecognizer used by the application.
     *
     * @param testCase the number identifying the test case
     * @param inputText the email address input to be tested
     * @param expectedPass the expected result of the validation
     */
    static void performEmailTestCase(
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

   
    /**
     * 
     * <p> Method: performLengthTestCase
     * 
     * <p> Description
     * Executes a maximum-length validation test case to determine
     * whether the input is within the allowed maximum length.
     *
     * @param testCase the number identifying the test case
     * @param inputText the input text whose length is being tested
     * @param maximumLength the maximum number of characters allowed
     * @param expectedPass the expected result of the validation
     */
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

    /**
     * 
     * <p> Method: performExactLengthTestCase
     * 
     * <p> Description
     * Executes an exact-length validation test case to determine
     * whether the input contains the required number of characters.
     *
     * @param testCase the number identifying the test case
     * @param inputText the input text whose length is being tested
     * @param requiredLength the exact number of characters required
     * @param expectedPass the expected result of the validation
     */
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
     * 
     * <p> Method: evaluateResult
     * 
     * <p> Description
     * Compares the actual validation result with the expected
     * result and updates the test counters.
     *
     * @param actualPass the actual result produced by the validation
     * @param expectedPass the expected result for the test case
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