/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pio1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class Inventario extends JFrame {

    private static final int PWIDTH = 1300; // size of panel 
    private static final int PHEIGHT = 1000;
    private boolean abierto;
    private boolean tengoAxe;
    private JPanel panel;
    private JLabel numeroTroncos;
    private JLabel textoAxe;

    public Inventario() {
        super("Inventario");
        this.setSize(PWIDTH, PHEIGHT);
        this.setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        this.setLocation(1400, 0);

        Container contenedor = this.getContentPane();
        this.panel = new JPanel();
        panel.setBackground(Color.pink);
        
        // Scroll inventario
        //JScrollPane scroll = new JScrollPane();
        //scroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        //scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        //scroll.setSize(PWIDTH, PHEIGHT);
        //scroll.setVisible(true);
        
        // Panel del scroll
        //JPanel panelScroll = new JPanel();
        
        // Instrucciones
        JLabel instrucciones = new JLabel("--> Mover Pio: A,D,W,S  o  flechas       ");
        JLabel instrucciones2 = new JLabel("--> Acción: E                           ");
        JLabel instrucciones3 = new JLabel("--> Abrir/Cerrar inventario: I                ");
        instrucciones.setFont(new Font("Courier New", Font.BOLD, 40));
        instrucciones2.setFont(new Font("Courier New", Font.BOLD, 40));
        instrucciones3.setFont(new Font("Courier New", Font.BOLD, 40));
        // Axe
        JLabel imagenAxe = new JLabel();
        ImageIcon icono = new ImageIcon("img/axe2.png");
        imagenAxe.setIcon(icono);
        
        this.textoAxe = new JLabel();
        this.setTengoAxe(false);
        this.textoAxe.enableInputMethods(false);
        this.textoAxe.setFont(new Font("Courier New", Font.BOLD, 32));
        
        this.tengoAxe = false;
        
        // Troncos
        JLabel imagenTroncos = new JLabel();
        icono = new ImageIcon("img/lenya2.png");
        imagenTroncos.setIcon(icono);
        
        this.numeroTroncos = new JLabel("0");
        this.numeroTroncos.enableInputMethods(false);
        this.numeroTroncos.setFont(new Font("Courier New", Font.BOLD, 60));
        
        // Afegir contingut
        //panelScroll.add(imagenAxe);   
        //panelScroll.add(this.textoAxe);     
        //panelScroll.add(imagenTroncos);
        //panelScroll.add(this.numeroTroncos); 
        
        //scroll.add(panelScroll);
        
        //panel.add(scroll);
        panel.add(instrucciones); 
        panel.add(instrucciones2); 
        panel.add(instrucciones3); 
        
        panel.add(imagenAxe);   
        panel.add(this.textoAxe);     
        panel.add(imagenTroncos);
        panel.add(this.numeroTroncos); 

        contenedor.add(panel);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    public boolean getAbierto() {
        return this.abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public void abrirInventario() {
        this.setVisible(true);
    }
    
    public void setMateriales(int numero) {
        String texto = String.valueOf(numero);
        this.numeroTroncos.setText(texto);
    }
    
    public void setTengoAxe(boolean estadoAxe) {
        this.tengoAxe = estadoAxe;
        if (this.tengoAxe) {
        this.textoAxe.setText("Tienes un hacha. Puedes cortar árboles para construir tu casa");
            
        } else {
        this.textoAxe.setText("Busca un hacha                        ");
            
        }
    }
    
    public boolean getTengoAxe() {
        return this.tengoAxe;
    }
}
