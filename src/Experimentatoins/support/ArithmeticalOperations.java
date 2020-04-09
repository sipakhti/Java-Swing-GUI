package Experimentatoins.support;

//import org.jetbrains.annotations.NotNull;


public abstract class ArithmeticalOperations {
    /** Takes a user string and parses it to do basic arithmetic calculations.
     *
      * @param input : String
     * @return String
     */
    private static String input;

    // Input Field setter
    private static void setInput(String input) {
        ArithmeticalOperations.input = input;
    }

    // Primary static method which is accessible to user and accepts the arithmetic String and returns the solved string
    public static String ArthOp(String s) {
        setInput(s);

        isDivide();
        isMultiply();
        isAdd();
        isSubtract();

    return input;
    }



    // Method to do division operations
    private static void isDivide() {
        char[] arr = input.toCharArray();
        long divide = 0;
        String num1 , num2 ;
        String sub;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '/') {

// IF X / Y then:
                num1 = extractNum1(i); // extracts X

                num2 = extractNum2(i); // extracts Y

                sub = num1 + "/" + num2; // Concatenate num1 and num2 to create a matching string to be used a source in string.replace() method
                divide = Long.parseLong(num1)/Long.parseLong(num2); // the actual Arithmetic operation
                input = input.replace(sub,Long.toString(divide)); // replaces the substring which was solved with the answer
                break;
            }
        }
    }

    // Method to do multiplication operations
    private static void isMultiply() {
        char[] arr = input.toCharArray();
        String num1, num2, sub;
        long product = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == '*') {

// IF X * Y then:
                num1 = extractNum1(i); // extracts X

                num2 = extractNum2(i); // extracts Y

                sub = num1 + "*" + num2; // Concatenate num1 and num2 to create a matching string to be used a source in string.replace() method
                product = Integer.parseInt(num1) * Integer.parseInt(num2); // the actual Arithmetic operation
                input = input.replace(sub, Long.toString(product)); // replaces the substring which was solved with the answer
                break;
                }
            }
        }


    // Method to do Addition operations
    private static void isAdd() {
        char[] arr = input.toCharArray();
        String num1, num2, sub;
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '+') {
// IF X + Y then:
                num1 = extractNum1(i); // extracts X

                num2 = extractNum2(i); // extracts Y

                sub = num1 + "+" + num2; // Concatenate num1 and num2 to create a matching string to be used a source in string.replace() method
                sum = Long.parseLong(num1) + Long.parseLong(num2); // the actual Arithmetic operation
                input = input.replace(sub,Long.toString(sum)); // replaces the substring which was solved with the answer
                break;

            }
        }
    }

    // Method to do Subtraction Operations
    private static void isSubtract() {
        char[] arr = input.toCharArray();
        String num1, num2, sub;
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
// IF X - Y then:
                num1 = extractNum1(i); // extracts X

                num2 = extractNum2(i); // extracts Y

                sub = num1 + "-" + num2; // Concatenate num1 and num2 to create a matching string to be used a source in string.replace() method
                sum = Long.parseLong(num1) - Long.parseLong(num2);// the actual Arithmetic operation
                input = input.replace(sub,Long.toString(sum)); // replaces the substring which was solved with the answer
                break;
            }
        }
    }


// Support methods for Arithmetic to extract the two numbers to be operated upon
    private static String extractNum1(int i) {
        char[] arr = input.toCharArray();
        int j = i;
        while (true) {
            j--;

            try {
                if (arr[j] == '+' || arr[j] == '-' || arr[j] == '*' || arr[j] == '/')
                    break;
            } catch (ArrayIndexOutOfBoundsException e) {
                j++;

                break;
            }
        }
        return input.substring(j,i);
    }

    private static String extractNum2(int i){
        char[] arr = input.toCharArray();
        int j = i;
        while (true) {
            j++;
            try {
                if (arr[j] == '+' || arr[j] == '-' || arr[j] == '*' || arr[j] == '/')
                    break;
            } catch (ArrayIndexOutOfBoundsException e) {
                return input.substring(i+1);
            }
        }
        return input.substring(i+1);
    }
}



