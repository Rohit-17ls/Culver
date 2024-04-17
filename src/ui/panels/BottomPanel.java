package ui.panels;

import ui.CulverColor;
import ui.labels.SimpleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class BottomPanel extends JPanel {

    protected SimpleLabel fileNameLabel;
    protected SimpleLabel cursorPositionLabel;

    public BottomPanel(){
        super();
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
    }

    public void setFileNameLabel(SimpleLabel fileNameLabel){
        this.fileNameLabel = fileNameLabel;

        this.fileNameLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(Desktop.isDesktopSupported()){
                    Desktop desktop = Desktop.getDesktop();
                    if(desktop.isSupported(Desktop.Action.OPEN)){
                        try{
                            File file = new File(fileNameLabel.getText());
                            desktop.open(new File(file.getParent()));
                        }catch(IOException ignored){}
                    }
                }
            }
        });
    }

    public void setCursorPositionLabel(SimpleLabel cursorPositionLabel){
        this.cursorPositionLabel = cursorPositionLabel;
    }

    public void setCurrentFileName(String name){
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                fileNameLabel.setText(name);
            }
        };

        EventQueue.invokeLater(runnable);
    }

    public void setCurrentCursorPosition(String text, int mark){
        final String[] lines = text.substring(0, mark).split("\n");
        int lineNumber = lines.length;
        int colNumber = lines[lineNumber - 1].length();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cursorPositionLabel.setText(lineNumber + ":" + colNumber);
            }
        };

        EventQueue.invokeLater(runnable);

    }



}
