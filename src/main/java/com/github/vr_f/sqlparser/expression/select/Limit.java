package com.github.vr_f.sqlparser.expression.select;

import com.github.vr_f.sqlparser.expression.Sql;

public class Limit extends Sql {
    private final Sql offset;
    private final Sql count;

    public Limit(Sql offset, Sql count) {
        this.offset = offset;
        this.count = count;
    }

    public Sql getOffset() {
        return offset;
    }

    public Sql getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "LIMIT " + (
                null == getOffset()
                        ? getCount()
                        : getOffset() + ", " + getCount()
        );
    }
}
