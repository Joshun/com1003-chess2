package uk.ac.sheffield.aca14jmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * uk.ac.sheffield.aca14jmo.InitialMenu.java
 *
 * Class for the menu that is displayed on game start to choose player types
 */
public class InitialMenu extends JFrame {
    private JComboBox<GameChoice> player1Combo;
    private JComboBox<GameChoice> player2Combo;
    private JSpinner computerDelaySpinner;

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GameChoice player1Choice = (GameChoice)player1Combo.getSelectedItem();
            GameChoice player2Choice = (GameChoice)player2Combo.getSelectedItem();
            Chess.startGame(player1Choice, player2Choice, (int)computerDelaySpinner.getValue());
            InitialMenu.this.setVisible(false);
        }
    }

    private void setupComboBox(JComboBox<GameChoice> comboBox) {
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
        player1Combo = new JComboBox<>();
        player2Combo = new JComboBox<>();
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

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
