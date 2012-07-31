package com.surfront.sieve.plugin.file;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import com.surfront.sieve.plugin.SieveLanguage;

import javax.swing.*;

public class SieveFileType extends LanguageFileType {
    public static final SieveFileType SIEVE_FILE_TYPE = new SieveFileType();
    public static final Language SIEVE_LANGUAGE = SIEVE_FILE_TYPE.getLanguage();

    public static final String DEFAULT_EXTENSION = "siv";
    public SieveFileType() {
        super(new SieveLanguage());
    }

    public String getName() {
        return "Sieve";
    }

    public String getDescription() {
        return "Sieve Filtering Language";
    }

    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    public Icon getIcon() {
        return IconLoader.getIcon("/com/surfront/sieve/plugin/file/sieve.png");
    }
}
