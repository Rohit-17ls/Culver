package ui.panels;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class WrapperPanel extends JPanel {

    public WrapperPanel(AbstractBorder border){
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(border);
    }

    public JComponent wrap(JComponent component){
        this.add(component, BorderLayout.WEST);
        this.setBackground(component.getBackground());
        this.setForeground(component.getForeground());

        return component;
    }

}
