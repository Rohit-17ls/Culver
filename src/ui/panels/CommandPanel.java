package ui.panels;

import ui.colors.CulverColor;
import ui.labels.SimpleLabel;
import ui.textfield.CommandField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CommandPanel extends JPanel {

    private static class InstanceHolder{
        private final static CommandPanel INSTANCE = new CommandPanel();
    }

    protected SimpleLabel commandLabel;
    protected CommandField commandField;

    private CommandPanel(){
        super();

        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setLayout(new GridBagLayout());

        this.addCommandLabel();
        this.addCommandField();

        this.setBorder(new EmptyBorder(3, 0, 3, 0));


    }

    public static CommandPanel getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public void addCommandLabel(){
        this.commandLabel = new SimpleLabel("--mode--");
        this.commandLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        this.commandLabel.setFont(new Font("Consolas", Font.ITALIC, 14));
        this.commandLabel.setForeground(CulverColor.YELLOW);
        this.commandLabel.setBorder(new EmptyBorder(0, 0, 0, 8));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(commandLabel, gbc);
    }

    public void addCommandField(){
        this.commandField = new CommandField();
        this.commandField.setBorder(new EmptyBorder(4, 2, 2, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        this.add(commandField, gbc);
    }



}
