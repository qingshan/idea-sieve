package com.surfront.sieve.plugin.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

public class SieveFileTypeFactory extends FileTypeFactory {
    public void createFileTypes(FileTypeConsumer consumer) {
        consumer.consume(SieveFileType.SIEVE_FILE_TYPE, "siv");
    }
}
