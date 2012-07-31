package com.surfront.sieve.plugin.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

import java.util.List;

public class SieveBlock implements Block {
    private ASTNode node;

    private final CodeStyleSettings settings;

    private Alignment alignment;
    private Indent indent;
    private Wrap wrap;
    private List<Block> subBlocks = null;

    public SieveBlock(final ASTNode node, final Alignment alignment, final Indent indent, final Wrap wrap, final CodeStyleSettings settings) {
        this.alignment = alignment;
        this.indent = indent;
        this.node = node;
        this.wrap = wrap;
        this.settings = settings;
    }

    public ASTNode getNode() {
        return node;
    }

    public TextRange getTextRange() {
        return node.getTextRange();
    }

    public List<Block> getSubBlocks() {
        if (subBlocks == null) {
            SubBlockVisitor visitor = new SubBlockVisitor(getSettings());
            visitor.visit(node);
            subBlocks = visitor.getBlocks();
        }
        return subBlocks;
    }

    public Wrap getWrap() {
        return wrap;
    }

    public Indent getIndent() {
        return indent;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Spacing getSpacing(Block child1, Block child2) {
        return new SieveSpacingProcessor(getNode(), ((SieveBlock) child1).getNode(), ((SieveBlock) child2).getNode()).getResult();
    }

    public ChildAttributes getChildAttributes(final int newChildIndex) {
        IElementType blockElementType = node.getElementType();
        Indent indent = null;
        if (blockElementType == SieveElementTypes.BLOCK) {
            indent = Indent.getNormalIndent();
        } else if (blockElementType == SieveElementTypes.FILE) {
            indent = Indent.getNoneIndent();
        } else if (SieveElementTypes.COMMANDS.contains(blockElementType)) {
            indent = Indent.getNoneIndent();
        }
        Alignment alignment = null;
        final List<Block> subBlocks = getSubBlocks();
        for (int i = 0; i < newChildIndex; i++) {
            final Alignment childAlignment = subBlocks.get(i).getAlignment();
            if (childAlignment != null) {
                alignment = childAlignment;
                break;
            }
        }
        return new ChildAttributes(indent, alignment);
    }

    public boolean isIncomplete() {
        return isIncomplete(node);
    }

    private boolean isIncomplete(ASTNode node) {
        ASTNode lastChild = node.getLastChildNode();
        while (lastChild != null && lastChild.getPsi() instanceof PsiWhiteSpace) {
            lastChild = lastChild.getTreePrev();
        }
        if (lastChild == null) {
            return false;
        }
        if (lastChild.getPsi() instanceof PsiErrorElement) {
            return true;
        }
        return isIncomplete(lastChild);
    }

    public CodeStyleSettings getSettings() {
        return settings;
    }

    public boolean isLeaf() {
        return node.getFirstChildNode() == null;
    }
}

