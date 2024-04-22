package ui.filetree;

import ui.colors.CulverColor;
import ui.cellrenderer.CulverCellRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class CulverFileTree extends JTree {

    public void clearTree(String treeTitle){
        DefaultTreeModel model = (DefaultTreeModel) this.treeModel;
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();
        root.setUserObject(treeTitle);
        model.reload();

    }

    public CulverFileTree(){
        super();

        this.setCellRenderer(new CulverCellRenderer());
        this.setBackground(CulverColor.SECONDARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
        this.setBorder(new EmptyBorder(1, 10,1 ,30 ));

        clearTree("Project");

    }

}
