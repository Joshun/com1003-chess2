package uk.ac.sheffield.aca14jmo;

import uk.ac.sheffield.aca14jmo.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by joshua on 29/04/15.
 */
public class InitialMenu extends JFrame {
    private JComboBox player1Combo;
    private JComboBox player2Combo;
    private JSpinner computerDelaySpinner;

//    private class MenuButton extends JButton {
//        private GameMode gameMode;
//        public MenuButton(String text, GameMode gm) {
//            super(text);
//            this.gameMode = gm;
//        }
//        public GameMode getGameMode() {
//            return gameMode;
//        }
//    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            MenuButton source = (MenuButton)e.getSource();
//            Chess.startGame(source.getGameMode());
            GameChoice player1Choice = (GameChoice)player1Combo.getSelectedItem();
            GameChoice player2Choice = (GameChoice)player2Combo.getSelectedItem();
//            if (player1Choice != GameChoice.HUMAN && player2Choice != GameChoice.HUMAN) {
//                Chess.playComputerVComputer();
//            }
//            else {
                Chess.startGame(player1Choice, player2Choice, (int)computerDelaySpinner.getValue());
//            }
            InitialMenu.this.setVisible(false);
        }
    }

//    private void setupButtons(MenuButton[] buttons, Container contentPane) {
//        ButtonHandler buttonListener = new ButtonHandler();
//        for (MenuButton button: buttons) {
//            button.addActionListener(buttonListener);
//            contentPane.add(button);
//        }
//    }

    private void setupComboBox(JComboBox comboBox) {
        comboBox.addItem(GameChoice.HUMAN);
        comboBox.addItem(GameChoice.AGGRESSIVE_COMPUTER);
        comboBox.addItem(GameChoice.RANDOM_COMPUTER);
    }

    public InitialMenu() {
        setTitle("Select a game mode");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        JButton playButton = new JButton("Play!");
        playButton.addActionListener(new ButtonHandler());
        JLabel player1Text = new JLabel("Player 1");
        JLabel player2Text = new JLabel("Player 2");
        JLabel delayText = new JLabel("Computer versus delay");
        SpinnerNumberModel delaySpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner delaySpinner = new JSpinner(delaySpinnerModel);
        computerDelaySpinner = delaySpinner;
        player1Combo = new JComboBox();
        player2Combo = new JComboBox();
        setupComboBox(player1Combo);
        setupComboBox(player2Combo);
        contentPane.add(player1Text);
        contentPane.add(player1Combo);
        contentPane.add(player2Text);
        contentPane.add(player2Combo);
        contentPane.add(delayText);
        contentPane.add(delaySpinner);
        // Empty container to push button to rightmost cell
        contentPane.add(new Container());
        contentPane.add(playButton);

//        MenuButton[] buttons = {
//                new MenuButton("Human vs Aggressive CPU", GameMode.HUMAN_V_AGGRESSIVE),
//                new MenuButton("Human vs Random CPU", GameMode.HUMAN_V_RANDOM),
//                new MenuButton("Human vs Human", GameMode.HUMAN_V_HUMAN)
//        };
//        // Connect event handler to buttons and add them to the contentPane
//        setupButtons(buttons, contentPane);
        // Automatically set size
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
