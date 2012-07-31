package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

public class SieveControlCommand extends SieveCommand {
    public SieveControlCommand(final ASTNode node) {
        super(node);
    }

    public SieveTest getTest() {
        ASTNode[] test = getNode().getChildren(SieveElementTypes.TESTS);
        return (SieveTest)(test.length == 1 ? test[0].getPsi() : null);
    }

    public SieveCommand getBlock() {
        ASTNode block = getNode().findChildByType(SieveElementTypes.BLOCK);
        return (SieveCommand) (block == null ? null : block.getPsi());
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitControlCommand(this);
        } else {
            super.accept(visitor);
        }
    }
}
