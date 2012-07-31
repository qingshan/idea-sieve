package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

public abstract class SieveArgument extends SieveElement {
    public SieveArgument(final ASTNode node) {
        super(node);
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitArgument(this);
        } else {
            super.accept(visitor);
        }
    }
}

