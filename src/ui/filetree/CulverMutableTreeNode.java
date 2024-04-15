package ui.filetree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;

public class CulverMutableTreeNode extends DefaultMutableTreeNode {

    protected boolean isNonLeafNode;
    protected boolean isExpanded;
    protected String path;

    // Storing the path along with each node
    // => Since there is no way to get the path of a nested node (after the file tree has been laid out initially) without a traversal
    public CulverMutableTreeNode(Object userObject, String path){
        super(userObject);
        this.path = path;
        this.isNonLeafNode = false;
        this.isExpanded = false;
    }


    public boolean getIsLeafNode(){
        return !this.isNonLeafNode;
    }

    public boolean getIsNonLeafNode(){
        return this.isNonLeafNode;
    }

    public boolean getIsExpanded(){
        return this.isExpanded;
    }

    public void setIsExpanded(boolean isExpanded){
        this.isExpanded = isExpanded;
    }

    public void setIsNonLeafNode(boolean isExpandable){
        this.isNonLeafNode = isExpandable;
    }

    public String getFilePath(){
        return this.path;
    }

    public void expand(JTree fileTree){

        CulverMutableTreeNode thisNode = this;

        Runnable runnable = new Runnable(){
            @Override
            public void run(){

                // Remove the placeholder node with the absolute file path
                thisNode.removeAllChildren();

                File directory = new File(thisNode.path);
                File[] files = directory.listFiles();

                DefaultTreeModel model = (DefaultTreeModel) fileTree.getModel();

                for(File file : files){
                    CulverMutableTreeNode newNode = new CulverMutableTreeNode(file.getName(), file.getAbsolutePath());

                    if(file.isDirectory()){
                        newNode.setIsNonLeafNode(true);
                        newNode.add(new CulverMutableTreeNode(file.getAbsolutePath(), null));
                    }

                    thisNode.add(newNode);
                }

                model.reload(thisNode);

            }
        };

        EventQueue.invokeLater(runnable);
    }




}
