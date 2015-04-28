package uk.ac.sheffield.aca14jmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by joshua on 22/04/15.
 */
public class GraphicalDisplay extends JFrame implements Display {
    public enum ClickState {
        INITIAL, CLICK_START, CLICK_END;
    }

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;
    private ChessButton[][] buttons = new ChessButton[BOARD_WIDTH][BOARD_HEIGHT];
    private ClickState currentState;
    private int startX, startY;
    private int endX, endY;

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("ButtonHandler event: " + e);
            ChessButton source = (ChessButton)e.getSource();
            int x = source.getXValue();
            int y = source.getYValue();

            source.setBackground(Color.lightGray);
            source.setBorderPainted(false);
            source.setOpaque(true);

            System.out.println("Button clicked: " + x + "," + y);
            toggleState(x, y);
            if (currentState == ClickState.CLICK_END) {
                System.out.println("Click sequence.");
                Chess.makeMove(startX, startY, endX, endY);
                resetState();
            }
        }
    }

    private class ChessButton extends JButton {
        private int x;
        private int y;

        public ChessButton(String text, int x, int y) {
            super(text);
            this.x = x;
            this.y = y;
        }

        public int getXValue() {
            return x;
        }

        public int getYValue() {
            return y;
        }
    }

    private void toggleState(int x, int y) {
        if (currentState == ClickState.INITIAL) {
            currentState = ClickState.CLICK_START;
            startX = x;
            startY = y;
        }
        else if (currentState == ClickState.CLICK_START) {
            currentState = ClickState.CLICK_END;
            endX = x;
            endY = y;
            System.out.println("State: [" + startX + "," + startY + "] -> [" + endX + "," + endY + "]");
        }
    }

    private void resetState() {
        currentState = ClickState.INITIAL;
    }

    public GraphicalDisplay() {
        currentState = ClickState.INITIAL;
        setTitle("Chess Game");
        setSize(WIDTH, HEIGHT);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(BOARD_WIDTH, BOARD_HEIGHT));

        ButtonHandler buttonListener = new ButtonHandler();

        for (int i=BOARD_HEIGHT-1; i>=0; i--) {
            for (int j=0; j<BOARD_WIDTH; j++) {
                ChessButton button = new ChessButton("x", j, i);
                button.addActionListener(buttonListener);
                contentPane.add(button);
                buttons[i][j] = button;
            }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showPiecesOnBoard(Piece[][] data) {
        for (int i=data.length-1; i>=0; i--) {
            for (int j=0; j<data.length; j++) {
                char buttonText;
                if (data[j][i] != null) {
                    buttonText = data[j][i].getChar();
                }
                else {
                    buttonText = '-';
                }
                buttons[i][j].setText(String.valueOf(buttonText));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorderPainted(true);
                buttons[i][j].setOpaque(false);
            }
        }
        TextDisplay tx = new TextDisplay();
        tx.showPiecesOnBoard(data);
    }

}
