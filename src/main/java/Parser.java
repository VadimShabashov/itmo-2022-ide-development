import java.util.ArrayList;
import java.util.HashMap;


public class Parser {
    static HashMap<Character, Integer> operationPriorities = new HashMap<>();

    static {
        operationPriorities.put('+', 2);
        operationPriorities.put('-', 2);
        operationPriorities.put('*', 3);
        operationPriorities.put('/', 3);
        operationPriorities.put('(', 0);
        operationPriorities.put(')', 1);
    }

    public static Expression parse(String string) {
        ArrayList<Character> stackOperation = new ArrayList<>();
        ArrayList<Expression> stackExpression = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            var newSymbol = string.charAt(i);

            if (newSymbol == '(') {
                // Левую скобку всегда добавляем
                stackOperation.add(newSymbol);
            } else if (operationPriorities.containsKey(newSymbol)) {
                // Если знак операции

                while (!stackOperation.isEmpty()) {
                    var previousOperation = stackOperation.get(stackOperation.size() - 1);

                    if (operationPriorities.get(previousOperation) >= operationPriorities.get(newSymbol)) {
                        var rightExpression = stackExpression.remove(stackExpression.size() - 1);
                        var leftExpression = stackExpression.remove(stackExpression.size() - 1);
                        var operation = stackOperation.remove(stackOperation.size() - 1);

                        stackExpression.add(new Binary(leftExpression, operation, rightExpression));
                    } else {
                        break;
                    }
                }

                if (newSymbol == ')') {
                    // Преобразуем посчитанное в скобках выражение в Bracket
                    var bracketExpression = stackExpression.remove(stackExpression.size() - 1);
                    stackOperation.remove(stackOperation.size() - 1);
                    stackExpression.add(new Bracket(bracketExpression));
                } else {
                    stackOperation.add(newSymbol);
                }

            } else if (Character.isDigit(newSymbol)) {
                // Если цифра
                stackExpression.add(new Literal(newSymbol));
            }
            else {
                // Если переменная
                stackExpression.add(new Variable(newSymbol));
            }
        }

        while (!stackOperation.isEmpty()) {
            var rightExpression = stackExpression.remove(stackExpression.size() - 1);
            var leftExpression = stackExpression.remove(stackExpression.size() - 1);
            var operation = stackOperation.remove(stackOperation.size() - 1);

            stackExpression.add(new Binary(leftExpression, operation, rightExpression));
        }

        return stackExpression.get(0);
    }

    public static void main(String[] args) {
        String string = "(a+c)*b+2/7-4*(8-6*9)";
        var expression = parse(string);
        expression.printExpression();
    }
}
