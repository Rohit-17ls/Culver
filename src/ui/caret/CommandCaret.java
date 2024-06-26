package ui.caret;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class CommandCaret extends DefaultCaret {
    private String mark = "|";

    public CommandCaret() {
        setBlinkRate(500);
    }

    @Override
    protected synchronized void damage(Rectangle r) {
        if (r == null) {
            return;
        }

        JTextComponent comp = getComponent();
        FontMetrics fm = comp.getFontMetrics(comp.getFont());
        int textWidth = fm.stringWidth(this.mark);
        int textHeight = fm.getHeight();
        x = r.x;
        y = r.y;
        width = textWidth;
        height = textHeight;
        repaint(); // calls getComponent().repaint(x, y, width, height)
    }

    @Override
    public void paint(Graphics g) {
        JTextComponent comp = getComponent();
        if (comp == null) {
            return;
        }

        int dot = getDot();
        Rectangle r = null;
        try {
            r = comp.modelToView(dot);
        } catch (BadLocationException e) {
            return;
        }
        if (r == null) {
            return;
        }

        if ((x != r.x) || (y != r.y)) {
            repaint(); // erase previous location of caret
            damage(r);
        }

        if (isVisible()) {
            FontMetrics fm = comp.getFontMetrics(comp.getFont());
            int textWidth = fm.stringWidth(">");
            int textHeight = fm.getHeight();

            g.setColor(comp.getCaretColor());
            g.drawString(mark, x, y + fm.getAscent());
        }
    }

}
