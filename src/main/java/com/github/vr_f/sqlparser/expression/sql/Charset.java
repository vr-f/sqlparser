package com.github.vr_f.sqlparser.expression.sql;

import com.github.vr_f.sqlparser.expression.Sql;

public class Charset extends Sql {
    private Sql charset;

    public Charset(Sql charset) {
        this.charset = charset;
    }

    public Sql getCharset() {
        return charset;
    }

    @Override
    public String toString() {
        return "CHARACTER SET " + getCharset();
    }
}
