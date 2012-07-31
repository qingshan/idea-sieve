package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

public class SieveArgumentList extends SieveElement {
    public SieveArgumentList(final ASTNode node) {
        super(node);
    }

    public SieveArgument[] getArguments() {
        ASTNode[] nodes = getNode().getChildren(SieveElementTypes.ARGUMENTS);
        SieveArgument[] arguments = new SieveArgument[nodes.length];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = (SieveArgument) nodes[i].getPsi();
        }
        return arguments;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitArgumentList(this);
        } else {
            super.accept(visitor);
        }
    }
}
