package com.surfront.sieve.plugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.surfront.sieve.plugin.file.SieveFileType;

public class SieveFile extends PsiFileBase implements PsiElement {
    public SieveFile(FileViewProvider viewProvider) {
        super(viewProvider, SieveFileType.SIEVE_LANGUAGE);
    }

    public FileType getFileType() {
        return SieveFileType.SIEVE_FILE_TYPE;
    }

    public String toString() {
        return "SieveFile:" + getName();
    }

    public void accept(PsiElementVisitor visitor) {
        if (visitor instanceof SieveElementVisitor) {
            ((SieveElementVisitor) visitor).visitSieveElement(this);
        } else {
            super.accept(visitor);
        }
    }

    public boolean isWritable() {
        return super.isWritable() && getVirtualFile() != null;
    }

}
