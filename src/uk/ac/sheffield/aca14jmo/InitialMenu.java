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

    private class MenuButton extends JButton {
        private GameMode gameMode;
        public MenuButton(String text, GameMode gm) {
            super(text);
            this.gameMode = gm;
        }
        public GameMode getGameMode() {
            return gameMode;
        }
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MenuButton source = (MenuButton)e.getSource();
            Chess.startGame(source.getGameMode());
        }
    }

    private void setupButtons(MenuButton[] buttons, Container contentPane) {
        ButtonHandler buttonListener = new ButtonHandler();
        for (MenuButton button: buttons) {
            button.addActionListener(buttonListener);
            contentPane.add(button);
        }
    }

    public InitialMenu() {
        setTitle("Select a game mode");
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        MenuButton[] buttons = {
                new MenuButton("Human vs Aggressive CPU", GameMode.HUMAN_V_AGGRESSIVE),
                new MenuButton("Human vs Random CPU", GameMode.HUMAN_V_RANDOM),
                new MenuButton("Human vs Human", GameMode.HUMAN_V_HUMAN)
        };
        // Connect event handler to buttons and add them to the contentPane
        setupButtons(buttons, contentPane);
        // Automatically set size
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
