package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

public class SieveTestList extends SieveElement {
    public SieveTestList(final ASTNode node) {
        super(node);
    }

    public SieveTest[] getTests() {
        ASTNode[] nodes = getNode().getChildren(SieveElementTypes.TESTS);
        SieveTest[] tests = new SieveTest[nodes.length];
        for (int i = 0; i < tests.length; i++) {
            tests[i] = (SieveTest) nodes[i].getPsi();
        }
        return tests;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitTestList(this);
        } else {
            super.accept(visitor);
        }
    }
}
