package com.github.vr_f.sqlparser.expression;

import com.github.vr_f.sqlparser.expression.select.*;
import com.github.vr_f.sqlparser.expression.sql.List;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Select extends Sql {
    private final Sql options;
    private final List selectExpr;
    private final From from;
    private final Where where;
    private final GroupBy groupBy;
    private final Having having;
    private final OrderBy orderBy;
    private final Limit limit;
    private final Into into;

    public Select(Sql options, List selectExpr, Into into) {
        this(options, selectExpr, null, null, null, null, null, null, into);
    }

    public Select(Sql options, List selectExpr, From from, Where where, GroupBy groupBy, Having having, OrderBy orderBy, Limit limit, Into into) {
        this.options = options;
        this.selectExpr = selectExpr;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
        this.limit = limit;
        this.into = into;
    }

    public Sql getOptions() {
        return options;
    }

    public List getSelectExpr() {
        return selectExpr;
    }

    public From getFrom() {
        return from;
    }

    public Where getWhere() {
        return where;
    }

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public Having getHaving() {
        return having;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public Limit getLimit() {
        return limit;
    }

    public Into getInto() {
        return into;
    }

    @Override
    public String toString() {
        return "SELECT " + Arrays.stream(new Sql[]{
                getOptions(),
                getSelectExpr(),
                getFrom(),
                getWhere(),
                getGroupBy(),
                getHaving(),
                getOrderBy(),
                getLimit(),
                getInto()
        })
                .filter(Objects::nonNull)
                .map(Sql::toString)
                .map(String::trim)
                .filter(((Predicate<String>) String::isEmpty).negate())
                .collect(Collectors.joining(" "));
    }
}
