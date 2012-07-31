package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

public class SieveStringArgument extends SieveArgument {
    private static final TokenSet STRING_LITERALS = TokenSet.create(SieveTokenTypes.STRING_LITERAL);
    public SieveStringArgument(final ASTNode node) {
        super(node);
    }

    public String[] getArgument() {
        ASTNode[] nodes = getNode().getChildren(STRING_LITERALS);
        String[] values = new String[nodes.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = nodes[i].getText();
        }
        return values;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitStringArgument(this);
        } else {
            super.accept(visitor);
        }
    }
}
