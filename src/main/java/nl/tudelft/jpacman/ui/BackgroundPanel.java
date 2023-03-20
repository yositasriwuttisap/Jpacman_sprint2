package nl.tudelft.jpacman.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        setBackgroundImage(imagePath);
    }

    public void setBackgroundImage(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        if (backgroundImage == null) {
            return super.getPreferredSize();
        } else {
            return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

