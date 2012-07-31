package com.surfront.sieve.plugin.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.openapi.util.Key;
import com.surfront.sieve.plugin.file.SieveFileType;

import javax.swing.*;

public abstract class SieveElement extends ASTWrapperPsiElement {
    public SieveElement(final ASTNode node) {
        super(node);
    }

    public <T> T getUserData(Key<T> key) {
        return null;
    }

    public <T> void putUserData(Key<T> key, T value) {
    }

    public Icon getIcon(int flags) {
        return null;
    }

    public Language getLanguage() {
        return SieveFileType.SIEVE_LANGUAGE;
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitSieveElement(this);
        } else {
            super.accept(visitor);
        }
    }

    public SearchScope getUseScope() {
        return new LocalSearchScope(getContainingFile());
    }
}
