package ui.textpane;

import listeners.TabChangeListener;
import ui.CulverColor;
import ui.scroll.CulverScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretListener;
import java.awt.*;
 import java.util.ArrayList;

public class CulverTabbedPane extends JTabbedPane {

    protected ArrayList<CulverTabbedPaneTab> tabs = new ArrayList<>();
    public static CaretListener caretListener;

    public CulverTabbedPane(){
        super();
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setForeground(CulverColor.WHITE);
    }

    @Override
    public void addTab(String title, Component component){
        super.addTab(title, new CulverScrollPane(component));

        EditorTextPane editorTextPane = (EditorTextPane) component;
        editorTextPane.addCaretListener(caretListener);

        CulverTabbedPaneTab newTab = new CulverTabbedPaneTab(this, editorTextPane, title);

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

    public void addTabChangeListener(TabChangeListener listener){
        super.addChangeListener(listener);
    }

    public void removeTabChangeListener(TabChangeListener listener){
        super.removeChangeListener(listener);
    }

    public CulverTabbedPaneTab getTabAt(int index){
        return this.tabs.get(index);
    }

}
