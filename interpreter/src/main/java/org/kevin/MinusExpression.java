package org.kevin;

public class MinusExpression extends Expression{

    private final Expression leftException;

    private final Expression rightException;

    public MinusExpression(Expression leftException, Expression rightException) {
        this.leftException = leftException;
        this.rightException = rightException;
    }

    @Override
    public int interpret() {
        return leftException.interpret() - rightException.interpret();
    }

    @Override
    public String toString() {
        return "-";
    }
}
