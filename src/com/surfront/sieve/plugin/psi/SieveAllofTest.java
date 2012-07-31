package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

public class SieveAllofTest extends SieveTest {
    public SieveAllofTest(final ASTNode node) {
        super(node);
    }

    public SieveTestList getTestList() {
        ASTNode node = getNode().findChildByType(SieveElementTypes.TEST_LIST);
        return node != null ? (SieveTestList)node.getPsi() : null;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitAllofTest(this);
        } else {
            super.accept(visitor);
        }
    }
}
