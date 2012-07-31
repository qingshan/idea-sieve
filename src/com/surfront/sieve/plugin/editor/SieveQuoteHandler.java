package com.surfront.sieve.plugin.editor;

import com.surfront.sieve.plugin.parser.SieveTokenTypes;
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;

public class SieveQuoteHandler extends SimpleTokenSetQuoteHandler {
    public SieveQuoteHandler() {
        super(SieveTokenTypes.STRING_LITERAL);
    }
}