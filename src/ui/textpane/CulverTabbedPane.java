package ui.textpane;

import ui.CulverColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CulverTabbedPane extends JTabbedPane {

    protected ArrayList<CulverTabbedPaneTab> tabs = new ArrayList<>();

    public CulverTabbedPane(){
        super();
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setForeground(CulverColor.WHITE);
    }

    @Override
    public void addTab(String title, Component component){
        super.addTab(title, component);

        CulverTabbedPaneTab newTab = new CulverTabbedPaneTab(this, title);
        tabs.add(newTab);

        System.out.println(this.getTabCount()-1);
        this.setTabComponentAt(this.getTabCount() - 1, newTab);
    }

    @Override
    public void removeTabAt(int index){
        super.removeTabAt(index);
        System.out.println(this.getTabCount()-1);
        this.tabs.remove(index);

        for(int i = 0; i < this.tabs.size(); i++){
            this.tabs.get(i).setIndex(i);
        }
    }


}
