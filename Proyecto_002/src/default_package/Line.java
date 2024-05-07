package default_package;

import java.awt.Color;
import java.awt.Graphics;

import interfaces.Drawable;

public class Line implements Drawable {

    private int yLinea;

    @Override
    public void drawable(Graphics g) {
        // Coordenadas de los puntos
        yLinea = (int) (g.getClipBounds().getHeight() * 0.66);
        g.setColor(Color.RED);
        g.drawLine(0, yLinea, (int) g.getClipBounds().getWidth(), yLinea);
    }

    public int getY() {
        return yLinea;
    }
}
