package com.github.vr_f.sqlparser.expression.select;

import com.github.vr_f.sqlparser.expression.Sql;

public class Where extends Sql {
    private Sql condition;

    public Where(Sql condition) {
        this.condition = condition;
    }

    public Sql getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "WHERE " + getCondition();
    }
}
