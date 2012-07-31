package com.surfront.sieve.plugin.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.StringEscapesTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.surfront.sieve.plugin.parser.SieveMergingLexer;
import com.surfront.sieve.plugin.parser.SieveTokenTypes;

import java.util.HashMap;
import java.util.Map;

public class SieveSyntaxHighlighter extends SyntaxHighlighterBase {
    private static Map<IElementType, TextAttributesKey> keys;

    public Lexer getHighlightingLexer() {
        return new SieveMergingLexer();
    }

    private static final TextAttributesKey SIEVE_KEYWORD = TextAttributesKey.createTextAttributesKey(
            "SIEVE.KEYWORD",
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_TAG = TextAttributesKey.createTextAttributesKey(
            "SIEVE.TAG",
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_STRING = TextAttributesKey.createTextAttributesKey(
            "SIEVE.STRING",
            SyntaxHighlighterColors.STRING.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_NUMBER = TextAttributesKey.createTextAttributesKey(
            "SIEVE.NUMBER",
            SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
            "SIEVE.LINE_COMMENT",
            SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
            "SIEVE.BLOCK_COMMENT",
            SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_PARENTHS = TextAttributesKey.createTextAttributesKey(
            "SIEVE.PARENTHS",
            SyntaxHighlighterColors.PARENTHS.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_BRACKETS = TextAttributesKey.createTextAttributesKey(
            "SIEVE.BRACKETS",
            SyntaxHighlighterColors.BRACKETS.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_BRACES = TextAttributesKey.createTextAttributesKey(
            "SIEVE.BRACES",
            SyntaxHighlighterColors.BRACES.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_COMMA = TextAttributesKey.createTextAttributesKey(
            "SIEVE.COMMA",
            SyntaxHighlighterColors.COMMA.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_SEMICOLON = TextAttributesKey.createTextAttributesKey(
            "SIEVE.SEMICOLON",
            SyntaxHighlighterColors.JAVA_SEMICOLON.getDefaultAttributes()
    );

    private static final TextAttributesKey SIEVE_BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
            "SIEVE.BADCHARACTER",
            HighlighterColors.BAD_CHARACTER.getDefaultAttributes()
    );
    private static final TextAttributesKey SIEVE_VALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey(
            "SIEVE.VALID_STRING_ESCAPE",
            SyntaxHighlighterColors.VALID_STRING_ESCAPE.getDefaultAttributes()
    );
    private static final TextAttributesKey SIEVE_INVALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey(
            "SIEVE.INVALID_STRING_ESCAPE",
            SyntaxHighlighterColors.INVALID_STRING_ESCAPE.getDefaultAttributes()
    );

    static {
        keys = new HashMap<IElementType, TextAttributesKey>();

        fillMap(keys, SieveTokenTypes.KEYWORDS, SIEVE_KEYWORD);
        keys.put(SieveTokenTypes.UNKNOWN_KEYWORD, SIEVE_KEYWORD);

        keys.put(StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN, SIEVE_VALID_STRING_ESCAPE);
        keys.put(StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN, SIEVE_INVALID_STRING_ESCAPE);
        keys.put(StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN, SIEVE_INVALID_STRING_ESCAPE);

        keys.put(SieveTokenTypes.TAG_LITERAL, SIEVE_TAG);
        keys.put(SieveTokenTypes.NUMBER_LITERAL, SIEVE_NUMBER);
        keys.put(SieveTokenTypes.STRING_LITERAL, SIEVE_STRING);

        keys.put(SieveTokenTypes.LPAREN, SIEVE_PARENTHS);
        keys.put(SieveTokenTypes.RPAREN, SIEVE_PARENTHS);

        keys.put(SieveTokenTypes.LBRACE, SIEVE_BRACES);
        keys.put(SieveTokenTypes.RBRACE, SIEVE_BRACES);

        keys.put(SieveTokenTypes.LBRACKET, SIEVE_BRACKETS);
        keys.put(SieveTokenTypes.RBRACKET, SIEVE_BRACKETS);

        keys.put(SieveTokenTypes.COMMA, SIEVE_COMMA);
        keys.put(SieveTokenTypes.SEMICOLON, SIEVE_SEMICOLON);

        keys.put(SieveTokenTypes.C_STYLE_COMMENT, SIEVE_BLOCK_COMMENT);
        keys.put(SieveTokenTypes.END_OF_LINE_COMMENT, SIEVE_LINE_COMMENT);
        keys.put(SieveTokenTypes.BAD_CHARACTER, SIEVE_BAD_CHARACTER);
    }

    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(keys.get(tokenType));
    }

    public Map<IElementType, TextAttributesKey> getKeys() {
        return (Map<IElementType, TextAttributesKey>) ((HashMap) keys).clone();
    }
}
