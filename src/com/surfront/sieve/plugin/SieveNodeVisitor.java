package com.surfront.sieve.plugin;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.surfront.sieve.plugin.parser.SieveElementTypes;

public abstract class SieveNodeVisitor {
    public final void visit(ASTNode node) {
        IElementType type = node.getElementType();
        if (type == SieveElementTypes.FILE) {
            visitFile(node);
        } else if (type == SieveElementTypes.BLOCK) {
            visitBlock(node);
        } else if (type == SieveElementTypes.CONTROL_COMMAND) {
            visitControlCommand(node);
        } else if (type == SieveElementTypes.ACTION_COMMAND) {
            visitActionCommand(node);
        } else if (type == SieveElementTypes.TRUE_TEST) {
            visitTrueTest(node);
        } else if (type == SieveElementTypes.FALSE_TEST) {
            visitFalseTest(node);
        } else if (type == SieveElementTypes.NOT_TEST) {
            visitNotTest(node);
        } else if (type == SieveElementTypes.ANYOF_TEST) {
            visitAnyofTest(node);
        } else if (type == SieveElementTypes.ALLOF_TEST) {
            visitAllofTest(node);
        } else if (type == SieveElementTypes.MESSAGE_TEST) {
            visitMessageTest(node);
        } else if (type == SieveElementTypes.TEST_LIST) {
            visitTestList(node);
        } else if (type == SieveElementTypes.TAG_ARGUMENT) {
            visitTagArgument(node);
        } else if (type == SieveElementTypes.NUMBER_ARGUMENT) {
            visitNumberArgument(node);
        } else if (type == SieveElementTypes.STRING_ARGUMENT) {
            visitStringArgument(node);
        } else if (type == SieveElementTypes.STRINGS_ARGUMENT) {
            visitStringsArgument(node);
        } else if (type == SieveElementTypes.ARGUMENT_LIST) {
            visitArgumentList(node);
        } else if (type == SieveElementTypes.STRING_LIST) {
            visitStringList(node);
        }
    }

    public void visitElement(ASTNode node) {
    }

    public void visitFile(ASTNode node) {
        visitElement(node);
    }

    public void visitBlock(ASTNode node) {
        visitElement(node);
    }

    public void visitCommand(ASTNode node) {
        visitElement(node);
    }

    public void visitControlCommand(ASTNode node) {
        visitCommand(node);
    }

    public void visitActionCommand(ASTNode node) {
        visitCommand(node);
    }

    public void visitTest(ASTNode node) {
        visitElement(node);
    }

    public void visitTrueTest(ASTNode node) {
        visitTest(node);
    }

    public void visitFalseTest(ASTNode node) {
        visitTest(node);
    }

    public void visitAllofTest(ASTNode node) {
        visitTest(node);
    }

    public void visitAnyofTest(ASTNode node) {
        visitTest(node);
    }

    public void visitNotTest(ASTNode node) {
        visitTest(node);
    }

    public void visitMessageTest(ASTNode node) {
        visitTest(node);
    }

    public void visitTestList(ASTNode node) {
        visitElement(node);
    }

    public void visitArgument(ASTNode node) {
        visitElement(node);
    }

    public void visitTagArgument(ASTNode node) {
        visitArgument(node);
    }

    public void visitNumberArgument(ASTNode node) {
        visitArgument(node);
    }

    public void visitStringArgument(ASTNode node) {
        visitArgument(node);
    }

    public void visitStringsArgument(ASTNode node) {
        visitArgument(node);
    }

    public void visitArgumentList(ASTNode node) {
        visitElement(node);
    }

    public void visitStringList(ASTNode node) {
        visitElement(node);
    }

}
