package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * A panel containing a button for every registered action.
 *
 * @author Jeroen Roosen 
 */
class ButtonPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new button panel with a button for every action.
     * @param buttons The map of caption - action for each button.
     * @param parent The parent frame, used to return window focus.
     */
    ButtonPanel(final Map<String, Action> buttons, final JFrame parent) {
        super();
        assert buttons != null;
        assert parent != null;

        for (final String caption : buttons.keySet()) {
            JButton button = new JButton(caption);
            button.addActionListener(e -> {
                buttons.get(caption).doAction();
                parent.requestFocusInWindow();
            });
            System.out.println(button.getText());
            if (button.getText().equals("Start"))
                button.setIcon(new ImageIcon("src/main/resources/img/button/start_36.png"));
            else if (button.getText().equals("Stop")) {
                button.setIcon(new ImageIcon("src/main/resources/img/button/stop_36.png"));
            } else {
                button.setIcon(new ImageIcon("src/main/resources/img/button/back_36.png"));
            }

//            EmptyBorder emptyBorder = new EmptyBorder(100, 0, 0, 0);
//
//            button.setBorder(emptyBorder);
            button.setOpaque(false);
            button.setBorder(null);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setText("");
            add(button);
        }
        setBackground(new Color(32, 13, 41));
    }
}
