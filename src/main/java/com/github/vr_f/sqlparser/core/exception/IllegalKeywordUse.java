package com.github.vr_f.sqlparser.core.exception;

import com.github.vr_f.sqlparser.core.Exception;

public class IllegalKeywordUse extends Exception {
    private final String keyword;

    public IllegalKeywordUse(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public String getMessage() {
        return String.format("Illegal use of reserved keyword <%s>, wrap it with `` if necessary", getKeyword());
    }
}
