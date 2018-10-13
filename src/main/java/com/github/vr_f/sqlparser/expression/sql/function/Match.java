package com.github.vr_f.sqlparser.expression.sql.function;

import com.github.vr_f.sqlparser.expression.Sql;
import com.github.vr_f.sqlparser.expression.sql.Function;
import com.github.vr_f.sqlparser.expression.sql.List;

public class Match extends Function {
    private final Sql expression;
    private final String searchModifier;

    public Match(List columns, Sql expression, String searchModifier) {
        super("MATCH", columns);
        this.expression = expression;
        this.searchModifier = searchModifier;
    }

    public Sql getExpression() {
        return expression;
    }

    public String getSearchModifier() {
        return searchModifier;
    }

    @Override
    public String toString() {
        return String.format(
                "%s AGAINST (%s%s)",
                super.toString(),
                getExpression(),
                null != getSearchModifier()
                        ? " " + getSearchModifier()
                        : ""
        );
    }
}
