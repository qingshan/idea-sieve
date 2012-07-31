package com.surfront.sieve.plugin.parser;

import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

public class SieveMergingLexer extends MergingLexerAdapter {
    private static final TokenSet MERGING_TOKENS = TokenSet.orSet(SieveTokenTypes.WHITESPACES, SieveTokenTypes.COMMENTS);

    public SieveMergingLexer() {
        super(new SieveLexer(), MERGING_TOKENS);
    }
}
