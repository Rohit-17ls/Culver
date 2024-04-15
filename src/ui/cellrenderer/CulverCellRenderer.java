package ui.cellrenderer;

import ui.CulverColor;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

//public class CulverCellRenderer implements TreeCellRenderer {
public class CulverCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus){
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        setForeground(CulverColor.SECONDARY_FOREGROUND);

        return component;
    }

    private Component getTreeCellRenderedComponentCustom(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus){
        JLabel label = new JLabel(value.toString());
        label.setBackground(CulverColor.SECONDARY_BACKGROUND);
        label.setForeground(CulverColor.SECONDARY_FOREGROUND);

        return label;
    }

}
