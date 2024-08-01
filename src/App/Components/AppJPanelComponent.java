package App.Components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class AppJPanelComponent extends JPanel {
    private final int cornerRadius;
    private final Color shadowColor;
    private float shadowOpacity;

    public AppJPanelComponent(Color backgroundColor, Color shadowColor, int cornerRadius, float shadowOpacity) {
        this.cornerRadius = cornerRadius;
        this.setBackground(backgroundColor);
        this.shadowColor = shadowColor;
        this.shadowOpacity = shadowOpacity;
        this.setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int shadowSize = 2;
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setColor(this.shadowColor);
        g2.setComposite(AlphaComposite.getInstance(3, this.shadowOpacity));
        g2.fillRoundRect(0, shadowSize, this.getWidth(), this.getHeight() - shadowSize, this.cornerRadius, this.cornerRadius);
        g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
        g2.setColor(this.getBackground());
        g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight() - shadowSize, this.cornerRadius, this.cornerRadius);
        g2.dispose();
    }
}
