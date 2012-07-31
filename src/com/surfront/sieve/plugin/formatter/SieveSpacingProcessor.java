package com.surfront.sieve.plugin.formatter;

import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.surfront.sieve.plugin.parser.SieveElementTypes;
import com.surfront.sieve.plugin.SieveNodeVisitor;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

public class SieveSpacingProcessor extends SieveNodeVisitor {
    private static final Spacing LINEBREAK_SPACING = Spacing.createSpacing(0, 0, 1, true, 1);
    private static final Spacing COMMON_SPACING = Spacing.createSpacing(1, 1, 0, true, 1);
    private static final Spacing NO_SPACING = Spacing.createSpacing(0, 0, 0, false, 0);
    private Spacing result;
    private final IElementType type1;
    private final IElementType type2;

    public SieveSpacingProcessor(ASTNode parent, ASTNode child1, ASTNode child2) {
        type1 = child1.getElementType();
        type2 = child2.getElementType();
        visit(parent);
    }

    public Spacing getResult() {
        return result;
    }

    public void visitFile(final ASTNode node) {
        if (SieveElementTypes.COMMANDS.contains(type1)
                || SieveElementTypes.COMMANDS.contains(type2)) {
            result = LINEBREAK_SPACING;
        }
    }

    public void visitBlock(final ASTNode node) {
        if (SieveElementTypes.COMMANDS.contains(type1)
                || SieveElementTypes.COMMANDS.contains(type2)) {
            result = LINEBREAK_SPACING;
        }
    }

    public void visitControlCommand(final ASTNode node) {
        result = COMMON_SPACING;
    }

    public void visitActionCommand(ASTNode node) {
        if (type2 == SieveTokenTypes.SEMICOLON) {
            result = NO_SPACING;
        }
    }

    public void visitAllofTest(ASTNode node) {
        if (type1 == SieveTokenTypes.ALLOF_KEYWORD) {
            result = COMMON_SPACING;
        }
    }

    public void visitAnyofTest(ASTNode node) {
        if (type1 == SieveTokenTypes.ANYOF_KEYWORD) {
            result = COMMON_SPACING;
        }
    }

    public void visitNotTest(ASTNode node) {
        if (type1 == SieveTokenTypes.NOT_KEYWORD) {
            result = COMMON_SPACING;
        }
    }

    public void visitMessageTest(ASTNode node) {
        if (SieveTokenTypes.TEST_KEYWORDS.contains(type1)) {
            result = COMMON_SPACING;
        }
    }

    public void visitStringList(ASTNode node) {
        if (type1 == SieveTokenTypes.LBRACKET
                || type2 == SieveTokenTypes.RBRACKET
                || type2 == SieveTokenTypes.COMMA) {
            result = NO_SPACING;
        } else if (type1 == SieveTokenTypes.COMMA) {
            result = COMMON_SPACING;
        }
    }

    public void visitTestList(final ASTNode node) {
        if (type1 == SieveTokenTypes.LPAREN && type2 == SieveTokenTypes.RPAREN) {
            result = NO_SPACING;
        } else if (type2 == SieveTokenTypes.COMMA) {
            result = NO_SPACING;
        } else if (type1 == SieveTokenTypes.LPAREN
                || type2 == SieveTokenTypes.RPAREN
                || type1 == SieveTokenTypes.COMMA
                ) {
            result = LINEBREAK_SPACING;
        }
    }

    public void visitArgumentList(ASTNode node) {
        result = COMMON_SPACING;
    }
}

