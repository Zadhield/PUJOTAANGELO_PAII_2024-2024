package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import interfaces.Deadble;
import interfaces.Drawable;
import interfaces.Movable;
import interfaces.Shoteable;

public class Ship implements Drawable, Deadble, Movable, Shoteable {
    private int x, y;
    private List<Bullet> bullets = new ArrayList<>();
    private boolean dead;
    private int life; // Variable para la vida del jugador

    public Ship() {
        this.x = 400; // Posición horizontal centrada
        this.y = 500; // Posición vertical en la parte inferior de la ventana
        this.dead = false;
        this.life = 100; // Inicializar la vida del jugador
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void shoteable() {
        bullets.add(new Bullet(getX() - 4, getY(), "UP")); // Cambiar la dirección a "UP"
    }


    public void updateBullets() {
        for (Bullet bullet : bullets) {
            bullet.movable("up");
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void movable(String dir) {
        if (!dead) {
            switch (dir) {
                case "UP":
                    if (y > 0) {
                        y -= 15;
                    }
                    break;

                case "DOWN":
                    if (y < 500) {
                        y += 15;
                    }
                    break;

                case "LEFT":
                    if (x > 30) {
                        x -= 15;
                    }
                    break;

                case "RIGHT":
                    if (x < 750) {
                        x += 15;
                    }
                    break;
            }
        }
    }

    @Override
    public void deadble() {
        dead = true;
    }

    @Override
    public void drawable(Graphics g) {
        g.setColor(Color.BLUE); // Color de la nave
        g.fillPolygon(new Polygon(new int[]{x, x - 20, x + 20},
                new int[]{y, y + 40, y + 40}, 3)); //
        
        g.setColor(Color.WHITE);
        g.drawRect(10, 10, 100, 20); // Marco de la barra de vida
        g.setColor(Color.GREEN);
        g.fillRect(10, 10, life , 20); // Barra de vida verde
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    @Override
    public Object getBounds() {
        return null;
    }

    public void removeInactiveBullets() {
        bullets.removeIf(bullet -> !bullet.isActive());
    }

    // Método para disminuir la vida del jugador
    public void decreaseLife(int damage) {
        if (!dead) {
            life -= damage;
            if (life <= 0) {
                deadble();
            }
        }
    }

    // Método para obtener la vida del jugador
    public int getLife() {
        return life;
    }
}
