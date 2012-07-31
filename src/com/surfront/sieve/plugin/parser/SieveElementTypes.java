package com.surfront.sieve.plugin.parser;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.surfront.sieve.plugin.SieveLanguage;

public interface SieveElementTypes {
    IFileElementType FILE = new IFileElementType(Language.findInstance(SieveLanguage.class));

    IElementType STRING_LIST = new SieveElementType("STRING_LIST");
    IElementType ARGUMENT_LIST = new SieveElementType("ARGUMENT_LIST");
    IElementType TEST_LIST = new SieveElementType("TEST_LIST");

    IElementType BLOCK = new SieveElementType("BLOCK");
    IElementType CONTROL_COMMAND = new SieveElementType("CONTROL_COMMAND");
    IElementType ACTION_COMMAND = new SieveElementType("ACTION_COMMAND");

    TokenSet COMMANDS = TokenSet.create(
            BLOCK, CONTROL_COMMAND, ACTION_COMMAND
    );

    IElementType TRUE_TEST = new SieveElementType("TRUE_TEST");
    IElementType FALSE_TEST = new SieveElementType("FALSE_TEST");
    IElementType NOT_TEST = new SieveElementType("NOT_TEST");
    IElementType ALLOF_TEST = new SieveElementType("ALLOF_TEST");
    IElementType ANYOF_TEST = new SieveElementType("ANYOF_TEST");
    IElementType MESSAGE_TEST = new SieveElementType("MESSAGE_TEST");

    TokenSet TESTS = TokenSet.create(
            TRUE_TEST, FALSE_TEST, NOT_TEST,
            ALLOF_TEST, ANYOF_TEST, MESSAGE_TEST
    );

    IElementType TAG_ARGUMENT = new SieveElementType("TAG_ARGUMENT");
    IElementType NUMBER_ARGUMENT = new SieveElementType("NUMBER_ARGUMENT");
    IElementType STRING_ARGUMENT = new SieveElementType("STRING_ARGUMENT");
    IElementType STRINGS_ARGUMENT = new SieveElementType("STRINGS_ARGUMENT");

    TokenSet ARGUMENTS = TokenSet.create(
            TAG_ARGUMENT, NUMBER_ARGUMENT,
            STRING_ARGUMENT, STRINGS_ARGUMENT
    );
}
