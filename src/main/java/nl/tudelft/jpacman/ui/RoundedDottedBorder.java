package nl.tudelft.jpacman.ui;


import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class RoundedDottedBorder extends AbstractBorder {
    private static final long serialVersionUID = 1L;
    private int radius;
    private Color color;

    public RoundedDottedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Set rendering hints to improve the appearance of the rounded rectangle
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the border color and stroke
        g2.setColor(color);
        float lineWidth = 5f; // line width in pixels, width
        float dashLength = 15f; // length of each dash in pixels, length
        float gapLength = 6f; // length of each gap in pixels, spacing
        float[] dashPattern = {dashLength, gapLength};
        BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1f, dashPattern, 0f);
        g2.setStroke(stroke);

        // Draw the rounded rectangle border
        RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius);
        g2.draw(roundRect);

        g2.dispose();
    }



    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = insets.bottom = insets.top = radius;
        return insets;
    }
}


