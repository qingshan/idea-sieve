package com.surfront.sieve.plugin.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.surfront.sieve.plugin.parser.SieveElementTypes;
import com.surfront.sieve.plugin.SieveNodeVisitor;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

import java.util.ArrayList;
import java.util.List;

public class SubBlockVisitor extends SieveNodeVisitor {
    private List<Block> blocks = new ArrayList<Block>();
    private CodeStyleSettings settings;

    public SubBlockVisitor(CodeStyleSettings settings) {
        this.settings = settings;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void visitElement(final ASTNode node) {
        Alignment alignment = getDefaultAlignment(node);
        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != SieveTokenTypes.WHITE_SPACE
                    && child.getTextRange().getLength() > 0) {
                Wrap wrap = getWrap(node, child);
                Alignment childAlignment = alignmentProjection(alignment, node, child);
                Indent childIndent = getIndent(node, child);
                blocks.add(new SieveBlock(child, childAlignment, childIndent, wrap, settings));
            }
            child = child.getTreeNext();
        }
    }

    private Wrap getWrap(final ASTNode node, final ASTNode child) {
        return Wrap.createWrap(WrapType.NONE, false);
    }

    private static Alignment getDefaultAlignment(final ASTNode node) {
        return null;
    }

    private Indent getIndent(final ASTNode node, final ASTNode child) {
        if (node.getElementType() == SieveElementTypes.FILE) {
            return Indent.getNoneIndent();
        }
        if (child.getElementType() == SieveElementTypes.BLOCK) {
            return Indent.getNoneIndent();
        }
        if (node.getElementType() == SieveElementTypes.CONTROL_COMMAND) {
            if (child.getElementType() == SieveTokenTypes.ELSIF_KEYWORD
                    || child.getElementType() == SieveTokenTypes.ELSE_KEYWORD) {
                return Indent.getNoneIndent();
            }
            if (SieveElementTypes.COMMANDS.contains(child.getElementType())) {
                return Indent.getNormalIndent();
            }
        }

        if (node.getElementType() == SieveElementTypes.BLOCK) {
            if (SieveElementTypes.COMMANDS.contains(child.getElementType())
                    || SieveTokenTypes.COMMENTS.contains(child.getElementType())) {
                return Indent.getNormalIndent();
            }
            return Indent.getNoneIndent();
        }

        if (SieveTokenTypes.COMMENTS.contains(child.getElementType())) {
            return Indent.getNoneIndent();
        }
        return null;
    }

    private Alignment alignmentProjection(final Alignment defaultAlignment, final ASTNode parent, final ASTNode child) {
        if (parent.getElementType() == SieveElementTypes.TEST_LIST
                && SieveElementTypes.TESTS.contains(child.getElementType())) {
            return defaultAlignment;
        } else if (parent.getElementType() == SieveElementTypes.ARGUMENT_LIST
                && SieveElementTypes.ARGUMENTS.contains(child.getElementType())) {
            return defaultAlignment;
        } else if (parent.getElementType() == SieveElementTypes.STRING_LIST
                && child.getElementType() == SieveTokenTypes.STRING_LITERAL) {
            return defaultAlignment;
        }
        return null;
    }
}