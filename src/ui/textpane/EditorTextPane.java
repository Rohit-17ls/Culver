package ui.textpane;

import ui.CulverColor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EditorTextPane extends JTextPane {

    public EditorTextPane(){
        super();
        this.setBackground(CulverColor.PRIMARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
        this.setCaretColor(CulverColor.SECONDARY_FOREGROUND);
        this.setFont(new Font("Consolas", Font.PLAIN, 16));
    }

    public EditorTextPane(File file){
        this();
        this.setText(file);
    }

    public void setText(File file){
        try{
            Scanner scanner = new Scanner(file);
            String text = "";

            while(scanner.hasNext()){
                text += scanner.nextLine();
                if(scanner.hasNext()) text += "\n";
            }

            super.setText(text);
            scanner.close();

        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
    }

}
