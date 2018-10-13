package com.github.vr_f.sqlparser.expression.sql;

import com.github.vr_f.sqlparser.expression.Sql;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Nary extends Sql {
    private final String separator;
    private final Sql[] parts;

    public Nary(String separator, Sql... parts) {
        this.separator = separator;
        this.parts = parts;
    }

    public Nary(Sql... operands) {
        this.separator = " ";
        this.parts = operands;
    }

    public String getSeparator() {
        return separator;
    }

    public Sql[] getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return Arrays
                .stream(getParts())
                .filter(Objects::nonNull)
                .map(Sql::toString)
                .collect(Collectors.joining(getSeparator()));
    }
}
