package default_package;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import javax.swing.JPanel;
import javax.swing.Timer;
 
public class Jpanel extends JPanel implements ActionListener {
 
    private Ship ship;
    private boolean gameActive;
    private Enemies enemies;
    private Line line;
    private Container container;
    private int score;
 
    public Jpanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
 
        container = new Container();
        ship = new Ship();
        line = new Line();
        enemies = new Enemies(6, 0, 800, 0, 600);
        event(ship);
 
        addKeyListener(new MyKeyListener());
        setFocusable(true);
        requestFocusInWindow();
 
        Timer timer = new Timer(10, this);
        timer.start();
        score = 0;
        gameActive = true;
    }
 
    public void decreaseLife(int damage) {
        ship.decreaseLife(damage);
        if (ship.getLife() <= 0) {
            ship.deadble();
            gameActive = false; // El juego se detiene cuando la vida llega a cero
        }
    }
 
    public void increaseScore(int points) {
        score += points;
    }
 
    public void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 600, 30);
    }
 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
 
        container.drawable(enemies, g);
        container.drawable(line, g);
        drawScore(g);
 
        if (gameActive) {
            for (Bullet bullet : ship.getBullets()) {
                container.drawable(bullet, g); // Dibuja cada bala en la lista de balas del jugador
            }
 
            if (ship.getY() > line.getY()) {
                container.drawable(ship, g);
            } else {
                // La nave ha llegado al límite, mostrar "GAME OVER"
                g.setColor(Color.WHITE);
                g.setFont(new Font("ARIAL", Font.PLAIN, 62));
                g.drawString("GAME OVER", (int) (getWidth() / 3.8f), getHeight() / 2);
                gameActive = false; // Desactivar el juego
            }
        } else {
            // El juego ha terminado, mostrar "GAME OVER"
            g.setColor(Color.WHITE);
            g.setFont(new Font("ARIAL", Font.PLAIN, 62));
            g.drawString("GAME OVER", (int) (getWidth() / 3.8f), getHeight() / 2);
        }
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameActive) {
            // Mover las balas automáticamente
            for (Bullet bullet : ship.getBullets()) {
                bullet.movable("UP");
            }
            ship.removeInactiveBullets();
            enemies.movable("DOWN");

            // Verificar colisión de balas con enemigos
            int scoreIncrease = enemies.checkCollision(ship.getBullets());
            increaseScore(scoreIncrease); // Aumentar el puntaje

            enemies.removeDeadEnemies();
            
            // Verificar colisión de la nave con los enemigos
            if (enemies.checkCollision(ship)) {
                decreaseLife(35); // Reducir la vida de la nave si colisiona con un enemigo
            }
            
            // Verificar colisión de balas del enemigo con la nave
            for (Bullet bullet : enemies.getEnemyBullets()) {
                Rectangle bulletBounds = bullet.getBounds();
                Rectangle shipBounds = new Rectangle(ship.getX(), ship.getY(), 40, 40);
                if (shipBounds.intersects(bulletBounds)) {
                    // Aquí necesitas reducir la vida de la nave según la velocidad del temporizador
                    decreaseLife(1); // Disminuir la vida del jugador
                    bullet.setActive(false); // Desactivar la bala
                }
            }
        }

        repaint(); // Vuelve a dibujar la pantalla después de cada movimiento
    }



 
    // Clase interna para manejar eventos de teclado
    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                // Lógica para mover a la izquierda
                ship.movable("LEFT");
            } else if (key == KeyEvent.VK_RIGHT) {
                // Lógica para mover a la derecha
                ship.movable("RIGHT");
            } else if (key == KeyEvent.VK_UP) {
                // Lógica para mover hacia arriba
                ship.movable("UP");
            } else if (key == KeyEvent.VK_DOWN) {
                // Lógica para mover hacia abajo
                ship.movable("DOWN");
            } else if (key == KeyEvent.VK_SPACE) {
                // Lógica para disparar
                ship.shoteable();
            }
 
            repaint();
        }
    }
 
    public void event(Ship ship) {
        this.ship = ship;
        this.gameActive = true;
    }
}