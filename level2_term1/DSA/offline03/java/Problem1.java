package level2_term1.DSA.offline3.java;

import java.util.Scanner;

public class Problem1 {
    public static Stack number = new Stack();
    public static Stack operator = new Stack();;

    public static double calculate(String inputString) {
        boolean mul = false;
        boolean div = false;
        double result = Double.MIN_NORMAL;
        int i = 0;

        while (i < inputString.length()) {
            if (inputString.charAt(i) <= '9' && inputString.charAt(i) >= '0') {
                int in = i, len = 0;
                // i++;
                while (true) {
                    char temp = inputString.charAt(i);
                    if (temp == '(' || temp == ')' || temp == '+' || temp == '-' || temp == '*' || temp == '/'
                            || i + 1 == inputString.length()) {
                        if (i + 1 == inputString.length()) {
                            i++;
                            len++;
                        }
                        String n = (String) inputString.subSequence(in, len + in);
                        double nmb = Double.parseDouble(n);
                        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        // checking for unary op
                        if (in > 1 && inputString.charAt(in - 1) == '-' && inputString.charAt(in - 2) == '('
                                && inputString.charAt(i) == ')')
                            number.push(0.0);
                        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        if (mul == true) {
                            if (operator.pop() == "*") {
                                mul = false;
                                double ans = nmb * (Double) number.pop();
                                // System.out.println(ans);
                                number.push(ans);
                                break;
                            }
                        } else if (div == true) {
                            if (operator.pop() == "/") {
                                div = false;
                                double ans = (Double) number.pop() / nmb;
                                number.push(ans);
                                // System.out.println(ans);
                                break;
                            }
                        } else {
                            number.push(nmb);
                            break;
                        }

                    } else {
                        i++;
                        len++;
                    }
                }
            } else if (inputString.charAt(i) == '(') {
                if (inputString.charAt(i + 1) == ')')
                    throw new Error("Invalid");
                if (inputString.charAt(i + 1) == '-' && inputString.charAt(i + 2) == '(') {
                    number.push(0.0);
                }
                operator.push("(");
                mul = div = false;
                i++;
            } else if (inputString.charAt(i) == '+') {
                operator.push("+");
                i++;
            } else if (inputString.charAt(i) == '-') {
                operator.push("-");
                i++;
            } else if (inputString.charAt(i) == '*') {
                operator.push("*");
                mul = true;
                i++;
            } else if (inputString.charAt(i) == '/') {
                operator.push("/");
                div = true;
                i++;
            } else if (inputString.charAt(i) == ')') {
                // number.print();
                // operator.print();
                i++;
                while (true) {
                    String op = (String) operator.pop();
                    if (op == "(")
                        break;
                    else if (op == "+") {
                        double res = (double) number.pop() + (double) number.pop();
                        number.push(res);
                    } else if (op == "-") {
                        double res = -(double) number.pop() + (double) number.pop();
                        number.push(res);
                    }
                }

                // after the last '('

                if (operator.isEmpty() && i + 1 >= inputString.length()) {
                    double res = (double) number.pop();

                    // checking if the number stack has other values than the reesult

                    if (number.isEmpty()) {
                        result = res;
                        continue;
                    } else
                        throw new Error("Invalid");

                } else {
                    double res = (double) number.pop();
                    // checking if the number stack is empty and the op stack not
                    if (number.isEmpty() && i + 1 >= inputString.length())
                        throw new Error("Invalid");
                    else {
                        number.push(res);
                    }
                }

                if (operator.isEmpty())
                    continue;
                // chechking if there's * or / before a '('

                String op = (String) operator.pop();
                if (op == "*") {
                    mul = false;
                    double ans = (Double) number.pop() * (Double) number.pop();
                    System.out.println(ans);
                    number.push(ans);
                } else if (op == "/") {
                    div = false;
                    double ans = (Double) number.pop() / (Double) number.pop();
                    System.out.println(1 / ans);
                    number.push(1 / ans);
                } else {
                    operator.push(op);
                }
            }
        }
        if (!operator.isEmpty()) {
            // operator.print();
            // number.print();
            result = 0;
            while (true) {
                String op = (String) operator.pop();
                if (op == "(")
                    throw new Error("Invalid");
                else if (op == "+") {
                    // number.print();
                    double res = (double) number.pop();
                    result += res;

                } else if (op == "-") {
                    double res = -(double) number.pop();
                    // number.push(res);
                    result += res;
                }

                // checking if the number stack is empty and the op stack not

                if (operator.isEmpty()) {
                    result += (double) number.pop();
                    // double res = (double) number.pop();
                    if (number.isEmpty()) {
                        // result = res;
                        break;
                    } else
                        throw new Error("Invalid");
                }
            }
        }
        if (result == Double.MIN_NORMAL)
            return (double) number.pop();
        return result;
    }

    public static void main(String[] args) {

        String input = "1-(-(-1))-1";
        String input1 = "(123.45*(678.90/((-2.5)+11.5)-(((80-(19)))*33.25))/20)-(123.45*(678.90/((-2.5)+11.5)-(((80-(19)))*33.25))/20)-1";
        Scanner scanner = new Scanner(System.in);
        try {
            String inputString = scanner.nextLine();
            System.out.println("Valid expression, Computed value: " + Problem1.calculate(inputString));

        } catch (Throwable t) {

            System.out.println("Not valid ");

        }
        scanner.close();
    }
}
