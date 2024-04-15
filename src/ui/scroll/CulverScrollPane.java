package ui.scroll;
import ui.CulverColor;

import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CulverScrollPane extends JScrollPane{

    public CulverScrollPane(Component component){
        super(component);
        this.getVerticalScrollBar().setBackground(CulverColor.PRIMARY_BACKGROUND);
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = CulverColor.SCROLLBAR_THUMB_COLOR;
            }
        });

        this.getHorizontalScrollBar().setBackground(CulverColor.PRIMARY_BACKGROUND);
        this.getHorizontalScrollBar().setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = CulverColor.SCROLLBAR_THUMB_COLOR;
            }

        });
    }

}
