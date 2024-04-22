package ui.panels;

import ui.colors.CulverColor;
import ui.filetree.CulverFileTree;
import ui.textpane.CulverTabbedPane;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditorPanel extends JPanel {

    protected CulverTabbedPane tabbedPane;
    protected CulverFileTree fileTree;


    public EditorPanel(){
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.SECONDARY_FOREGROUND);
        this.setBorder(new LineBorder(Color.DARK_GRAY.brighter(), 1));
    }

    public EditorPanel(CulverTabbedPane tabbedPane, CulverFileTree fileTree){
        this.tabbedPane = tabbedPane;
        this.fileTree = fileTree;
    }

    public void setTabbedPane(CulverTabbedPane tabbedPane){
        this.tabbedPane = tabbedPane;
    }

    public CulverTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    public void setFileTree(CulverFileTree fileTree){
        this.fileTree = fileTree;
    }

    public CulverFileTree getFileTree() {
        return this.fileTree;
    }

}
