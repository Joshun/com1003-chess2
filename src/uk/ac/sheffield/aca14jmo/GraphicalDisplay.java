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

    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;
    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;
    private ChessButton[][] buttons = new ChessButton[BOARD_WIDTH][BOARD_HEIGHT];
    private ClickState currentState;
    private int startX, startY;
    private int endX, endY;
    private JLabel statusBar;
    private TextDisplay textDisplay = new TextDisplay();
    private ImageLoader imageLoader;
    private int computerDelay;
    private Timer computerTimer = null;

    private class ComputerHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Chess.nextComputerMove();
        }
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DebugLog.println("ButtonHandler event: " + e);
            ChessButton source = (ChessButton)e.getSource();
            int x = source.getXValue();
            int y = source.getYValue();

            source.setBackground(Color.lightGray);
            source.setBorderPainted(false);
            source.setOpaque(true);

            DebugLog.println("Button clicked: " + x + "," + y);
            toggleState(x, y);
            if (currentState == ClickState.CLICK_END) {
                DebugLog.println("Click sequence.");
                Chess.makeMove(startX, startY, endX, endY);
                resetState();
            }
        }
    }

    public void setStatusText(String text) {
        statusBar.setText(text);
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
            DebugLog.println("State: [" + startX + "," + startY + "] -> [" + endX + "," + endY + "]");
        }
    }

    private void resetState() {
        currentState = ClickState.INITIAL;
    }

    public GraphicalDisplay(int computerDelay) {
        this.computerDelay = computerDelay;
        imageLoader = new ImageLoader("images/", ".png");
        currentState = ClickState.INITIAL;
        setTitle("Chess Game");
        setSize(WIDTH, HEIGHT);
        JPanel grid = new JPanel(new GridLayout(BOARD_WIDTH, BOARD_HEIGHT));
        statusBar = new JLabel("White\'s move.");

        Container contentPane = getContentPane();
        //contentPane.setLayout(new GridLayout(BOARD_WIDTH, BOARD_HEIGHT));
        ButtonHandler buttonListener = new ButtonHandler();

        for (int i=BOARD_HEIGHT-1; i>=0; i--) {
            for (int j=0; j<BOARD_WIDTH; j++) {
                ChessButton button = new ChessButton(" ", j, i);
                button.addActionListener(buttonListener);
                grid.add(button);
                buttons[i][j] = button;
            }
        }
        contentPane.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        contentPane.add(grid);
        contentPane.add(statusBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        if (Chess.getComputerVComputer()) {
            computerTimer = new Timer(computerDelay * 1000, new ComputerHandler());
            computerTimer.setRepeats(true);
            computerTimer.start();
        }
    }

    public void resetMarkings() {
//       for (ChessButton[] row: buttons) {
//           for (ChessButton cell: row) {
//               cell.setBackground(Color.WHITE);
//               cell.setBorderPainted(true);
//               cell.setOpaque(false);
//           }
//       }
        for (int row=0; row<buttons.length; row++) {
            for (int column=0; column<buttons[0].length; column++) {
                if ((row % 2) != 0) {
                    if ((column % 2) != 0) {
                        buttons[row][column].setBackground(Color.BLACK);
                    }
                    else {
                        buttons[row][column].setBackground(Color.WHITE);
                    }
                }
                else {
                    if ((column % 2) != 0) {
                        buttons[row][column].setBackground(Color.WHITE);
                    }
                    else {
                        buttons[row][column].setBackground(Color.BLACK);
                    }
                }
            }
        }
    }

    public void showPiecesOnBoard(Piece[][] data) {
        resetMarkings();
        for (int i=data.length-1; i>=0; i--) {
            for (int j=0; j<data.length; j++) {
                char buttonText;
                if (data[j][i] != null) {
                    buttonText = PieceCode.letterToSymbol(data[j][i].getChar());
                    buttons[i][j].setIcon(imageLoader.getImageForPiece(data[j][i]));
//                    System.out.println(imageLoader.getImageForPiece(data[j][i]));
                }
                else {
                    buttonText = '-';
                    buttons[i][j].setIcon(null);
                }
//                buttons[i][j].setText(String.valueOf(buttonText));
//                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorderPainted(true);
                buttons[i][j].setOpaque(true);
//                ImageLoader img = new ImageLoader("images/", ".png");

            }
        }
        if (DebugLog.isEnabled()) {
            textDisplay.showPiecesOnBoard(data);
        }

    }

    public void stopComputerMovement() {
        if (computerTimer != null) computerTimer.stop();
    }

}
