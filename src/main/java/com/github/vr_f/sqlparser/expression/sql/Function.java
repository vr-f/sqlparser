package com.github.vr_f.sqlparser.expression.sql;

import com.github.vr_f.sqlparser.expression.Sql;

public class Function extends Sql {
    private final String name;
    private final List arguments;

    public Function(String name) {
        this(name, new List());
    }

    public Function(String name, List arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public Function(String name, Sql... arguments) {
        this(name, new List(arguments));
    }

    public String getName() {
        return name;
    }

    public List getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return getName() + (
                getArguments().hasParenthesis()
                        ? getArguments()
                        : " " + getArguments()
        );
    }
}
