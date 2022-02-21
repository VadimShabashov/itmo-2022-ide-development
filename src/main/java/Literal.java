public class Literal implements Expression {
    int literal;

    public Literal(char number) {
        literal = Character.getNumericValue(number);
    }

    public void printExpression() {
        System.out.print("(Lit " + Integer.toString(literal) + ")");
    }
}
