public class Variable implements Expression {
    char variableName;

    public Variable(char variable) {
        variableName = variable;
    }

    public void printExpression() {
        System.out.print("(Var " + variableName + ")");
    }
}
