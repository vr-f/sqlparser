package com.github.vr_f.sqlparser.expression.select;

import com.github.vr_f.sqlparser.expression.Sql;
import com.github.vr_f.sqlparser.expression.sql.List;

public class From extends Sql {
    private List tableReferences;

    public From(List tableReferences) {
        this.tableReferences = tableReferences;
    }

    public List getTableReferences() {
        return tableReferences;
    }

    @Override
    public String toString() {
        return "FROM " + getTableReferences();
    }
}
