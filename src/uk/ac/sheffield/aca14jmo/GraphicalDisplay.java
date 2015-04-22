package uk.ac.sheffield.aca14jmo;

import javax.swing.*;

/**
 * Created by joshua on 22/04/15.
 */
public class GraphicalDisplay extends JFrame implements Display {
    public final static int WIDTH = 640;
    public final static int HEIGHT = 480;

    public GraphicalDisplay() {
        setTitle("Chess Game");
        setSize(WIDTH, HEIGHT);
    }
    public void showPiecesOnBoard(Piece[][] data) {

    }

}
