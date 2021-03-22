package app;

import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;

public final class GraphicsTry extends Canvas {
    static final long serialVersionUID = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("My GraphicsTry");
        Canvas canvas = new GraphicsTry();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.fillOval(100, 100, 200, 200);
    }
}
