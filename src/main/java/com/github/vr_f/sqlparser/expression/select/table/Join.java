package com.github.vr_f.sqlparser.expression.select.table;

import com.github.vr_f.sqlparser.expression.Sql;

public class Join extends Sql {
    private final Sql reference;
    private final Sql definition;
    private final Sql source;
    private final Sql condition;

    public Join(Sql reference, Sql definition, Sql source) {
        this(reference, definition, source, null);
    }

    public Join(Sql reference, Sql definition, Sql source, Sql condition) {
        this.reference = reference;
        this.definition = definition;
        this.source = source;
        this.condition = condition;
    }

    public Sql getReference() {
        return reference;
    }

    public Sql getDefinition() {
        return definition;
    }

    public Sql getSource() {
        return source;
    }

    public Sql getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s",
                getReference(),
                getDefinition(),
                getSource() + (
                        null != getCondition()
                                ? " " + getCondition()
                                : ""
                )
        );
    }
}
