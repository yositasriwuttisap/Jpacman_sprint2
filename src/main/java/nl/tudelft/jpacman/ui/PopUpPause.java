package nl.tudelft.jpacman.ui;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class PopUpPause extends JFrame {
    private JButton resumeButton;
    private JButton backButton;

    public PopUpPause(final Game game) {
        // set the window properties
        setTitle("Popup Window");
        setSize(200, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a container panel
        JPanel panel = new JPanel(new GridBagLayout());
        getContentPane().add(panel);

        // create a logo label and add it to the panel
        JLabel logoLabel = new JLabel(new ImageIcon("src/main/resources/img/popup/rause_logo_128_31.png"));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(logoLabel, c);

        // create a resume button and add it to the panel
        resumeButton = new JButton(new ImageIcon("src/main/resources/img/button/resume.png"));
        resumeButton.setPreferredSize(new Dimension(128,38));
        resumeButton.setBackground(Color.white);
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.start();
                game.start();
            }
        });
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(resumeButton, c);

        // create a back button and add it to the panel
        backButton = new JButton(new ImageIcon("src/main/resources/img/button/back.png"));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JFrame) {
                        window.dispose();
                    }
                }
                IndexPacman indexPacman = new IndexPacman();

                indexPacman.setVisible(true);
            }
        });
        backButton.setPreferredSize(new Dimension(128,36));
        backButton.setBackground(Color.white);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, c);
        RoundedDottedBorder border = new RoundedDottedBorder(50,Color.BLACK);
        panel.setBorder(border);
        panel.setBackground(Color.white);
        setUndecorated(true);
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
