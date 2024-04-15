package ui;

import javax.swing.*;

public class CulverMenuItem extends JMenuItem {

    public CulverMenuItem(String name){
        super(name);
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
    }
}
