package com.surfront.sieve.plugin.editor;

import com.surfront.sieve.plugin.parser.SieveTokenTypes;
import com.intellij.codeInsight.editorActions.CopyPastePreProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.RawText;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;

public class SieveLiteralCopyPasteProcessor implements CopyPastePreProcessor {

    public String preprocessOnCopy(PsiFile file, int[] startOffsets, int[] endOffsets, String text) {
        boolean isLiteral = true;
        for (int i = 0; i < startOffsets.length; i++) {
            if (findLiteralTokenType(file, startOffsets[i], endOffsets[i]) == null) {
                isLiteral = false;
                break;
            }
        }
        return isLiteral ? StringUtil.unescapeStringCharacters(text) : null;
    }

    public String preprocessOnPaste(Project project, PsiFile file, Editor editor, String text, RawText rawText) {
        Document document = editor.getDocument();
        PsiDocumentManager.getInstance(project).commitDocument(document);
        SelectionModel selectionModel = editor.getSelectionModel();

        // pastes in block selection mode (column mode) are not handled by a CopyPasteProcessor
        int selectionStart = selectionModel.getSelectionStart();
        int selectionEnd = selectionModel.getSelectionEnd();
        IElementType tokenType = findLiteralTokenType(file, selectionStart, selectionEnd);

        if (tokenType == SieveTokenTypes.STRING_LITERAL) {
            if (rawText != null && rawText.rawText != null)
                return rawText.rawText; // Copied from the string literal. Copy as is.

            text = StringUtil.escapeStringCharacters(text);
        }
        return text;
    }

    private static IElementType findLiteralTokenType(PsiFile file, int selectionStart, int selectionEnd) {
        PsiElement elementAtSelection = file.findElementAt(selectionStart);
        if (elementAtSelection == null)
            return null;
        IElementType tokenType = elementAtSelection.getNode().getElementType();
        if (tokenType != SieveTokenTypes.STRING_LITERAL)
            return null;
        TextRange textRange = elementAtSelection.getTextRange();
        if (selectionStart <= textRange.getStartOffset() || selectionEnd >= textRange.getEndOffset()) {
            return null;
        }
        return tokenType;
    }
}