//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.awt.*;

import builders.CulverMenuBuilder;
import builders.EditorPanelBuilder;
import builders.UnknownMenuTypeException;

import listeners.OpenActionListener;
import ui.CulverColor;
import ui.Window;
import ui.panels.BottomPanel;
import builders.CulverBottomPanelBuilder;
import ui.panels.EditorPanel;

import javax.swing.*;
import java.lang.Runnable;

/*
C:\Users\Rohit\Music\Projects\Social-Network-Management-System\Client\node_modules
C:\Users\Rohit\Music\Projects\Social-Network-Management-System
 */

public class Main {

    private static final String title = "Culver";

    private static void configureUIManager(){
        UIManager.put("Tree.rendererFillBackground", false);
        UIManager.put("TabbedPane.background", CulverColor.SECONDARY_BACKGROUND);
        UIManager.put("TabbedPane.foreground", CulverColor.SECONDARY_FOREGROUND);
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

            EditorPanel editorPanel = new EditorPanelBuilder(openActionListener).buildEditorPanel();
            window.add(editorPanel, BorderLayout.CENTER);


            BottomPanel bottomPanel = new CulverBottomPanelBuilder().buildBottomPanel();
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