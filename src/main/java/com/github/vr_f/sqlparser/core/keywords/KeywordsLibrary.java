package com.github.vr_f.sqlparser.core.keywords;

import com.github.vr_f.sqlparser.core.MySqlVersion;

import java.util.Set;

public class KeywordsLibrary {
    private final Set<String> keywords;

    public KeywordsLibrary(MySqlVersion version) {
        keywords = getKeywordsByVersion(version);
    }

    private Set<String> getKeywordsByVersion(MySqlVersion version) {
        switch (version) {
            default:
                return v8_0.data;
        }
    }

    public boolean isKeyword(String name) {
        return keywords.contains(name.toUpperCase());
    }
}
