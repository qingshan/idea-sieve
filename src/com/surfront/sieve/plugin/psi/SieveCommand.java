package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

public abstract class SieveCommand extends SieveElement {
    public SieveCommand(final ASTNode node) {
        super(node);
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitCommand(this);
        } else {
            super.accept(visitor);
        }
    }
}
