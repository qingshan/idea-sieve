package com.surfront.sieve.plugin.parser;

import com.intellij.psi.tree.IElementType;
import com.surfront.sieve.plugin.file.SieveFileType;

public class SieveElementType extends IElementType {
    public SieveElementType(String debugName) {
        super(debugName, SieveFileType.SIEVE_LANGUAGE);
    }
}
