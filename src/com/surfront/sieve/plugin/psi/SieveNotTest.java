package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

public class SieveNotTest extends SieveTest {
    public SieveNotTest(final ASTNode node) {
        super(node);
    }

    public SieveTest getTest() {
        ASTNode[] nodes = getNode().getChildren(SieveElementTypes.TESTS);
        return nodes.length == 1 ? (SieveTest) nodes[0].getPsi() : null;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitNotTest(this);
        } else {
            super.accept(visitor);
        }
    }
}
