package com.surfront.sieve.plugin;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.surfront.sieve.plugin.highlighter.SieveSyntaxHighlighter;

public class SieveLanguage extends Language {
    public SieveLanguage() {
        super("Sieve", "application/sieve");
        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
            protected SyntaxHighlighter createHighlighter() {
                return new SieveSyntaxHighlighter();
            }
        });
    }

    public boolean isCaseSensitive() {
        return true;
    }
}
