package com.surfront.sieve.plugin.parser;

import com.surfront.sieve.plugin.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageUtil;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

public class SieveParserDefinition implements ParserDefinition {
    public Lexer createLexer(Project project) {
        return new SieveLexer();
    }

    public PsiParser createParser(Project project) {
        return new SievePsiParser();
    }

    public IFileElementType getFileNodeType() {
        return SieveElementTypes.FILE;
    }

    public TokenSet getWhitespaceTokens() {
        return SieveTokenTypes.WHITESPACES;
    }

    public TokenSet getCommentTokens() {
        return SieveTokenTypes.COMMENTS;
    }

    public TokenSet getStringLiteralElements() {
        return SieveTokenTypes.LITERALS;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new SieveFile(viewProvider);
    }

    public PsiElement createElement(ASTNode node) {
        IElementType type = node.getElementType();
        if (type == SieveElementTypes.BLOCK) {
            return new SieveBlockCommand(node);
        } else if (type == SieveElementTypes.CONTROL_COMMAND) {
            return new SieveControlCommand(node);
        } else if (type == SieveElementTypes.ACTION_COMMAND) {
            return new SieveActionCommand(node);
        } else if (type == SieveElementTypes.TRUE_TEST) {
            return new SieveTrueTest(node);
        } else if (type == SieveElementTypes.FALSE_TEST) {
            return new SieveFalseTest(node);
        } else if (type == SieveElementTypes.NOT_TEST) {
            return new SieveNotTest(node);
        } else if (type == SieveElementTypes.ALLOF_TEST) {
            return new SieveAllofTest(node);
        } else if (type == SieveElementTypes.ANYOF_TEST) {
            return new SieveAnyofTest(node);
        } else if (type == SieveElementTypes.MESSAGE_TEST) {
            return new SieveMessageTest(node);
        } else if (type == SieveElementTypes.STRING_LIST) {
            return new SieveStringList(node);
        } else if (type == SieveElementTypes.TEST_LIST) {
            return new SieveTestList(node);
        } else if (type == SieveElementTypes.ARGUMENT_LIST) {
            return new SieveArgumentList(node);
        } else if (type == SieveElementTypes.TAG_ARGUMENT) {
            return new SieveTagArgument(node);
        } else if (type == SieveElementTypes.NUMBER_ARGUMENT) {
            return new SieveNumberArgument(node);
        } else if (type == SieveElementTypes.STRING_ARGUMENT || type == SieveElementTypes.STRINGS_ARGUMENT) {
            return new SieveStringArgument(node);
        }
        return new ASTWrapperPsiElement(node);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        final Lexer lexer = createLexer(left.getPsi().getProject());
        return LanguageUtil.canStickTokensTogetherByLexer(left, right, lexer);
    }
}
