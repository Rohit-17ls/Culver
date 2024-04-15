package ui.textpane;

import ui.CulverColor;
import ui.buttons.CloseButton;
import ui.labels.SimpleLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CulverTabbedPaneTab extends JPanel {

    protected int index;

    public CulverTabbedPaneTab(CulverTabbedPane tabbedPane, String name){
        super();
        this.setOpaque(false);
        this.setBackground(CulverColor.TRANSPARENT);

        this.setName(name);
        this.index = this.setUpTab(tabbedPane);

    }

    public CulverTabbedPaneTab(CulverTabbedPane tabbedPane){
        super();
        this.index = this.setUpTab(tabbedPane);
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



}
