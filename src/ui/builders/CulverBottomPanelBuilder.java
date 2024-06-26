package ui.builders;

import ui.colors.CulverColor;
import ui.labels.SimpleLabel;
import ui.panels.BottomPanel;
import ui.panels.CommandPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CulverBottomPanelBuilder{

    public CulverBottomPanelBuilder(){}

    public BottomPanel buildBottomPanel(BottomPanel bottomPanel){

        bottomPanel.setLayout(new BorderLayout());

        SimpleLabel fileNameLabel = new SimpleLabel("Welcome");
        fileNameLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

        SimpleLabel gitLabel = new SimpleLabel("<no git>");

        SimpleLabel cursorPositionLabel = new SimpleLabel(0 + ":" + 0);
        cursorPositionLabel.setBorder(new EmptyBorder(0, 0, 0, 10));

        bottomPanel.setFileNameLabel(fileNameLabel);
        bottomPanel.setCursorPositionLabel(cursorPositionLabel);
        bottomPanel.setGitLabel(gitLabel);

        bottomPanel.add(CommandPanel.getInstance(), BorderLayout.NORTH);


        JPanel wrapper = new JPanel();
        wrapper.setBackground(CulverColor.SECONDARY_BACKGROUND);
        wrapper.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1;
        wrapper.add(fileNameLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        wrapper.add(gitLabel, gbc);
        gbc.gridx = 2; gbc.weightx = 1;
        wrapper.add(cursorPositionLabel, gbc);

        bottomPanel.add(wrapper, BorderLayout.SOUTH);


        return bottomPanel;

    }

}
