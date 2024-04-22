package ui.colors;

import java.awt.*;

public class CulverColor extends Color {

    public static final Color PRIMARY_BACKGROUND = new Color(9, 9, 11);
    public static final Color SECONDARY_BACKGROUND = new Color(30, 31, 34);
    public static final Color SECONDARY_FOREGROUND = Color.LIGHT_GRAY;
    public static final Color SCROLLBAR_THUMB_COLOR = new Color(76, 76, 84);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    public static final Color COMMAND_COLOR = new Color(255, 75, 206);

    public CulverColor(int r, int g, int b){
        super(r, g, b);
    }

    public static String toStringFormat(Color color){
        if(color.getColorSpace().isCS_sRGB()){
            return String.format("rgb(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
        }

        return color.toString();
    }

    public static String rgb_to_hex(Color color){
        if(color.getColorSpace().isCS_sRGB()){
            String r = Integer.toHexString(color.getRed());
            String g = Integer.toHexString(color.getGreen());
            String b = Integer.toHexString(color.getBlue());

            r = r.length() == 1 ? "0" + r : r;
            g = g.length() == 1 ? "0" + g : g;
            b = b.length() == 1 ? "0" + b : b;

            return String.format("#%s%s%s", r, g, b);
        }

        return color.toString();
    }

}
