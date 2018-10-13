package com.github.vr_f.sqlparser.core.exception;

import com.github.vr_f.sqlparser.core.Exception;

public class ReThrown extends Exception {
    private java.lang.Exception originalException;

    public ReThrown(java.lang.Exception originalException) {
        this.originalException = originalException;
    }

    public java.lang.Exception getOriginalException() {
        return originalException;
    }
}
