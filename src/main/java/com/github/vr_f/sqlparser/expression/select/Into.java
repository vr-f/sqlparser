package com.github.vr_f.sqlparser.expression.select;

import com.github.vr_f.sqlparser.expression.Sql;
import com.github.vr_f.sqlparser.expression.sql.Charset;
import com.github.vr_f.sqlparser.expression.sql.List;

public class Into extends Sql {
    public static final int OUTFILE = 1;
    public static final int DUMPFILE = 2;
    public static final int VARLIST = 3;

    private int type;
    private final Sql file;
    private final Charset charset;
    private final Sql options;
    private List uservars;

    public Into(Sql file, Charset charset, Sql options) {
        this.file = file;
        this.charset = charset;
        this.options = options;
        this.type = OUTFILE;
    }

    public Into(Sql file) {
        this(file, null, null);
        this.type = DUMPFILE;
    }

    public Into(List userVars) {
        this(null, null, null);
        this.uservars = userVars;
        this.type = VARLIST;
    }

    public int getType() {
        return type;
    }

    public Sql getFile() {
        return file;
    }

    public Charset getCharset() {
        return charset;
    }

    public Sql getOptions() {
        return options;
    }

    public List getUservars() {
        return uservars;
    }

    @Override
    public String toString() {
        switch (getType()) {
            case OUTFILE:
                return String.format(
                        "INTO OUTFILE %s%s%s",
                        getFile(),
                        (" " + getCharset()).replaceAll("\\s+$", ""),
                        (" " + getOptions()).replaceAll("\\s+$", "")
                );
            case VARLIST:
                return "INTO " + getUservars();
            default:
                return "INTO DUMPFILE " + getFile();
        }
    }
}
