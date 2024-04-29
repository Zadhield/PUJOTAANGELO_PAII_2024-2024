package default_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import interfaces.Drawable;
import interfaces.Movable;

public class Bullet implements Drawable, Movable {
    private int bulletX, bulletY;
    private boolean active;
    private int speedY; // Velocidad vertical de la bala

    public Bullet(int bulletX, int bulletY, String direction) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.active = true;

        if (direction.equals("UP")) {
            this.speedY = -10; // Si la dirección es "UP", la bala se moverá hacia arriba
        } else if (direction.equals("DOWN")) {
            this.speedY = 2; // Si la dirección es "DOWN", la bala se moverá hacia abajo
        }
    }

    @Override
    public void movable(String direction) {
        bulletY += speedY; // Ajustar la posición vertical de la bala según la velocidad
        if (bulletY <= 0 - 15 || bulletY >= 600) {
            active = false; // Desactivar la bala cuando sale de la pantalla
        }
    }

    @Override
    public void drawable(Graphics g) {
        if (active) {
            g.setColor(Color.YELLOW);
            g.fillOval(bulletX, bulletY, 10, 16);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBounds() {
        return new Rectangle(bulletX, bulletY, 10, 16);
    }
}
