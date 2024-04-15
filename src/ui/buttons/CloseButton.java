package ui.buttons;

import ui.CulverColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CloseButton extends JButton {

    public CloseButton(String text){
        super(text);

        this.setFont(new Font("Courier new", Font.BOLD, 18));

        this.setOpaque(false);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setContentAreaFilled(false);
        this.setForeground(CulverColor.SECONDARY_FOREGROUND);

        this.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                if(getModel().isArmed()) {
                    setForeground(CulverColor.WHITE);
                }else if(getModel().isRollover()){
                    setForeground(CulverColor.WHITE.darker());
                }else{
                    setForeground(CulverColor.SECONDARY_FOREGROUND);
                }
            }
        });
    }


}
