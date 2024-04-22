package test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @ Angelo Pujota
 * @ OpenGl
 */
public class Proyecto_001 extends JFrame {
    private JPanel panel;
    private Circulo circulo;
    private Cuadrado cuadrado;
    private Triangulo triangulo;

    public Proyecto_001() {
        this.setSize(500, 500);
        this.setTitle("Proyecto_001");
        this.setLocation(200,300 );
        //this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();
        circulo = new Circulo();
        cuadrado = new Cuadrado();
        triangulo = new Triangulo();
    }

    private void iniciarComponentes() {
        colocarPaneles();
        colocarEtiquetas();
        colocarBotones();
    }

    private void colocarPaneles() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }

    private void colocarEtiquetas() {
        JLabel etiqueta = new JLabel("Proyecto_001");
        etiqueta.setBounds(200, 0, 100, 100);
        panel.add(etiqueta);
    }

    private void colocarBotones() {
        JButton boton1 = new JButton("Triángulo");
        boton1.setBounds(80, 100, 200, 60);
        boton1.setForeground(Color.BLUE);
        boton1.setFont(new Font("arial", Font.BOLD, 16));
        panel.add(boton1);

        JButton boton2 = new JButton("Círculo");
        boton2.setBounds(80, 180, 200, 60);
        boton2.setForeground(Color.RED);
        boton2.setFont(new Font("arial", Font.BOLD, 16));
        panel.add(boton2);

        JButton boton3 = new JButton("Cuadrado");
        boton3.setBounds(80, 260, 200, 60);
        boton3.setForeground(Color.GREEN);
        boton3.setFont(new Font("arial", Font.BOLD, 16));
        panel.add(boton3);

        ActionListener oyenteDeAccion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == boton1) {
                    System.out.println("Botón Triángulo presionado");
                    triangulo.ejecutar();
                } else if (e.getSource() == boton2) {
                    System.out.println("Botón Círculo presionado");
                    circulo.ejecutar();
                } else if (e.getSource() == boton3) {
                    System.out.println("Botón Cuadrado presionado");
                    cuadrado.ejecutar();
                }
            }
        };

        boton1.addActionListener(oyenteDeAccion);
        boton2.addActionListener(oyenteDeAccion);
        boton3.addActionListener(oyenteDeAccion);
    }

    public static void main(String[] args) {
        Proyecto_001 v1 = new Proyecto_001();
        v1.setVisible(true);
    }

}
