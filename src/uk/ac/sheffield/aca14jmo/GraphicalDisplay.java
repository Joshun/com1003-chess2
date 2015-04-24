package uk.ac.sheffield.aca14jmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by joshua on 22/04/15.
 */
public class GraphicalDisplay extends JFrame implements Display {
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("ButtonHandler event: " + e);
        }
    }

    public GraphicalDisplay() throws InterruptedException {
        setTitle("Chess Game");
        setSize(WIDTH, HEIGHT);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(BOARD_WIDTH, BOARD_HEIGHT));
        for (int i=0; i<BOARD_HEIGHT; i++) {
            for (int j=0; j<BOARD_WIDTH; j++) {
                JButton button = new JButton("x");
                button.addActionListener(new ButtonHandler());
                contentPane.add(button);
            }
        }
        setVisible(true);
    }
    public void showPiecesOnBoard(Piece[][] data) {

    }

}
