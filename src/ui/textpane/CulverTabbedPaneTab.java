package ui.textpane;

import ui.colors.CulverColor;
import ui.buttons.CloseButton;
import ui.labels.SimpleLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CulverTabbedPaneTab extends JPanel {

    protected int index;
    protected EditorTextPane editorPane;

    public CulverTabbedPaneTab(CulverTabbedPane tabbedPane, EditorTextPane editorPane, String name){
        super();
        this.setOpaque(false);
        this.setBackground(CulverColor.TRANSPARENT);

        this.setName(name);
        this.index = this.setUpTab(tabbedPane);
        this.editorPane = editorPane;

    }

    public CulverTabbedPaneTab(CulverTabbedPane tabbedPane, EditorTextPane editorPane){
        this(tabbedPane, editorPane, "");
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    protected int setUpTab(CulverTabbedPane tabbedPane){
        SimpleLabel label = new SimpleLabel(this.getName());
        label.setBorder(new EmptyBorder(0, 0, 0, 4));
        this.add(label);

        CloseButton closeButton = new CloseButton("×");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(closeButton.getModel().isEnabled()){

                    editorPane.saveContentsToFile();

                    // TODO : Save contents of tab before closing
                    EventQueue.invokeLater(new Runnable(){
                        @Override
                        public void run(){
                            tabbedPane.removeTabAt(index);
                        }
                    });
                }
            }
        });
        this.add(closeButton);

        return tabbedPane.getTabCount() - 1;
    }

    public String getFilePath(){
        return this.editorPane.getFilePath();
    }



}
