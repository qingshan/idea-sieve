package com.surfront.sieve.plugin.parser;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class SieveLexer extends FlexAdapter {
    public SieveLexer() {
        super(new _SieveLexer((Reader) null));
    }
}
