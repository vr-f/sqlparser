package com.github.vr_f.sqlparser.expression.sql.function;

import com.github.vr_f.sqlparser.expression.Sql;
import com.github.vr_f.sqlparser.expression.sql.Function;
import com.github.vr_f.sqlparser.expression.sql.List;

public class Char extends Function {
    private Sql charset = null;

    public Char(String name, List arguments) {
        super(name, arguments);
    }

    public Char(String name, List arguments, Sql charset) {
        super(name, arguments);
        this.charset = charset;
    }

    public Sql getCharset() {
        return charset;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (%s%s)",
                getName(),
                getArguments(),
                null != getCharset()
                        ? " USING " + getCharset()
                        : ""
        );
    }
}
