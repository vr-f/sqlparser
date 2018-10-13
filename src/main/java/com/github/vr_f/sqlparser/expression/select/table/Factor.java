package com.github.vr_f.sqlparser.expression.select.table;

import com.github.vr_f.sqlparser.expression.Sql;
import com.github.vr_f.sqlparser.expression.sql.List;
import com.github.vr_f.sqlparser.expression.sql.SubSelect;

public class Factor extends Sql {
    public static final int TABLE = 1;
    public static final int SUBQUERY = 2;
    public static final int REFERENCES = 3;

    private int form;
    private String name;
    private List partition;
    private String alias;
    private List columns;
    private List indexHintList;
    private SubSelect subSelect;
    private List references;

    public Factor(String name, List partition, String alias, List indexHintList) {
        this.name = name;
        this.partition = partition;
        this.alias = alias;
        this.indexHintList = indexHintList;
        this.form = TABLE;
    }

    public Factor(SubSelect subSelect, String alias, List columns) {
        this.subSelect = subSelect;
        this.alias = alias;
        this.columns = columns;
        this.form = SUBQUERY;
    }

    public Factor(List references) {
        this.references = references;
        this.form = REFERENCES;
    }

    public int getForm() {
        return form;
    }

    public String getName() {
        return name;
    }

    public List getPartition() {
        return partition;
    }

    public String getAlias() {
        return alias;
    }

    public List getColumns() {
        return columns;
    }

    public List getIndexHintList() {
        return indexHintList;
    }

    public SubSelect getSubSelect() {
        return subSelect;
    }

    public List getReferences() {
        return references;
    }

    @Override
    public String toString() {
        switch (getForm()) {
            case REFERENCES:
                return getReferences().toString();

            case SUBQUERY:
                return getSubSelect() + (
                        null != getAlias() ? " AS " + getAlias() : ""
                ) + (
                        null != getColumns() ? " " + getColumns() : ""
                );

            default:
                return getName() + (
                        null != getPartition() ? " PARTITION " + getPartition() : ""
                ) + (
                        null != getAlias() ? " AS " + getAlias() : ""
                ) + (
                        null != getIndexHintList() ? " " + getIndexHintList() : ""
                );
        }
    }
}
