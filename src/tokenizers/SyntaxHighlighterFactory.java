package tokenizers;

import java.awt.*;
import java.util.LinkedHashMap;

public class SyntaxHighlighterFactory {

    public static SyntaxHighlighter defaultSyntaxHighlighter = new SyntaxHighlighter() {
        @Override
        public LinkedHashMap<Integer, Color> getHighlightedSyntax(String text) {
            LinkedHashMap<Integer, Color> codeMap = new LinkedHashMap<>();
            codeMap.put(text.length(), Color.WHITE);

            return codeMap;
        }
    };

    public static SyntaxHighlighter getSyntaxHighlighter(String fileExtension){
        if(".c".equals(fileExtension) || ".h".equals(fileExtension)){
            return new CSyntaxHighlighter();
        }

        return defaultSyntaxHighlighter;
    }

}
