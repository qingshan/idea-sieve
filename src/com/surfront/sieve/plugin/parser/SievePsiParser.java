package com.surfront.sieve.plugin.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.surfront.sieve.plugin.SieveBundle;

public class SievePsiParser implements PsiParser {
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        PsiBuilder.Marker rootMarker = builder.mark();
        while (!builder.eof()) {
            parseCommand(builder);
        }
        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    public static void parseCommand(PsiBuilder builder) {
        IElementType tokenType = builder.getTokenType();
        if (tokenType == null) {
            return;
        }
        if (tokenType == SieveTokenTypes.LBRACE) {
            parseBlock(builder);
            return;
        }
        if (tokenType == SieveTokenTypes.IF_KEYWORD) {
            parseIfCommand(builder);
            return;
        }
        if (SieveTokenTypes.ACTION_KEYWORDS.contains(tokenType)) {
            parseActionCommand(builder);
            return;
        }
        if (tokenType == SieveTokenTypes.UNKNOWN_KEYWORD) {
            parseActionCommand(builder);
            return;
        }
        builder.advanceLexer();
        builder.error(SieveBundle.message("sieve.expected.statement"));
    }

    private static void parseBlock(PsiBuilder builder) {
        if (builder.getTokenType() != SieveTokenTypes.LBRACE) {
            builder.error(SieveBundle.message("sieve.expected.lbrace"));
            return;
        }
        PsiBuilder.Marker block = builder.mark();
        builder.advanceLexer();
        while (builder.getTokenType() != SieveTokenTypes.RBRACE) {
            if (builder.eof()) {
                builder.error(SieveBundle.message("sieve.missing.rbrace"));
                block.done(SieveElementTypes.BLOCK);
                return;
            }
            parseCommand(builder);
        }
        builder.advanceLexer();
        block.done(SieveElementTypes.BLOCK);
    }

    private static void parseIfCommand(PsiBuilder builder) {
        PsiBuilder.Marker command = builder.mark();
        builder.advanceLexer();

        parseTest(builder);
        parseBlock(builder);

        if (builder.getTokenType() == SieveTokenTypes.ELSIF_KEYWORD || builder.getTokenType() == SieveTokenTypes.ELSE_KEYWORD) {
            parseIfCommand(builder);
        }
        command.done(SieveElementTypes.CONTROL_COMMAND);
    }

    private static void parseActionCommand(PsiBuilder builder) {
        PsiBuilder.Marker command = builder.mark();
        builder.advanceLexer();
        parseArgumentList(builder);
        checkForSemicolon(builder);
        command.done(SieveElementTypes.ACTION_COMMAND);
    }

    private static void parseTest(PsiBuilder builder) {
        PsiBuilder.Marker test = builder.mark();
        IElementType tokenType = builder.getTokenType();
        builder.advanceLexer();
        if (tokenType == SieveTokenTypes.TRUE_KEYWORD) {
            test.done(SieveElementTypes.TRUE_TEST);
        } else if (tokenType == SieveTokenTypes.FALSE_KEYWORD) {
            test.done(SieveElementTypes.FALSE_TEST);
        } else if (tokenType == SieveTokenTypes.NOT_KEYWORD) {
            parseTest(builder);
            test.done(SieveElementTypes.NOT_TEST);
        } else if (tokenType == SieveTokenTypes.ALLOF_KEYWORD) {
            parseTestList(builder);
            test.done(SieveElementTypes.ALLOF_TEST);
        } else if (tokenType == SieveTokenTypes.ANYOF_KEYWORD) {
            parseTestList(builder);
            test.done(SieveElementTypes.ANYOF_TEST);
        } else if (SieveTokenTypes.TEST_KEYWORDS.contains(tokenType)) {
            parseArgumentList(builder);
            test.done(SieveElementTypes.MESSAGE_TEST);
        } else if (tokenType == SieveTokenTypes.UNKNOWN_KEYWORD) {
            parseArgumentList(builder);
            test.done(SieveElementTypes.MESSAGE_TEST);
        } else {
            test.drop();
            builder.error(SieveBundle.message("sieve.expected.test"));
        }
    }

    private static void parseTestList(PsiBuilder builder) {
        PsiBuilder.Marker testlist = builder.mark();
        checkMatches(builder, SieveTokenTypes.LPAREN, SieveBundle.message("sieve.expected.lparen"));
        boolean first = true;
        while (builder.getTokenType() != SieveTokenTypes.RPAREN) {
            if (first) {
                first = false;
            } else {
                if (builder.getTokenType() == SieveTokenTypes.COMMA) {
                    builder.advanceLexer();
                } else {
                    builder.error(SieveBundle.message("sieve.expected.comma.or.rparen"));
                    break;
                }
            }
            parseTest(builder);
        }
        checkMatches(builder, SieveTokenTypes.RPAREN, SieveBundle.message("sieve.expected.rparen"));
        testlist.done(SieveElementTypes.TEST_LIST);
    }

    private static void parseArgumentList(PsiBuilder builder) {
        PsiBuilder.Marker argumentlist = builder.mark();
        while (true) {
            IElementType tokenType = builder.getTokenType();
            PsiBuilder.Marker argument = builder.mark();
            if (tokenType == SieveTokenTypes.TAG_LITERAL) {
                builder.advanceLexer();
                argument.done(SieveElementTypes.TAG_ARGUMENT);
            } else if (tokenType == SieveTokenTypes.NUMBER_LITERAL) {
                builder.advanceLexer();
                argument.done(SieveElementTypes.NUMBER_ARGUMENT);
            } else if (tokenType == SieveTokenTypes.STRING_LITERAL) {
                builder.advanceLexer();
                argument.done(SieveElementTypes.STRING_ARGUMENT);
            } else if (tokenType == SieveTokenTypes.LBRACKET) {
                builder.advanceLexer();
                parseStringList(builder);
                checkMatches(builder, SieveTokenTypes.RBRACKET, SieveBundle.message("sieve.expected.rbracket"));
                argument.done(SieveElementTypes.STRINGS_ARGUMENT);
            } else {
                argument.drop();
                break;
            }
        }
        argumentlist.done(SieveElementTypes.ARGUMENT_LIST);
    }

    private static void parseStringList(PsiBuilder builder) {
        PsiBuilder.Marker stringlist = builder.mark();
        boolean first = true;
        while (builder.getTokenType() != SieveTokenTypes.RBRACKET) {
            if (first) {
                first = false;
            } else {
                if (builder.getTokenType() == SieveTokenTypes.COMMA) {
                    builder.advanceLexer();
                } else {
                    builder.error(SieveBundle.message("sieve.expected.comma.or.rbracket"));
                    break;
                }
            }
            if (builder.getTokenType() == SieveTokenTypes.STRING_LITERAL) {
                builder.advanceLexer();
            } else {
                builder.error(SieveBundle.message("sieve.expected.string"));
                break;
            }
        }
        stringlist.done(SieveElementTypes.STRING_LIST);
    }

    private static void checkForSemicolon(PsiBuilder builder) {
        if (builder.getTokenType() == SieveTokenTypes.SEMICOLON) {
            builder.advanceLexer();
        } else {
            builder.error(SieveBundle.message("sieve.expected.semicolon"));
        }
    }

    protected static void checkMatches(PsiBuilder builder, IElementType token, String message) {
        if (builder.getTokenType() == token) {
            builder.advanceLexer();
        } else {
            builder.error(message);
        }
    }
}

