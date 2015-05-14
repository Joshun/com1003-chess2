package uk.ac.sheffield.aca14jmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * uk.ac.sheffield.aca14jmo.Dialog.java
 *
 * Class for a simple alert dialog for a given message, with the option of exiting upon its closure
 */
public class Dialog extends JFrame {
    private boolean shouldQuit;

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Dialog.this.setVisible(false);
            if (Dialog.this.shouldQuit) {
                System.exit(0);
            }
        }
    }

    public Dialog(String message, boolean shouldQuit) {
        this.shouldQuit = shouldQuit;
        setSize(200, 200);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        JLabel messageLabel = new JLabel(message);
        JButton button = new JButton("OK");
        button.addActionListener(new ButtonHandler());
        contentPane.add(messageLabel);
        contentPane.add(button);
        if (shouldQuit) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        setVisible(true);
    }

}
