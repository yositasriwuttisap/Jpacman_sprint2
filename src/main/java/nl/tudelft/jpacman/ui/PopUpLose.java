package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.Launcher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PopUpLose extends JFrame {
    JButton backButton;
    public PopUpLose(int score,String currentTheme,String levelMap) {
        super("My Game");


        JLabel panel = new JLabel(new ImageIcon("src/main/resources/img/popup/popup_gameover.png"));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // First row: Score label
        JLabel scoreLabel = new JLabel(""+score);
        scoreLabel.setBorder(new EmptyBorder(170,180,0,0));
        scoreLabel.setFont(new Font("SansSerif",Font.BOLD,50));
        scoreLabel.setForeground(Color.red);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(scoreLabel, c);

        // Second row: Highscore label

        JLabel highscoreLabel = new JLabel("");
        highscoreLabel.setBorder(new EmptyBorder(0,170,0,0));
        highscoreLabel.setFont(new Font("SansSerif",Font.BOLD,20));
        highscoreLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(highscoreLabel, c);

        // Third row: Back button and Retry button
        backButton = new JButton(new ImageIcon("src/main/resources/img/button/back.png"));
        backButton.setBorder(new EmptyBorder(100,50,0,0));
        backButton.setContentAreaFilled(false);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(backButton, c);
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
        JButton retryButton = new JButton(new ImageIcon("src/main/resources/img/button/retry.png"));
        retryButton.setBorder(new EmptyBorder(100,0,0,50));
        retryButton.setContentAreaFilled(false);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(retryButton, c);
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JFrame) {
                        window.dispose();
                    }
                }
                Launcher Mylauncher = new Launcher();
                Launcher.board(levelMap);
                Mylauncher.launch(currentTheme);
                Window win = SwingUtilities.getWindowAncestor(retryButton);
                win.dispose();
            }
        });
        getContentPane().add(panel);
        setUndecorated(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public JButton getBackButton() {
        return backButton;
    }

    public static void main(String[] args) {
//        PopUpLose frame = new PopUpLose(100, "A", "/map1.txt");
//        frame.setSize(600,534);

    }
}
