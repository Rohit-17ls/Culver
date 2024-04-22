package ui.textpane;

import tokenizers.SyntaxHighlighter;
import tokenizers.SyntaxHighlighterFactory;
import ui.colors.CulverColor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class EditorTextPane extends JTextPane {

    protected String path;
    protected String fileExtension = "";
    protected SyntaxHighlighter syntaxHighlighter = null;
    protected long cursorOffset = 0;


    public EditorTextPane(){
        super();
        this.setBackground(CulverColor.PRIMARY_BACKGROUND);
        this.setForeground(CulverColor.WHITE);
        this.setCaretColor(CulverColor.SECONDARY_FOREGROUND);
        this.setFont(new Font("Consolas", Font.PLAIN, 16));
        this.syntaxHighlighter = SyntaxHighlighterFactory.defaultSyntaxHighlighter;

        this.path = null;
    }

    public EditorTextPane(File file){
        this();

        this.path = file.getAbsolutePath();
        this.setFileExtension(file);
        this.syntaxHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(this.fileExtension);
        this.setUpKeyListener();

        this.setText(file);

    }

    public String getFilePath(){
        return this.path;
    }

    public void setText(File file){
        try{
            Scanner scanner = new Scanner(file);
            String text = "";

            while(scanner.hasNext()){
                text += scanner.nextLine();
                if(scanner.hasNext()) text += "\n";
            }

            final String plainText = text;

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        LinkedHashMap<Integer, Color> codeMap = syntaxHighlighter.getHighlightedSyntax(plainText);
                        StyledDocument styledDocument = getStyledDocument();
                        Style style = addStyle(getFilePath(), null);

                        int ind = 0;

                        for (HashMap.Entry<Integer, Color> entry : codeMap.entrySet()) {
                            StyleConstants.setForeground(style, entry.getValue());
                            styledDocument.insertString(styledDocument.getLength(), plainText.substring(ind, entry.getKey()), style);
                            ind = entry.getKey();
                        }


                    } catch (BadLocationException ignored) {}

                }
            });

            System.out.println(text);
//            super.setText(text);
            scanner.close();

        }catch(FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }


    public void saveContentsToFile(){
        Document document = this.getDocument();
        String modifiedFileContents = "";
        try{
            modifiedFileContents = document.getText(0, document.getLength());
        }catch(BadLocationException ble){
            return;
        }

        if(this.path == null) return;

        try{
            File file = new File(this.path);
            PrintWriter writer = new PrintWriter(file);
            writer.printf("%s", modifiedFileContents);
            writer.close();

        }catch(FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }

    public void setFileExtension(File file){
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");

        if(index >= 0){
            this.fileExtension = fileName.substring(index);
        }
    }

    public void setCursorOffset(long offset){
        this.cursorOffset = offset;
    }

    protected void setUpKeyListener(){

        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(KeyUtilities.isNeglectableKeyCode(e.getKeyCode())) return;

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Document document = getDocument();
                            String plainText = document.getText(0, document.getLength());

                            int offset = getCaretPosition();
                            LinkedHashMap<Integer, Color> codeMap = syntaxHighlighter.getHighlightedSyntax(plainText);
                            setText("");
                            StyledDocument styledDocument = getStyledDocument();
                            Style style = addStyle(getFilePath(), null);

                            int ind = 0;

                            for (HashMap.Entry<Integer, Color> entry : codeMap.entrySet()) {
                                StyleConstants.setForeground(style, entry.getValue());
                                styledDocument.insertString(ind, plainText.substring(ind, entry.getKey()), style);
                                ind = entry.getKey();
                            }

                            System.out.println("OFFSET " + getCaretPosition());
                            setCaretPosition(offset);


                        } catch (BadLocationException ignored) {}

                    }
                });

            }

        });
    }


}
