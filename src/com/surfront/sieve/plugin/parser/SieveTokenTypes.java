package com.surfront.sieve.plugin.parser;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface SieveTokenTypes {
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    // Comments
    IElementType END_OF_LINE_COMMENT = new SieveElementType("END_OF_LINE_COMMENT");
    IElementType C_STYLE_COMMENT = new SieveElementType("C_STYLE_COMMENT");

    // Keywords
    IElementType IF_KEYWORD = new SieveElementType("IF_KEYWORD");
    IElementType ELSIF_KEYWORD = new SieveElementType("ELSIF_KEYWORD");
    IElementType ELSE_KEYWORD = new SieveElementType("ELSE_KEYWORD");

    IElementType REQUIRE_KEYWORD = new SieveElementType("REQUIRE_KEYWORD");
    IElementType DISCARD_KEYWORD = new SieveElementType("DISCARD_KEYWORD");
    IElementType STOP_KEYWORD = new SieveElementType("STOP_KEYWORD");

    IElementType TRUE_KEYWORD = new SieveElementType("TRUE_KEYWORD");
    IElementType FALSE_KEYWORD = new SieveElementType("FALSE_KEYWORD");
    IElementType NOT_KEYWORD = new SieveElementType("NOT_KEYWORD");
    IElementType ALLOF_KEYWORD = new SieveElementType("ALLOF_KEYWORD");
    IElementType ANYOF_KEYWORD = new SieveElementType("ANYOF_KEYWORD");
    IElementType HEADER_KEYWORD = new SieveElementType("HEADER_KEYWORD");
    IElementType ADDRESS_KEYWORD = new SieveElementType("ADDRESS_KEYWORD");
    IElementType ENVELOPE_KEYWORD = new SieveElementType("ENVELOPE_KEYWORD");
    IElementType BODY_KEYWORD = new SieveElementType("BODY_KEYWORD");
    IElementType ATTACHMENT_KEYWORD = new SieveElementType("ATTACHMENT_KEYWORD");
    IElementType MIME_KEYWORD = new SieveElementType("MIME_KEYWORD");
    IElementType PREAMBLE_KEYWORD = new SieveElementType("PREAMBLE_KEYWORD");
    IElementType URI_KEYWORD = new SieveElementType("URI_KEYWORD");
    IElementType IP_KEYWORD = new SieveElementType("IP_KEYWORD");
    IElementType EXISTS_KEYWORD = new SieveElementType("EXISTS_KEYWORD");
    IElementType SIZE_KEYWORD = new SieveElementType("SIZE_KEYWORD");
    IElementType SCORE_KEYWORD = new SieveElementType("SCORE_KEYWORD");

    TokenSet CONTROL_KEYWORDS = TokenSet.create(
            IF_KEYWORD, ELSIF_KEYWORD, ELSE_KEYWORD
    );

    TokenSet ACTION_KEYWORDS = TokenSet.create(
            REQUIRE_KEYWORD, STOP_KEYWORD, DISCARD_KEYWORD, SCORE_KEYWORD
    );

    TokenSet TEST_KEYWORDS = TokenSet.create(
            TRUE_KEYWORD, FALSE_KEYWORD, NOT_KEYWORD,
            ALLOF_KEYWORD, ANYOF_KEYWORD, HEADER_KEYWORD,
            ADDRESS_KEYWORD, ENVELOPE_KEYWORD, BODY_KEYWORD,
            ATTACHMENT_KEYWORD, MIME_KEYWORD, PREAMBLE_KEYWORD,
            URI_KEYWORD, IP_KEYWORD, EXISTS_KEYWORD, SIZE_KEYWORD,
            SCORE_KEYWORD
    );

    TokenSet KEYWORDS = TokenSet.orSet(
            CONTROL_KEYWORDS, ACTION_KEYWORDS, TEST_KEYWORDS
    );
    IElementType UNKNOWN_KEYWORD = new SieveElementType("UNKNOWN_KEYWORD");

    // Punctuators
    IElementType LPAREN = new SieveElementType("LPAREN");// (
    IElementType RPAREN = new SieveElementType("RPAREN");// )
    IElementType LBRACE = new SieveElementType("LBRACE");// {
    IElementType RBRACE = new SieveElementType("RBRACE");// }
    IElementType LBRACKET = new SieveElementType("LBRACKET");// [
    IElementType RBRACKET = new SieveElementType("RBRACKET");// ]
    IElementType SEMICOLON = new SieveElementType("SEMICOLON");// ;
    IElementType COMMA = new SieveElementType("COMMA");// ,

    // Literals
    IElementType TAG_LITERAL = new SieveElementType("TAG_LITERAL");
    IElementType NUMBER_LITERAL = new SieveElementType("NUMBER_LITERAL");
    IElementType STRING_LITERAL = new SieveElementType("STRING_LITERAL");

    TokenSet LITERALS = TokenSet.create(
            TAG_LITERAL, NUMBER_LITERAL, STRING_LITERAL
    );
    TokenSet COMMENTS = TokenSet.create(
            END_OF_LINE_COMMENT, C_STYLE_COMMENT
    );
    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
}
