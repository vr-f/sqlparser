package com.github.vr_f.sqlparser.expression.sql;

import com.github.vr_f.sqlparser.expression.Sql;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class List extends Sql {
    private java.util.List<Sql> list = new LinkedList<>();
    private boolean parenthesis = true;

    public List(boolean parenthesis, Sql... expressions) {
        this(expressions);
        this.parenthesis = parenthesis;
    }

    public List(Sql... expressions) {
        this.list.addAll(Arrays.asList(expressions));
    }

    public java.util.List<Sql> getList() {
        return list;
    }

    public Boolean hasParenthesis() {
        return parenthesis;
    }

    public List withParenthesis() {
        return hasParenthesis() ? this : new List(true, getList().toArray(new Sql[0]));
    }

    public List withoutParenthesis() {
        return hasParenthesis() ? new List(false, getList().toArray(new Sql[0])) : this;
    }

    @Override
    public String toString() {
        String expressions = getList()
                .stream()
                .map(Sql::toString)
                .collect(Collectors.joining(", "));

        return hasParenthesis()
                ? "(" + expressions + ")"
                : expressions;
    }
}
