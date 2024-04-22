package ui.colors;

import java.awt.*;

public class EditorColor extends Color {

    public static final Color EDITOR_GREEN = Color.GREEN.darker();
    public static final Color EDITOR_RED = Color.RED.brighter();
    public static final Color EDITOR_WHITE = Color.WHITE;
    public static final Color EDITOR_COMMENT_COLOR = Color.LIGHT_GRAY;
    public static final Color EDITOR_YELLOW = Color.YELLOW;



    public EditorColor(int r, int g, int b){
        super(r, g, b);
    }

}
