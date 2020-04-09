package Experimentatoins.support;

//import org.jetbrains.annotations.NotNull;


public abstract class ArithmeticalOperations {
    /** Takes a user string and parses it to do basic arithmetic calculations.
     *
      * @param input : String
     * @return String
     */
    private static String input;

    public static void setInput(String input) {
        ArithmeticalOperations.input = input;
    }

    public static String ArthOp(String s) {
        setInput(s);
        int divideCount = 0, multiplyCount = 0, addCount = 0, subtractCount = 0;

        isDivide();
        isMultiply();
        isAdd();
        isSubtract();

    return input;
    }



    private static void isDivide() {
        char[] arr = input.toCharArray();
        long divide = 0;
        String num1 , num2 ;
        String sub;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '/') {

                num1 = extractNum1(i);


                num2 = extractNum2(i);
                sub = num1 + "/" + num2;
                divide = Long.parseLong(num1)/Long.parseLong(num2);
                input = input.replace(sub,Long.toString(divide));
                break;
            }
        }
    }

    private static void isMultiply() {
        char[] arr = input.toCharArray();
        String num1, num2, sub;
        long product = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == '*') {


                num1 = extractNum1(i);

                num2 = extractNum2(i);
                product = Integer.parseInt(num1) * Integer.parseInt(num2);
                sub = num1 + "*" + num2;
                input = input.replace(sub, Long.toString(product));
                break;
                }
            }
        }



    private static void isAdd() {
        char[] arr = input.toCharArray();
        String num1, num2, sub;
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '+') {

                num1 = extractNum1(i);

                num2 = extractNum2(i);
                sub = num1 + "+" + num2;
                sum = Long.parseLong(num1) + Long.parseLong(num2);
                input = input.replace(sub,Long.toString(sum));
                break;

            }
        }
    }

    private static void isSubtract() {
        char[] arr = input.toCharArray();
        String num1, num2, sub;
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {

                num1 = extractNum1(i);

                num2 = extractNum2(i);

                sub = num1 + "-" + num2;
                sum = Long.parseLong(num1) - Long.parseLong(num2);
                input = input.replace(sub,Long.toString(sum));
                break;
            }
        }
    }

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



