public class Bracket implements Expression {
    Expression expression;

    public Bracket(Expression expr) {
        expression = expr;
    }

    public void printExpression() {
            System.out.print("(");
            expression.printExpression();
            System.out.print(")");
    }
}
