package ui;

import ui.colors.CulverColor;

import javax.swing.*;

public class CulverMenu extends JMenu {

    public CulverMenu(String name){
        super(name);
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
    }

}
