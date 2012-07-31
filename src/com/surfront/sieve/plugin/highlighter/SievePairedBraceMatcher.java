package com.surfront.sieve.plugin.highlighter;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

public class SievePairedBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(SieveTokenTypes.LPAREN, SieveTokenTypes.RPAREN, false),
            new BracePair(SieveTokenTypes.LBRACKET, SieveTokenTypes.RBRACKET, false),
            new BracePair(SieveTokenTypes.LBRACE, SieveTokenTypes.RBRACE, true),
    };

    @Override
    public int getCodeConstructStart(PsiFile psiFile, int openingBraceOffset) {
        return openingBraceOffset;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull final IElementType lbraceType, IElementType tokenType) {
        return SieveTokenTypes.WHITE_SPACE == tokenType
                || SieveTokenTypes.COMMENTS.contains(tokenType)
                || tokenType == SieveTokenTypes.SEMICOLON
                || tokenType == SieveTokenTypes.COMMA
                || tokenType == SieveTokenTypes.LPAREN
                || tokenType == SieveTokenTypes.RBRACKET
                || tokenType == SieveTokenTypes.RBRACE
                || null == tokenType;
    }

    public BracePair[] getPairs() {
        return PAIRS;
    }
}
