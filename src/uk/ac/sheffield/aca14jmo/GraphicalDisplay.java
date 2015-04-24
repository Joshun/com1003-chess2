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
    private JButton[][] buttons = new JButton[BOARD_WIDTH][BOARD_HEIGHT];
    private HumanPlayerState humanPlayerState;

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("ButtonHandler event: " + e);
            JButton source = (JButton)e.getSource();

            int x=0, y=0;
            boolean buttonFound = false;
            for(int i=0; i<BOARD_HEIGHT; i++) {
                for (int j=0; j<BOARD_WIDTH; j++) {
                    if (buttonFound) {
                        break;
                    }
                    else if (source == buttons[i][j]) {
                        x = j;
                        y = i;
                        buttonFound = true;
                        break;
                    }
                }
            }
            System.out.println("Button clicked: " + x + "," + y);

            humanPlayerState.click(x, y);
            Chess.processMoves(x, y);
        }
    }

    public GraphicalDisplay(HumanPlayerState humanPlayerState) {
        this.humanPlayerState = humanPlayerState;
        setTitle("Chess Game");
        setSize(WIDTH, HEIGHT);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(BOARD_WIDTH, BOARD_HEIGHT));
        for (int i=0; i<BOARD_HEIGHT; i++) {
            for (int j=0; j<BOARD_WIDTH; j++) {
                JButton button = new JButton("x");
                button.addActionListener(new ButtonHandler());
                contentPane.add(button);
                buttons[i][j] = button;
            }
        }
        setVisible(true);
    }
    public void showPiecesOnBoard(Piece[][] data) {
        for (int i=0; i<data.length; i++) {
            for (int j=0; j<data.length; j++) {
                char buttonText;
                if (data[i][j] != null) {
                    buttonText = data[i][j].getChar();
                }
                else {
                    buttonText = '-';
                }
                buttons[i][j].setText(String.valueOf(buttonText));
            }
        }
    }

}
