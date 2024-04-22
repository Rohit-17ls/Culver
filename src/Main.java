import java.awt.*;

import ui.builders.CulverMenuBuilder;
import ui.builders.EditorPanelBuilder;
import ui.builders.UnknownMenuTypeException;

import listeners.OpenActionListener;
import listeners.TabChangeListener;
import ui.colors.CulverColor;
import ui.Window;
import ui.panels.BottomPanel;
import ui.panels.EditorPanel;
import ui.scroll.CulverScrollPane;
import ui.textpane.CulverTabbedPane;
import ui.textpane.CulverTabbedPaneTab;
import ui.textpane.EditorTextPane;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import java.lang.Runnable;


public class Main {

    private static final String title = "Culver";

    private static void configureUIManager(){
        UIManager.put("Tree.rendererFillBackground", false);
        UIManager.put("TabbedPane.background", CulverColor.SECONDARY_BACKGROUND);
        UIManager.put("TabbedPane.foreground", CulverColor.SECONDARY_FOREGROUND);
        UIManager.put("TabbedPane.focus", CulverColor.GREEN);
        UIManager.put("TabbedPane.selected", CulverColor.DARK_GRAY);
    }

    private static void setUpSkeleton(Window window){
        window.setLayout(new BorderLayout());

        try {

            OpenActionListener openActionListener = new OpenActionListener();

            CulverMenuBuilder menuBuilder = new CulverMenuBuilder(CulverMenuBuilder.MAIN_MENU);
            menuBuilder.setOpenActionListener(openActionListener);
            JMenuBar menuBar = menuBuilder.buildMainMenu();
            window.add(menuBar, BorderLayout.NORTH);

            BottomPanel bottomPanel = BottomPanel.getInstance();


            EditorPanelBuilder editorPanelBuilder = new EditorPanelBuilder(openActionListener);
            EditorPanel editorPanel = editorPanelBuilder.buildEditorPanel();

            editorPanel.getTabbedPane().caretListener = new CaretListener() {
                @Override
                public void caretUpdate(CaretEvent e) {
                    CulverScrollPane scrollWrapper = (CulverScrollPane) editorPanel.getTabbedPane().getSelectedComponent();
                    EditorTextPane editorTextPane = (EditorTextPane)scrollWrapper.getComponent();
                    bottomPanel.setCurrentCursorPosition(editorTextPane.getText(), e.getMark());
//                    editorTextPane.setCursorOffset(offset);
                }
            };

            window.add(editorPanel, BorderLayout.CENTER);


            editorPanel.getTabbedPane().addChangeListener(new TabChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    CulverTabbedPane tabbedPane = (CulverTabbedPane) e.getSource();
                    int index = tabbedPane.getSelectedIndex();

                    CulverTabbedPaneTab tab = tabbedPane.getTabAt(index);

                    bottomPanel.setCurrentFileName(tab.getFilePath());
                }
            });

            window.add(bottomPanel, BorderLayout.SOUTH);

        }catch(UnknownMenuTypeException umte){
            umte.printStackTrace();
        }
    }

    public static void main(final String[] args) {

        Runnable runnable = new Runnable(){

            @Override
            public void run(){
                Window window = new Window(title);
                window.getContentPane().setBackground(new Color(9, 9, 11));
                window.setExtendedState(JFrame.MAXIMIZED_BOTH);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);

                configureUIManager();
                setUpSkeleton(window);
            }

        };

        EventQueue.invokeLater(runnable);

    }
}