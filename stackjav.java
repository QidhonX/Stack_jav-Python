import java.util.*;

public class stackjav {
    
    static int precedence(char ch) {
        if (ch == '+' || ch == '-') return 1;
        if (ch == '*' || ch == '/') return 2;
        return -1;
    }

    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (Character.isWhitespace(c)) continue;

            if (Character.isLetterOrDigit(c)) {
                result.append(c).append(" ");
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop()).append(" ");
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    result.append(stack.pop()).append(" ");
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) result.append(stack.pop()).append(" ");
        return result.toString().trim();
    }

    static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");
        
        System.out.println("\n--- Step by Step Perhitungan ---");
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push(Double.parseDouble(token));
                System.out.println("Push: " + token + " \t Stack: " + stack);
            } else {
                double v2 = stack.pop();
                double v1 = stack.pop();
                double res = 0;
                switch (token.charAt(0)) {
                    case '+': res = v1 + v2; break;
                    case '-': res = v1 - v2; break;
                    case '*': res = v1 * v2; break;
                    case '/': res = v1 / v2; break;
                }
                stack.push(res);
                System.out.println("Calc " + v1 + token + v2 + " = " + res + " \t Stack: " + stack);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String expression = "6 + 9 * ( 4 - 5 )";
        String postfix = infixToPostfix(expression);
        
        System.out.println("Infix  : " + expression);
        System.out.println("Postfix: " + postfix);
        
        double finalResult = evaluatePostfix(postfix);
        System.out.println("\nHasil Akhir: " + finalResult);
    }
}