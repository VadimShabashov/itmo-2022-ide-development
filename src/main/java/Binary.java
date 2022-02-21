public class Binary implements Expression {
    Expression leftExpression;
    char operation;
    Expression rightExpression;

    public Binary(Expression expr1, char op, Expression expr2) {
        leftExpression = expr1;
        operation = op;
        rightExpression = expr2;
    }

    public void printExpression() {
        leftExpression.printExpression();
        System.out.print(operation);
        rightExpression.printExpression();
    }
}
