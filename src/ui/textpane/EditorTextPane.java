package ui.textpane;

import ui.CulverColor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EditorTextPane extends JTextPane {

    protected String path;

    public EditorTextPane(){
        super();
        this.setBackground(CulverColor.PRIMARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
        this.setCaretColor(CulverColor.SECONDARY_FOREGROUND);
        this.setFont(new Font("Consolas", Font.PLAIN, 16));

        this.path = null;
    }

    public EditorTextPane(File file){
        this();
        this.setText(file);

        this.path = file.getAbsolutePath();
    }

    public String getFilePath(){
        return this.path;
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
            System.err.println(fnfe.getMessage());
        }
    }


    public void saveContentsToFile(){
        String modifiedFileContents = this.getText();

        if(this.path == null) return;

        try{
            File file = new File(this.path);
            PrintWriter writer = new PrintWriter(file);
            writer.printf("%s", modifiedFileContents);
            writer.close();

        }catch(FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }


}
