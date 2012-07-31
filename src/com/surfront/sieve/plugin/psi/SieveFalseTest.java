package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

public class SieveFalseTest extends SieveTest {
    public SieveFalseTest(final ASTNode node) {
        super(node);
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitFalseTest(this);
        } else {
            super.accept(visitor);
        }
    }
}
