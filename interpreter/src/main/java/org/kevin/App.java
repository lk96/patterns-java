package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

@Slf4j
public class App {

    public static void main(String[] args) {
        var tokenString = "4 3 2 - 1 + *";
        var stack = new Stack<Expression>();

        var tokenList = tokenString.split(" ");
        for (var s : tokenList) {
            if (isOperator(s)) {
                var rightExpression = stack.pop();
                var leftExpression = stack.pop();
                log.info("popped from stack left: {} right: {}",
                        leftExpression.interpret(), rightExpression.interpret());
                var operator = getOperatorInstance(s, leftExpression, rightExpression);
                log.info("operator: {}", operator);
                var result = operator.interpret();
                var resultExpression = new NumberExpression(result);
                stack.push(resultExpression);
                log.info("push result to stack: {}", resultExpression.interpret());
            } else {
                var i = new NumberExpression(s);
                stack.push(i);
                log.info("push to stack: {}", i.interpret());
            }
        }
        log.info("result: {}", stack.pop().interpret());
    }

    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*");
    }

    public static Expression getOperatorInstance(String s, Expression left, Expression right) {
        switch (s) {
            case "+":
                return new PlusExpression(left, right);
            case "-":
                return new MinusExpression(left, right);
            case "*":
                return new MultiplyExpression(left, right);
            default:
                return new MultiplyExpression(left, right);
        }
    }
}
