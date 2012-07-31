package com.surfront.sieve.plugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

public class SieveBlockCommand extends SieveCommand {
    public SieveBlockCommand(final ASTNode node) {
        super(node);
    }

    public SieveCommand[] getCommands() {
        ASTNode[] nodes = getNode().getChildren(SieveElementTypes.COMMANDS);
        SieveCommand[] commands = new SieveCommand[nodes.length];
        for (int i = 0; i < commands.length; i++) {
            commands[i] = (SieveCommand) nodes[i].getPsi();
        }
        return commands;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitBlockCommand(this);
        } else {
            super.accept(visitor);
        }
    }
}
