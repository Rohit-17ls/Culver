package tokenizers;

import java.awt.*;
import java.util.LinkedHashMap;

public interface SyntaxHighlighter {

    public LinkedHashMap<Integer, Color> getHighlightedSyntax(String text);
}
