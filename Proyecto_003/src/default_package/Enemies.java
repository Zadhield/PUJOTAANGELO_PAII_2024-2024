package default_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

import interfaces.Deadble;
import interfaces.Drawable;
import interfaces.Movable;
import interfaces.Shoteable;

public class Enemies implements Drawable, Deadble, Shoteable, Movable {
    private List<Point> positions;
    private List<Boolean> deads;
    private List<Bullet> enemyBullets;
    private List<Integer> directionXList; // Dirección horizontal de los enemigos
    private int minX, maxX;
    private int minY, maxY;
    private int moveCount; // Contador de movimientos para cambiar la dirección horizontal
    private int damage = 10;
    private int scoreValue = 10; // Valor del puntaje por eliminar un enemigo
    private Timer timer;

    public Enemies(int numEnemies, int minX, int maxX, int minY, int maxY) {
        positions = new ArrayList<>();
        deads = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        directionXList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numEnemies; i++) {
            int x = random.nextInt(maxX - 50 - minX) + minX;
            int y = minY + (i + 10); // Generar enemigos solo en el borde superior
            positions.add(new Point(x, y));
            deads.add(false);
            // Asignar una dirección aleatoria a cada enemigo
            directionXList.add(random.nextBoolean() ? 1 : -1);
        }

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        // Inicializar el temporizador para generar nuevos enemigos
        timer = new Timer(500, (e) -> generateNewEnemy());
        timer.start();

        // Inicializar el temporizador para que los enemigos disparen
        Timer enemyTimer = new Timer(2000, (e) -> shoteable());
        enemyTimer.start();
    }

    private void generateNewEnemy() {
        Random random = new Random();
        int x = random.nextInt(maxX - 50 - minX) + minX;
        int y = minY;
        positions.add(new Point(x, y));
        deads.add(false);
        // Asignar una dirección aleatoria al nuevo enemigo
        directionXList.add(random.nextBoolean() ? 1 : -1);
    }
  
    public List<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public List<Boolean> getDeads() {
        return deads;
    }

    @Override
    public void shoteable() {
        for (Point position : positions) {
            enemyBullets.add(new Bullet(position.x + 20, position.y + 40, "DOWN")); // Cambiar la dirección a "DOWN"
        }
    }

    @Override
    public void deadble() {
        // No necesario en este momento
    }

    @Override
    public void drawable(Graphics g) {
        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            // Coordenadas de los puntos
            int[] xPoints = { position.x, position.x, position.x + 20, position.x + 40, position.x + 40 };
            int[] yPoints = { position.y, position.y + 40, position.y + 20, position.y + 40, position.y };
            // Crear el polígono
            Polygon polygon = new Polygon(xPoints, yPoints, 5);
            // Dibujar el polígono
            g.setColor(Color.GREEN);
            g.fillPolygon(polygon);
        }
        for (Bullet bullet : enemyBullets) {
            bullet.drawable(g);
            bullet.movable("UP");
        }
    }

    @Override
    public void movable(String direction) {
        moveCount++;
        if (moveCount % 300 == 0) { // Cambiar la dirección horizontal cada 50 movimientos
            // Invertir la dirección horizontal de cada enemigo
            for (int i = 0; i < directionXList.size(); i++) {
                directionXList.set(i, -directionXList.get(i));
            }
        }

        Iterator<Point> iterator = positions.iterator();
        Iterator<Integer> directionIterator = directionXList.iterator();
        while (iterator.hasNext() && directionIterator.hasNext()) {
            Point position = iterator.next();
            int directionX = directionIterator.next();
            int moveX = directionX;
            int moveY = 1; // Siempre se mueven hacia abajo

            int newX = position.x + moveX;
            int newY = position.y + moveY;

            // Verificar si el nuevo movimiento lleva al enemigo fuera del borde izquierdo o derecho
            if (newX < minX || newX > maxX - 40) {
                // Invertir la dirección horizontal si alcanza los bordes
                moveX = -moveX;
            }

            // Verificar si el nuevo movimiento lleva al enemigo fuera del borde inferior
            if (newY > maxY - 40) {
                // Eliminar al enemigo si pasa el panel inferior
                iterator.remove();
                deads.remove(0);
                directionIterator.remove();
                continue;
            }

            position.setLocation(newX, newY);
        }
    }

    public List<Point> getPositions() {
        return positions;
    }

    public Rectangle getBounds() {
        return new Rectangle(positions.get(0).x, positions.get(0).y, 40, 40);
    }

    public void removeDeadEnemies() {
        for (int i = 0; i < deads.size(); i++) {
            if (deads.get(i)) {
                positions.remove(i);
                deads.remove(i);
                directionXList.remove(i);
            }
        }
    }

    // Método para verificar la colisión con las balas
    public int checkCollision(List<Bullet> bullets) {
        int scoreIncrease = 0;
        for (Bullet bullet : bullets) {
            for (int i = 0; i < positions.size(); i++) {
                Point position = positions.get(i);
                Rectangle enemyBounds = new Rectangle(position.x, position.y, 40, 40);
                if (enemyBounds.intersects(bullet.getBounds())) {
                    deads.set(i, true); // Marcar el enemigo como muerto si hay una colisión
                    scoreIncrease += scoreValue; // Aumentar el puntaje por cada enemigo eliminado
                }
            }
        }
        return scoreIncrease;
    }

    public boolean checkCollision(Ship ship) {
        Rectangle shipBounds = new Rectangle(ship.getX(), ship.getY(), 40, 40);
        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            Rectangle enemyBounds = new Rectangle(position.x, position.y, 40, 40);
            if (enemyBounds.intersects(shipBounds)) {
                ship.decreaseLife(damage); // Disminuir la vida del jugador
                deads.set(i, true); // Marcar el enemigo como muerto si hay una colisión
                return true;
            }
        }
        return false;
    }

}
