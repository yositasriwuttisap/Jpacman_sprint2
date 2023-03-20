package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import nl.tudelft.jpacman.ui.PacKeyListener;
import javax.swing.*;
import nl.tudelft.jpacman.ui.PacManUiBuilder;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.ui.PacManUI;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective scores underneath.
 *
 * @author Jeroen Roosen 
 *
 */
public class ScorePanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    public PacManUiBuilder takecare;

    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their scores are on.
     */
    private final Map<Player, JLabel> scoreLabels;

    /**
     * The default way in which the score is shown.
     */
    public static final ScoreFormatter DEFAULT_SCORE_FORMATTER =
        (Player player) -> String.format("%40s: %3d", "SCORE", player.getScore());

    /**
     * The way to format the score information.
     */
    private ScoreFormatter scoreFormatter = DEFAULT_SCORE_FORMATTER;

    /**
     * Creates a new score panel with a column for each player.
     *
     * @param players
     *            The players to display the scores of.
     */
    private JButton resumeButton;
    private JButton button;
    private int currentIndex = 0;
    private  int status = 0;

    public ScorePanel(List<Player> players,final Game game) {
        super();
        assert players != null;
        
        setLayout(new GridLayout(1, players.size()));

//        for (int i = 1; i <= players.size(); i++) {
//            add(new JLabel("Player " + i, JLabel.CENTER));
//        }
        scoreLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel scoreLabel = new JLabel("0", JLabel.LEFT);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new Font("Helvetica Neue Condensed Black", Font.BOLD, 20));
            JButton button = new JButton(new ImageIcon("src/main/resources/img/button/exit_action_button_36_36.png"));
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game.stop();
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        if (window instanceof JFrame) {
                            window.dispose();
                        }
                    }
                    IndexPacman indexPacman  = new IndexPacman();
                    indexPacman.setVisible(true);
                }

            });
            setPreferredSize(new Dimension(getWidth(), 50));
            scoreLabels.put(player, scoreLabel);
            JPanel cellPanel = new JPanel(new BorderLayout());
            cellPanel.setBackground(new Color(32, 13, 41));
            cellPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            cellPanel.add(scoreLabel, BorderLayout.CENTER);
            //cellPanel.add(button, BorderLayout.EAST);
            add(cellPanel);

        }
    }

    public int getStatus() {
        return status;
    }

    /**
     * Refreshes the scores of the players.
     */

    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : scoreLabels.entrySet()) {
            Player player = entry.getKey();
            String score = "";
            if (!player.isAlive()) {
                score = "You died. ";
            }
            score += scoreFormatter.format(player);
            entry.getValue().setText(score);
        }
    }

    /**
     * Provide means to format the score for a given player.
     */
    public interface ScoreFormatter {

        /**
         * Format the score of a given player.
         * @param player The player and its score
         * @return Formatted score.
         */
        String format(Player player);
    }

    /**
     * Let the score panel use a dedicated score formatter.
     * @param scoreFormatter Score formatter to be used.
     */
    public void setScoreFormatter(ScoreFormatter scoreFormatter) {
        assert scoreFormatter != null;
        this.scoreFormatter = scoreFormatter;
    }





}
