package builders;

import listeners.OpenActionListener;
import ui.CulverColor;
import ui.filetree.CulverFileTree;
import ui.filetree.CulverMutableTreeNode;
import ui.textpane.CulverTabbedPane;
import ui.panels.EditorPanel;
import ui.panels.WrapperPanel;
import ui.scroll.CulverScrollPane;
import ui.textpane.EditorTextPane;

import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class EditorPanelBuilder {

    protected OpenActionListener openActionListener;

    public EditorPanelBuilder(){
        super();
    }

    public EditorPanelBuilder(OpenActionListener openActionListener){
        this.openActionListener = openActionListener;
    }

    public EditorPanel buildEditorPanel(){
        EditorPanel editorPanel = new EditorPanel();

        WrapperPanel wrapper = new WrapperPanel(new MatteBorder(0, 0, 0, 1, CulverColor.DARK_GRAY.brighter()));
        CulverFileTree fileTree = (CulverFileTree) wrapper.wrap(new CulverFileTree());
        editorPanel.setFileTree(fileTree);

        openActionListener.addListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // CulverFileTree needs to be updated -> UI update. So it needs to use invokeLater so that it is ran on the ED Thread

                Runnable runnable = new Runnable(){
                    @Override
                    public void run(){
                        String path = openActionListener.path;

                        fileTree.clearTree("");

                        try{
                            File directory = new File(path);
                            File[] files = directory.listFiles();

                            DefaultTreeModel model = (DefaultTreeModel) fileTree.getModel();
                            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
                            root.setUserObject(directory.getName());

                            for(File file : files){
                                CulverMutableTreeNode newNode = new CulverMutableTreeNode(file.getName(), file.getAbsolutePath());

                                if(file.isDirectory()){
                                    newNode.setIsNonLeafNode(true);
                                    newNode.add(new CulverMutableTreeNode(file.getName(), null));
                                }
                                model.insertNodeInto(newNode, root, root.getChildCount());

                            }
                            model.nodeChanged(root);
                        }catch(NullPointerException npe){
                            return;
                        }

                    }

                };

                EventQueue.invokeLater(runnable);

            }
        });

        editorPanel.add(new CulverScrollPane(wrapper), BorderLayout.WEST);

        EditorTextPane editorTextPane = new EditorTextPane();

        CulverTabbedPane tabbedPane = new CulverTabbedPane();
        editorPanel.setTabbedPane(tabbedPane);
        tabbedPane.addTab("Welcome", editorTextPane);
        editorTextPane.setText("Pick a project/folder to get started : file -> open\nC:\\Users\\Rohit\\Music\\Projects\\Suave");


        editorPanel.add(tabbedPane, BorderLayout.CENTER);


        // TODO : Make tree node collapse a single click event
        fileTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();

                    if(node instanceof CulverMutableTreeNode){
                        CulverMutableTreeNode culverNode = (CulverMutableTreeNode) node;
                        if(!culverNode.getIsExpanded() && culverNode.getIsNonLeafNode()){
                            culverNode.setIsExpanded(true);
                            culverNode.expand(fileTree);
                        }else if(!culverNode.getIsNonLeafNode()){
                            EventQueue.invokeLater(new Runnable(){
                                @Override
                                public void run(){
                                    File file = new File(culverNode.getFilePath());
                                    EditorTextPane editorTextPane = new EditorTextPane(file);


                                    tabbedPane.addTab(culverNode.getUserObject().toString(), new EditorTextPane(file));
                                }
                            });
                        }
                    }
                }
            }
        });




        return editorPanel;
    }

}
