package builders;

import ui.labels.SimpleLabel;
import ui.panels.BottomPanel;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CulverBottomPanelBuilder{

    public CulverBottomPanelBuilder(){}

    public BottomPanel buildBottomPanel(){
        BottomPanel bottomPanel = new BottomPanel();

        bottomPanel.setLayout(new GridLayout(1, 2));

        SimpleLabel fileNameLabel = new SimpleLabel("Welcome");
        fileNameLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

        SimpleLabel cursorPositionLabel = new SimpleLabel(0 + ":" + 0);


        bottomPanel.add(fileNameLabel);
        bottomPanel.add(cursorPositionLabel);

        return bottomPanel;

    }

}
