package ui.labels;

import ui.colors.CulverColor;

import javax.swing.*;

public class SimpleLabel extends JLabel {

    public SimpleLabel(String text){
        super(text);
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
    }
}
