package com.github.vr_f.sqlparser.expression.sql;

import com.github.vr_f.sqlparser.expression.Sql;

public class Variable extends Sql {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "@" + getName();
    }
}
