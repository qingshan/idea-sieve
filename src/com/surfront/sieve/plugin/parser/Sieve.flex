package com.surfront.sieve.plugin.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

%%

%class _SieveLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

DIGIT=[0-9]
CRLF= [\ \t \f]* (\n | \r | \r\n)
WHITE_SPACE_CHAR=[\ \n\r\t\f]
ESCAPE_SEQUENCE=\\[^\r\n]

IDENTIFIER=[:jletter:] [:jletterdigit:]*

TAG_LITERAL=(":")+({IDENTIFIER})
NUMBER_LITERAL=({DIGIT})+([KMG]?)
STRING_LITERAL=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE}|\\{CRLF})*(\"|\\)?

C_STYLE_COMMENT=("/*"[^"*"]{COMMENT_TAIL})|"/*"
COMMENT_TAIL=([^"*"]*("*"+[^"*""/"])?)*("*"+"/")?
END_OF_LINE_COMMENT="#"[^\r\n]*

%%

{WHITE_SPACE_CHAR}+   { return SieveTokenTypes.WHITE_SPACE; }

{C_STYLE_COMMENT}     { return SieveTokenTypes.C_STYLE_COMMENT; }
{END_OF_LINE_COMMENT} { return SieveTokenTypes.END_OF_LINE_COMMENT; }

{TAG_LITERAL}         { yybegin(YYINITIAL); return SieveTokenTypes.TAG_LITERAL; }
{NUMBER_LITERAL}      { yybegin(YYINITIAL); return SieveTokenTypes.NUMBER_LITERAL; }
{STRING_LITERAL}      { yybegin(YYINITIAL); return SieveTokenTypes.STRING_LITERAL; }

"if"                  { yybegin(YYINITIAL); return SieveTokenTypes.IF_KEYWORD; }
"elsif"               { yybegin(YYINITIAL); return SieveTokenTypes.ELSIF_KEYWORD; }
"else"                { yybegin(YYINITIAL); return SieveTokenTypes.ELSE_KEYWORD; }

"require"             { yybegin(YYINITIAL); return SieveTokenTypes.REQUIRE_KEYWORD; }
"discard"             { yybegin(YYINITIAL); return SieveTokenTypes.DISCARD_KEYWORD; }
"stop"                { yybegin(YYINITIAL); return SieveTokenTypes.STOP_KEYWORD; }

"true"                { yybegin(YYINITIAL); return SieveTokenTypes.TRUE_KEYWORD; }
"false"               { yybegin(YYINITIAL); return SieveTokenTypes.FALSE_KEYWORD; }
"not"                 { yybegin(YYINITIAL); return SieveTokenTypes.NOT_KEYWORD; }
"allof"               { yybegin(YYINITIAL); return SieveTokenTypes.ALLOF_KEYWORD; }
"anyof"               { yybegin(YYINITIAL); return SieveTokenTypes.ANYOF_KEYWORD; }
"header"              { yybegin(YYINITIAL); return SieveTokenTypes.HEADER_KEYWORD; }
"address"             { yybegin(YYINITIAL); return SieveTokenTypes.ADDRESS_KEYWORD; }
"envelope"            { yybegin(YYINITIAL); return SieveTokenTypes.ENVELOPE_KEYWORD; }
"body"                { yybegin(YYINITIAL); return SieveTokenTypes.BODY_KEYWORD; }
"attachment"          { yybegin(YYINITIAL); return SieveTokenTypes.ATTACHMENT_KEYWORD; }
"mime"                { yybegin(YYINITIAL); return SieveTokenTypes.MIME_KEYWORD; }
"preamble"            { yybegin(YYINITIAL); return SieveTokenTypes.PREAMBLE_KEYWORD; }
"uri"                 { yybegin(YYINITIAL); return SieveTokenTypes.URI_KEYWORD; }
"ip"                  { yybegin(YYINITIAL); return SieveTokenTypes.IP_KEYWORD; }
"exists"              { yybegin(YYINITIAL); return SieveTokenTypes.EXISTS_KEYWORD; }
"size"                { yybegin(YYINITIAL); return SieveTokenTypes.SIZE_KEYWORD; }
"score"               { yybegin(YYINITIAL); return SieveTokenTypes.SCORE_KEYWORD; }
"attribute"           { yybegin(YYINITIAL); return SieveTokenTypes.ATTRIBUTE_KEYWORD; }
"analyze"             { yybegin(YYINITIAL); return SieveTokenTypes.ANALYZE_KEYWORD; }

"("                   { yybegin(YYINITIAL); return SieveTokenTypes.LPAREN; }
")"                   { yybegin(YYINITIAL); return SieveTokenTypes.RPAREN; }
"{"                   { yybegin(YYINITIAL); return SieveTokenTypes.LBRACE; }
"}"                   { yybegin(YYINITIAL); return SieveTokenTypes.RBRACE; }
"["                   { yybegin(YYINITIAL); return SieveTokenTypes.LBRACKET; }
"]"                   { yybegin(YYINITIAL); return SieveTokenTypes.RBRACKET; }
";"                   { yybegin(YYINITIAL); return SieveTokenTypes.SEMICOLON; }
","                   { yybegin(YYINITIAL); return SieveTokenTypes.COMMA; }

{IDENTIFIER}          { yybegin(YYINITIAL); return SieveTokenTypes.UNKNOWN_KEYWORD; }
.                     { yybegin(YYINITIAL); return SieveTokenTypes.BAD_CHARACTER; }