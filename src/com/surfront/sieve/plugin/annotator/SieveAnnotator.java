package com.surfront.sieve.plugin.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.surfront.sieve.plugin.SieveBundle;
import com.surfront.sieve.plugin.psi.SieveActionCommand;
import com.surfront.sieve.plugin.psi.SieveElementVisitor;
import com.surfront.sieve.plugin.psi.SieveMessageTest;

public class SieveAnnotator extends SieveElementVisitor implements Annotator {
    private AnnotationHolder holder;

    public void annotate(PsiElement psiElement, AnnotationHolder holder) {
      this.holder = holder;
      psiElement.accept(this);
    }

    public void visitMessageTest(SieveMessageTest node) {
        if (node.isUnknown()) {
            holder.createErrorAnnotation(node, SieveBundle.message("sieve.annotator.unknown"));
        }
    }

    public void visitActionCommand(SieveActionCommand node) {
        if (node.isUnknown()) {
            holder.createErrorAnnotation(node, SieveBundle.message("sieve.annotator.unknown"));
        }
    }
}
