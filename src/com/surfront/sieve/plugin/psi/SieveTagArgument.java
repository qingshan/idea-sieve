package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

public class SieveTagArgument extends SieveArgument {
    public SieveTagArgument(final ASTNode node) {
        super(node);
    }

    public String getArgument() {
        ASTNode node = getNode().findChildByType(SieveTokenTypes.TAG_LITERAL);
        return node == null ? null : node.getText();
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitTagArgument(this);
        } else {
            super.accept(visitor);
        }
    }
}
