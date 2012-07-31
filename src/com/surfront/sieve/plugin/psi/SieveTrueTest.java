package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

public class SieveTrueTest extends SieveTest {
    public SieveTrueTest(final ASTNode node) {
        super(node);
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitTrueTest(this);
        } else {
            super.accept(visitor);
        }
    }
}
