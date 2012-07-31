package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveElementTypes;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

public class SieveMessageTest extends SieveTest {
    public SieveMessageTest(final ASTNode node) {
        super(node);
    }

    public boolean isUnknown() {
        ASTNode nameNode = getNode().findChildByType(SieveTokenTypes.UNKNOWN_KEYWORD);
        return nameNode != null;
    }

    public SieveArgumentList getArgumentList() {
        ASTNode argumentlist = getNode().findChildByType(SieveElementTypes.ARGUMENT_LIST);
        return argumentlist != null ? (SieveArgumentList) argumentlist.getPsi() : null;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitMessageTest(this);
        } else {
            super.accept(visitor);
        }
    }
}
