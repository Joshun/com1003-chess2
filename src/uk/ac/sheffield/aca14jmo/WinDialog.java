package uk.ac.sheffield.aca14jmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by joshua on 29/04/15.
 */
public class WinDialog extends JFrame {

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            WinDialog.this.setVisible(false);
            System.exit(0);
        }
    }

    public WinDialog(String message) {
        setSize(200, 200);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        JLabel messageLabel = new JLabel(message);
        JButton button = new JButton("OK");
        button.addActionListener(new ButtonHandler());
        contentPane.add(messageLabel);
        contentPane.add(button);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
