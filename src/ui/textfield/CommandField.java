package ui.textfield;

import ui.CulverColor;
import ui.caret.CommandCaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CommandField extends JTextField {

    public CommandField(int cols){
        super(cols);
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.COMMAND_COLOR);
        this.setCaret(new CommandCaret());
        this.setCaretColor(CulverColor.SECONDARY_FOREGROUND);

        this.setFont(new Font("Consolas", Font.PLAIN, 15));

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(CulverColor.SECONDARY_BACKGROUND.brighter());
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(CulverColor.SECONDARY_BACKGROUND);
            }
        });
    }

    public CommandField(){
        this(0);
    }

}
