package listeners;

import editor.Project;
import ui.panels.BottomPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OpenActionListener implements ActionListener {

    protected ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
    public String path;

    public OpenActionListener(){}

    public void addListener(ActionListener actionListener){
        actionListeners.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int status = fileChooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION){
            this.path = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println(path);
            Project.getInstance().setProjectPath(path);
            BottomPanel.getInstance().setGitDetails();

        }

        ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Folder Opened");
        for(ActionListener actionListener : actionListeners){
            actionListener.actionPerformed(actionEvent);
        }
    }

}
