package builders;


import listeners.OpenActionListener;
import ui.CulverColor;
import ui.CulverMenu;
import ui.CulverMenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CulverMenuBuilder {

    public static int MAIN_MENU = 1;
    private OpenActionListener openActionListener;

    public int menuType;

    public CulverMenuBuilder(final int menu_choice) throws UnknownMenuTypeException{
        if((menu_choice & MAIN_MENU) == MAIN_MENU){
            this.menuType = MAIN_MENU;
        }else{
            throw new UnknownMenuTypeException("Menu Choice " + menu_choice + " is not a valid menu choice");
        }
    }

    public void setOpenActionListener(OpenActionListener openActionListener){
        this.openActionListener = openActionListener;
    }

    public JMenuBar buildMainMenu(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(CulverColor.SECONDARY_BACKGROUND);

        CulverMenu fileMenu = new CulverMenu("file");

        CulverMenuItem newMenuItem = new CulverMenuItem("new");
        fileMenu.add(newMenuItem);

        CulverMenuItem openMenuItem = new CulverMenuItem("open");
        openMenuItem.addActionListener(this.openActionListener);
        fileMenu.add(openMenuItem);

        CulverMenuItem exitMenuItem = new CulverMenuItem("exit");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        menuBar.setBorder(new EmptyBorder(0, 0, 5, 0));


        return menuBar;
    }


}
