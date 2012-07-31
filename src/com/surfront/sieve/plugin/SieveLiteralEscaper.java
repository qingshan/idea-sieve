package com.surfront.sieve.plugin;

import com.surfront.sieve.plugin.psi.SieveStringArgument;
import com.intellij.lang.LiteralEscaper;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;

public class SieveLiteralEscaper implements LiteralEscaper {
    public String getEscapedText(final PsiElement context, final String originalText) {
        if (context instanceof SieveStringArgument) {
            return StringUtil.escapeStringCharacters(originalText);
        }
        return originalText;
    }

    public String escapeText(String originalText) {
        return StringUtil.escapeStringCharacters(originalText);
    }

    public String unescapeText(String originalText) {
        return StringUtil.unescapeStringCharacters(originalText);
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.escapeStringCharacters("dfadfa\r\n\tabc\""));
    }
}
