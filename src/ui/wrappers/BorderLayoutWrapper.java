package ui.wrappers;

import ui.CulverColor;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutWrapper extends JPanel {

    public BorderLayoutWrapper(){
        super();

        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setLayout(new BorderLayout());
    }

    public void addNorthComponent(Component northComponent){
        if(northComponent != null){
            this.add(northComponent, BorderLayout.NORTH);
        }
    }
    public void addWestComponent(Component westComponent){
        if(westComponent != null){
            this.add(westComponent, BorderLayout.WEST);
        }
    }

    public void addSouthComponent(Component southComponent){
        if(southComponent != null){
            this.add(southComponent, BorderLayout.SOUTH);
        }
    }

    public void addEastComponent(Component eastComponent){
        if(eastComponent != null){
            this.add(eastComponent, BorderLayout.EAST);
        }
    }

    public void addCenterComponent(Component centerComponent){
        if(centerComponent != null){
            this.add(centerComponent, BorderLayout.CENTER);
        }
    }

    public void wrapComponents(Component northComponent,
                               Component westComponent,
                               Component southComponent,
                               Component eastComponent,
                               Component centerComponent){

        this.addNorthComponent(northComponent);
        this.addWestComponent(westComponent);
        this.addSouthComponent(southComponent);
        this.addEastComponent(eastComponent);
        this.addCenterComponent(centerComponent);


    }

}
