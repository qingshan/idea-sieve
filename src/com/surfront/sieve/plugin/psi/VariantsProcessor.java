package com.surfront.sieve.plugin.psi;

import com.intellij.openapi.util.Key;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.PsiElement;

import java.util.List;
import java.util.ArrayList;

public class VariantsProcessor implements PsiScopeProcessor {
    private List<PsiElement> tags = new ArrayList<PsiElement>();

    public PsiElement[] getResult() {
        return tags.toArray(new PsiElement[0]);
    }

    public boolean execute(PsiElement element, ResolveState resolveState) {
        if (element instanceof SieveTagArgument) {
            tags.add(element);
        }
        return true;
    }

    public <T> T getHint(Key<T> tKey) {
        return null;
    }

    public void handleEvent(Event event, Object associated) {
    }
}
