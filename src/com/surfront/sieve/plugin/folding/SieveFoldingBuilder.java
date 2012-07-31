package com.surfront.sieve.plugin.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.surfront.sieve.plugin.parser.SieveElementTypes;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

import java.util.ArrayList;
import java.util.List;

public class SieveFoldingBuilder implements FoldingBuilder {
    public FoldingDescriptor[] buildFoldRegions(ASTNode node, Document document) {
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, document, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    private void appendDescriptors(final ASTNode node, final Document document, final List<FoldingDescriptor> descriptors) {
        if (node.getElementType() == SieveElementTypes.BLOCK) {
            if (document.getLineNumber(node.getStartOffset()) != document.getLineNumber(node.getTextRange().getEndOffset())) {
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
        } else if (node.getElementType() == SieveTokenTypes.C_STYLE_COMMENT) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            appendDescriptors(child, document, descriptors);
            child = child.getTreeNext();
        }
    }

    public String getPlaceholderText(ASTNode node) {
        if (node.getElementType() == SieveTokenTypes.C_STYLE_COMMENT) {
            return "/*...*/";
        } else if (node.getElementType() == SieveElementTypes.BLOCK) {
            return "{...}";
        }
        return null;
    }

    public boolean isCollapsedByDefault(ASTNode node) {
        return false;
    }
}

