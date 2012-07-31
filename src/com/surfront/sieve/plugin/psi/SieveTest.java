package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

public abstract class SieveTest extends SieveElement {
    public SieveTest(final ASTNode node) {
        super(node);
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitTest(this);
        } else {
            super.accept(visitor);
        }
    }
}
