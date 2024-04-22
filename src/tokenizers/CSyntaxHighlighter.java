package tokenizers;

import ui.colors.CulverColor;
import ui.colors.EditorColor;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CSyntaxHighlighter extends Tokenizer implements SyntaxHighlighter{

    private static final String[] keywords = {
            // Data Types
            "auto", "char", "const", "double", "enum", "float", "int", "long", "short", "signed", "struct", "typedef", "union", "unsigned", "void", "volatile",

            // Control Flow
            "break", "case", "continue", "default", "do", "else", "for", "goto", "if", "return", "static", "switch", "while",

            // Storage Classes
            "extern", "register",

            // Preprocessor Directives
            "#include", "#define", "#ifdef", "#ifndef", "#if", "#else", "#elif", "#endif", "#error", "#warning", "#pragma once", "#line",

            // Other
            "sizeof",
    };;

    CSyntaxHighlighter(){
        super();
        this.addPatterns();
    }

    protected void addPatterns(){
        for(String keyword : keywords){
            this.addPattern(keyword, EditorColor.GREEN);
        }

        this.addPattern(PATTERN_IDENTIFIER, EditorColor.WHITE);
        this.addPattern(Pattern.compile("<[a-zA-Z0-9\\.]+>"), EditorColor.YELLOW);
        this.addPattern(PATTERN_NUMERIC_LITERAL, EditorColor.RED);
        this.addPattern(PATTERN_PUNCTUATOR, EditorColor.WHITE);
        this.addPattern(PATTERN_SPACES, EditorColor.WHITE);
        this.addPattern(PATTERN_SINGLE_LINE_COMMENT, EditorColor.LIGHT_GRAY);
        this.addPattern(PATTERN_STRING_LITERAL, EditorColor.YELLOW);
    }

    @Override
    public LinkedHashMap<Integer, Color> getHighlightedSyntax(String text){
        return super.getStyledText(text);
    }
}
