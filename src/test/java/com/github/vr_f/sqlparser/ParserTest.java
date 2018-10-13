package com.github.vr_f.sqlparser;

import com.github.vr_f.sqlparser.core.Exception;
import com.github.vr_f.sqlparser.core.exception.IllegalKeywordUse;
import com.github.vr_f.sqlparser.expression.Select;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void shouldParse() throws Exception {
        assertNotNull(parseResource("select/simplest.sql"));
        assertNotNull(parseResource("select/with_parsable_keywords.sql"));
        assertNotNull(parseResource("select/with_variables.sql"));
        assertNotNull(parseResource("select/big.sql"));
        assertNotNull(parseResource("select/bigger.sql"));
    }

    @Test (expected = IllegalKeywordUse.class)
    public void shouldNotParse() throws Exception {
        parseResource("select/with_unparsable_keywords.sql");
    }

    protected Select parseResource(String resource) throws Exception {
        String query = new Scanner(this.getClass().getResourceAsStream(resource)).useDelimiter("\\A").next();
        Parser parser = new Parser();
        return parser.parse(new StringReader(query));
    }
}