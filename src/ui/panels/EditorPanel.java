package ui.panels;

import ui.CulverColor;
import ui.filetree.CulverFileTree;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditorPanel extends JPanel {

    public EditorPanel(){
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.SECONDARY_FOREGROUND);
        this.setBorder(new LineBorder(Color.DARK_GRAY.brighter(), 1));
    }

}
