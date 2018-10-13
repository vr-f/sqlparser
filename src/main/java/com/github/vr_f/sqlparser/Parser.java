package com.github.vr_f.sqlparser;

import com.github.vr_f.sqlparser.core.Exception;
import com.github.vr_f.sqlparser.core.MySqlVersion;
import com.github.vr_f.sqlparser.core.exception.ReThrown;
import com.github.vr_f.sqlparser.core.keywords.KeywordsLibrary;
import com.github.vr_f.sqlparser.expression.Select;
import java_cup.runtime.Scanner;
import java_cup.runtime.lr_parser;

import java.io.Reader;

public class Parser {
    private boolean debug = false;
    private MySqlVersion version = MySqlVersion.v8_0;

    public boolean isDebug() {
        return debug;
    }

    public Parser setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public MySqlVersion getVersion() {
        return version;
    }

    public Parser setVersion(MySqlVersion version) {
        this.version = version;
        return this;
    }

    public Select parse(Reader reader) throws Exception {
        Scanner lexer = new Lexer(reader);
        KeywordsLibrary keywords = new KeywordsLibrary(getVersion());
        lr_parser parser = new InnerParser(lexer, keywords);

        try {
            if (isDebug()) {
                return (Select) parser.debug_parse().value;
            } else {
                return (Select) parser.parse().value;
            }
        } catch (Exception e) {
            throw e;
        } catch (java.lang.Exception e) {
            throw new ReThrown(e);
        }
    }
}
