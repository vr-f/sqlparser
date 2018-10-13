package com.github.vr_f.sqlparser.expression.select;

import com.github.vr_f.sqlparser.expression.Sql;

public class SourceEntry extends Sql {
    private final Sql expr;
    private final String alias;

    public SourceEntry(Sql expr) {
        this(expr, null);
    }

    public SourceEntry(Sql expr, String alias) {
        this.expr = expr;
        this.alias = alias;
    }

    public Sql getExpr() {
        return expr;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        String alias = null == getAlias()
                ? ""
                : " AS " + getAlias();
        return getExpr() + alias;
    }
}
