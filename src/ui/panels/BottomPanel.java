package ui.panels;

import ui.builders.CulverBottomPanelBuilder;
import editor.GitUtilities;
import editor.Project;
import ui.colors.CulverColor;
import ui.labels.SimpleLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

public class BottomPanel extends JPanel {

    private static class InstanceHolder{
        private static final BottomPanel INSTANCE = new CulverBottomPanelBuilder().buildBottomPanel(new BottomPanel());
    }

    protected SimpleLabel fileNameLabel;
    protected SimpleLabel gitLabel;
    protected SimpleLabel cursorPositionLabel;
    protected int cursorOffset;

    public BottomPanel(){
        super();
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
        this.setBorder(new EmptyBorder(0, 0, 5, 0));
    }

    public static BottomPanel getInstance(){
        return InstanceHolder.INSTANCE;
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
                        }catch(Exception ignored){}
                    }
                }
            }
        });
    }

    public void setGitLabel(SimpleLabel gitLabel){
        this.gitLabel = gitLabel;
    }

    public void setCursorPositionLabel(SimpleLabel cursorPositionLabel){
        this.cursorPositionLabel = cursorPositionLabel;
    }

    public void setCurrentFileName(String name){
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                try{
                    File file = new File(name);
                    fileNameLabel.setText(String.format("%s \t %dB", name, file.length()));
                }catch(Exception e){
                    fileNameLabel.setText("NO FILE SELECTED \t 0B");
                }

            }
        };

        EventQueue.invokeLater(runnable);
    }

    public void setGitDetails(){
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                Project project = Project.getInstance();
                if(project.isGitInitialized()){
                    try{
                        HashMap<String, String> gitConfigMap = GitUtilities.getGitConfig(Project.getInstance().getProjectPath());
                        String gitDetails = "";

                        if(gitConfigMap.containsKey("branch")){
                            gitDetails = String.format("%s %s", gitDetails, gitConfigMap.get("branch"));
                        }
                        gitLabel.setText(gitDetails.trim());

                    }catch(Exception ignore){}
                }
            }
        };

        EventQueue.invokeLater(runnable);
    }

    public int getCursorOffset(){
        return this.cursorOffset;
    }

    public void setCurrentCursorPosition(String text, int mark){

        this.cursorOffset = mark;
        int lines = 0;
        int lastLineIndex = 0;
        for(int i = 0; i < mark; i++){
            if(text.charAt(i) == '\n'){
                lines++;
                lastLineIndex = i + 1;
            }
        }

        int lineNumber = lines + 1;
        int colNumber = mark - lastLineIndex + 1;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cursorPositionLabel.setText(lineNumber + ":" + colNumber);
            }
        };


        EventQueue.invokeLater(runnable);


    }



}
