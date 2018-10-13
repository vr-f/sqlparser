package com.github.vr_f.sqlparser.expression.sql;

import com.github.vr_f.sqlparser.expression.Sql;
import com.github.vr_f.sqlparser.expression.Select;

public class SubSelect extends Sql {
    private Select expr;

    public SubSelect(Select expr) {
        this.expr = expr;
    }

    public Select getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        return String.format("(%s)", getExpr());
    }
}
