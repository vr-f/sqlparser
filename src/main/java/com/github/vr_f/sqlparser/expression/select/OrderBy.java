package com.github.vr_f.sqlparser.expression.select;

import com.github.vr_f.sqlparser.expression.Sql;
import com.github.vr_f.sqlparser.expression.sql.List;

public class OrderBy extends Sql {
    private final List conditions;
    private final boolean rollup;

    public OrderBy(List conditions, boolean rollup) {
        this.conditions = conditions;
        this.rollup = rollup;
    }

    public List getConditions() {
        return conditions;
    }

    public boolean isRollup() {
        return rollup;
    }

    @Override
    public String toString() {
        return "ORDER BY " + getConditions() + (isRollup() ? " WITH ROLLUP" : "");
    }
}
