package com.github.vr_f.sqlparser.expression.sql;

import com.github.vr_f.sqlparser.expression.Sql;

public class Parenthesis extends Sql {
    private Sql expression;

    public Parenthesis(Sql expression) {
        this.expression = expression;
    }

    public Sql getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "(" + getExpression() + ")";
    }
}
