

package parentheses;

import static parentheses.BalancedParentheses.isBalanced;


public class Ass2A1320220837 {

    public static void main(String[] args) {
        String[] tests = {
            "(2 + 3) * {4 - 1}",
            "((5 + 2) * 3",
            "({[]})",
            "({[}])"
        };

        for (String test : tests) {
            System.out.println(test + " : " + isBalanced(test));
    }
}
}
