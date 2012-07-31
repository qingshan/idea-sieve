package com.surfront.sieve.plugin.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

public class SieveElementVisitor extends PsiElementVisitor {
    public void visitSieveElement(PsiElement node) {
        visitElement(node);
    }

    public void visitComment(SieveComment node) {
        visitElement(node);
    }

    public void visitCommand(SieveCommand node) {
        visitElement(node);
    }

    public void visitTest(SieveTest node) {
        visitElement(node);
    }

    public void visitBlockCommand(SieveBlockCommand node) {
        visitCommand(node);
    }

    public void visitControlCommand(SieveControlCommand node) {
        visitCommand(node);
    }

    public void visitActionCommand(SieveActionCommand node) {
        visitCommand(node);
    }

    public void visitTrueTest(SieveTrueTest node) {
        visitTest(node);
    }

    public void visitFalseTest(SieveFalseTest node) {
        visitTest(node);
    }

    public void visitNotTest(SieveNotTest node) {
        visitTest(node);
    }

    public void visitAllofTest(SieveAllofTest node) {
        visitTest(node);
    }

    public void visitAnyofTest(SieveAnyofTest node) {
        visitTest(node);
    }

    public void visitMessageTest(SieveMessageTest node) {
        visitTest(node);
    }

    public void visitStringList(SieveStringList node) {
        visitElement(node);
    }

    public void visitArgumentList(SieveArgumentList node) {
        visitElement(node);
    }

    public void visitTestList(SieveTestList node) {
        visitElement(node);
    }

    public void visitArgument(SieveArgument node) {
        visitElement(node);
    }

    public void visitTagArgument(SieveTagArgument node) {
        visitArgument(node);
    }

    public void visitNumberArgument(SieveNumberArgument node) {
        visitArgument(node);
    }

    public void visitStringArgument(SieveStringArgument node) {
        visitArgument(node);
    }
}

