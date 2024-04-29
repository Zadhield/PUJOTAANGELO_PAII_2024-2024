package default_package;

import javax.swing.*;

public class Panel extends JFrame {
    private Jpanel jpanel; // Agrega una instancia de Jpanel
    
    public Panel() {
        this.setSize(800, 600); // Establecer el tamaño de la ventana
        this.setTitle("GALAGA GAME");
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Para cerrar la ventana
        
        // Crea una instancia de Jpanel y agrégala al contenido de Panel
        jpanel = new Jpanel();
        this.add(jpanel);
    }
}
