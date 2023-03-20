package nl.tudelft.jpacman.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.List;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Level.LevelObserver;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.ui.IndexPacman;
import nl.tudelft.jpacman.ui.PopUpLose;
import nl.tudelft.jpacman.ui.PopUpWin;
import nl.tudelft.jpacman.ui.ScorePanel;

import javax.swing.*;

/**
 * A basic implementation of a Pac-Man game.
 *
 * @author Jeroen Roosen 
 */
public abstract class Game implements LevelObserver {
    Player player_score;
    ScorePanel scorePanel;
    /**
     * <code>true</code> if the game is in progress.
     */
    private boolean inProgress;

    /**
     * Object that locks the start and stop methods.
     */
    private final Object progressLock = new Object();

    /**
     * The algorithm used to calculate the points that
     * they player gets whenever some action happens.
     */
    private PointCalculator pointCalculator;
    private String levelMap;
    private String currentTheme;

    /**
     * Creates a new game.
     *
     * @param pointCalculator
     *             The way to calculate points upon collisions.
     */
    protected Game(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
        inProgress = false;
        System.out.println("protected Game");
    }

    /**
     * Starts or resumes the game.
     */
    public void start() {
        synchronized (progressLock) {
            if (isInProgress()) {
                System.out.println("isInProgress with start");
                return;
            }
            if (getLevel().isAnyPlayerAlive() && getLevel().remainingPellets() > 0) {
                inProgress = true;
                getLevel().addObserver(this);
                getLevel().start();
                System.out.println("isAnyPlayerAlive");
            }
        }
        System.out.println("starting");
    }

    /**
     * Pauses the game.
     */
    public void stop() {
        synchronized (progressLock) {
            if (!isInProgress()) {
                System.out.println("is not InProgress with stop");
                return;
            }
            inProgress = false;
            getLevel().stop();
            System.out.println("is InProgress with stop");
        }
        System.out.println("stoped");
    }

    public void back() {
        synchronized (progressLock) {
            stop();
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame) {
                    window.dispose();
                }
            }
            IndexPacman indexPacman = new IndexPacman();

            indexPacman.setVisible(true);
        }
    };
    /**
     * @return <code>true</code> iff the game is started and in progress.
     */
    public boolean isInProgress() {
        System.out.println("set isInProgress");
        return inProgress;
    }

    /**
     * @return An immutable list of the participants of this game.
     */
    public abstract List<Player> getPlayers();

    /**
     * @return The level currently being played.
     */
    public abstract Level getLevel();

    /**
     * Moves the specified player one square in the given direction.
     *
     * @param player
     *            The player to move.
     * @param direction
     *            The direction to move in.
     */
    public void move(Player player, Direction direction) {
        player_score = player;
        if (isInProgress()) {
            // execute player move.
            getLevel().move(player, direction);
            pointCalculator.pacmanMoved(player, direction);
            System.out.println("isInProgress");
        }
        System.out.println("moving");
    }

    @Override
    public void levelWon() {
        stop();
        PopUpWin popUpWin = new PopUpWin(player_score.getScore(),getCurrentTheme(),getLevelMap());
        popUpWin.setVisible(true);
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setContentPane(popUpWin.getContentPane());
        dialog.setModal(false);
        dialog.setAlwaysOnTop(true);
        dialog.pack();

        // Add a listener to the JFrame of PacManUI
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this.scorePanel);
        dialog.setLocationRelativeTo(frame);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                Rectangle jFrameBounds = frame.getBounds();

                // Get size of JDialog
                Dimension dialogSize = dialog.getSize();

                // Calculate position of JDialog in center of PacManUI JFrame
                int dialogX = (jFrameBounds.width - dialogSize.width) / 2 + jFrameBounds.x;
                int dialogY = (jFrameBounds.height - dialogSize.height) / 2 + jFrameBounds.y;

                // Set position of JDialog
                dialog.setLocation(dialogX, dialogY);
            }
        });

        popUpWin.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                IndexPacman indexPacman = new IndexPacman();
                indexPacman.setVisible(true);
            }
        });

        dialog.setVisible(true);
        System.out.println("levelWon");
    }

    @Override
    public void levelLost() {
        stop();
        PopUpLose popUpLose = new PopUpLose(player_score.getScore(),getCurrentTheme(),getLevelMap());
        popUpLose.setVisible(true);
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setContentPane(popUpLose.getContentPane());
        dialog.setModal(false);
        dialog.setAlwaysOnTop(true);
        dialog.pack();

        // Add a listener to the JFrame of PacManUI
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this.scorePanel);
        dialog.setLocationRelativeTo(frame);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                Rectangle jFrameBounds = frame.getBounds();

                // Get size of JDialog
                Dimension dialogSize = dialog.getSize();

                // Calculate position of JDialog in center of PacManUI JFrame
                int dialogX = (jFrameBounds.width - dialogSize.width) / 2 + jFrameBounds.x;
                int dialogY = (jFrameBounds.height - dialogSize.height) / 2 + jFrameBounds.y;

                // Set position of JDialog
                dialog.setLocation(dialogX, dialogY);
            }
        });

        popUpLose.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                IndexPacman indexPacman = new IndexPacman();
                indexPacman.setVisible(true);
            }
        });

        dialog.setVisible(true);
        System.out.println("levelLost");
    }

    public void setLevelMap(String levelMap) {
        System.out.println("setLevelMap");
        this.levelMap = levelMap;
    }

    public void setCurrentTheme(String currentTheme) {
        System.out.println("setCurrentTheme");
        this.currentTheme = currentTheme;
    }

    public String getCurrentTheme() {
        System.out.println("getCurrentTheme");
        return currentTheme;
    }

    public String getLevelMap() {
        System.out.println("getLevelMap");
        return levelMap;
    }

    public void setScorePanel(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }
}
