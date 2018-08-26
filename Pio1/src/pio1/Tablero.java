/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pio1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author miaad
 */
public class Tablero extends JPanel implements Runnable, KeyListener {

    private static final int PWIDTH = 1400; // size of panel 
    private static final int PHEIGHT = 1000;

    private Pio1 game;
    private Inventario inventario;

    private Casilla[][] casillas;
    private Pio pio;
    private Axe axe;
    // private Casa casa;
    private boolean[] teclasControlPio;

    private boolean accion;

    private Graphics buffGraph;
    private Image buffImage;

    public Tablero(Pio1 game) {
        this.game = game;
        this.inventario = new Inventario();
        this.setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
        this.setBackground(Color.white);

        this.teclasControlPio = new boolean[4];
        for (int i = 0; i < this.teclasControlPio.length; i++) {
            this.teclasControlPio[i] = false;
        }

        this.accion = false;

        this.addKeyListener(this);

        this.buffImage = null;

        // Crear casillas tablero
        this.casillas = new Casilla[14][10];
        this.crearCasillas(6);

        // Crear personaje
        this.pio = new Pio(100, 100);

        // Crear casa
        this.casillas[13][9] = new Casa(1300, 900);

        // Crear hacha
        this.crearAxe();

        this.setFocusable(true);
        this.requestFocus(); // JPanel now receives key events

        Thread tablero = new Thread(this);
        tablero.start();
    }

    private void crearAxe() {
        int x, y;
        do {
            double[] coordenadas = this.getCoordenadas();
            x = (int) coordenadas[0];
            y = (int) coordenadas[1];
        } while ((this.casillas[x][y] instanceof Arbol) || (x == 13 && y == 9) || (x == 0 && y == 0));

        this.axe = new Axe(x * 100, y * 100);
    }

    private void crearCasillas(int arboles) {
        this.crearArboles(arboles);
        this.crearHierba();
    }

    private void crearArboles(int arboles) {
        for (int arbol = 1; arbol <= arboles; arbol++) {
            double[] coordenadas = this.getCoordenadas();
            int x = (int) coordenadas[0];
            int y = (int) coordenadas[1];

            if (this.casillas[x][y] == null) {
                this.casillas[x][y] = new Arbol(x * 100, y * 100);
            } else {
                arbol -= arbol;
            }
        }
    }

    private double[] getCoordenadas() {
        double[] coordenadas = new double[2];
        coordenadas[0] = Math.random() * 13;
        coordenadas[1] = Math.random() * 9;
        System.out.println("x = " + coordenadas[0]);
        System.out.println("y = " + coordenadas[1]);
        return coordenadas;
    }

    private void crearHierba() {
        for (int x = 0; x < this.casillas.length; x++) {
            for (int y = 0; y < this.casillas[0].length; y++) {
                if (this.casillas[x][y] == null) {
                    this.casillas[x][y] = new Hierba(x * 100, y * 100);
                }
            }
        }
    }

    private void gameUpdate() {
        // Repasar orden
        this.regarArboles();
        this.setVelocidadPio();
        this.pio.mover();
        this.comprobarPosicionPio();
        if (this.accion) {
            this.interactuar();
            this.accion = false;
        }
        ((Casa) this.casillas[13][9]).mejorarCasa();
    }

    private void regarArboles() {
        for (int x = 0; x < this.casillas.length; x++) {
            for (int y = 0; y < this.casillas[0].length; y++) {
                if (this.casillas[x][y] instanceof Arbol) {
                    ((Arbol) this.casillas[x][y]).crecer();
                }
            }
        }
    }

    private void setVelocidadPio() {
        // 0=up, 1=down, 2=left; 3=right

        // Velocidad x
        if (this.teclasControlPio[0] == true && this.teclasControlPio[1] == false) {
            this.pio.setVelocidadX(-20);
        } else if (this.teclasControlPio[0] == false && this.teclasControlPio[1] == true) {
            this.pio.setVelocidadX(20);
        } else {
            this.pio.setVelocidadX(0);
        }

        // Velocidad y
        if (this.teclasControlPio[2] == true && this.teclasControlPio[3] == false) {
            this.pio.setVelocidadY(-20);
        } else if (this.teclasControlPio[2] == false && this.teclasControlPio[3] == true) {
            this.pio.setVelocidadY(20);
        } else {
            this.pio.setVelocidadY(0);
        }
    }

    private void comprobarPosicionPio() {
        
        for (int x = 0; x < this.casillas.length; x++) {
            for (int y = 0; y < this.casillas[0].length; y++) {
                this.casillas[x][y].setActivo(false);
            }
        }

        int x = this.pio.getX();
        int y = this.pio.getY();
        
        this.casillas[x / 100][y / 100].setActivo(true);
        this.casillas[(x + 100) / 100][y / 100].setActivo(true);
        this.casillas[x / 100][(y + 100) / 100].setActivo(true);
        this.casillas[(x + 100) / 100][(y + 100) / 100].setActivo(true);        
        
        int xAxe = this.axe.getX();
        int yAxe = this.axe.getY();
        
        if (!this.axe.getAxeRecogida() && x == xAxe && y == yAxe) {
            this.axe.recoger();
            this.inventario.setTengoAxe(true);
        }
    }

    private void interactuar() {
        for (int x = 0; x < this.casillas.length; x++) {
            for (int y = 0; y < this.casillas[0].length; y++) {
                if (this.casillas[x][y] instanceof Arbol && this.axe.getAxeRecogida()) {
                    if ((this.casillas[x][y].getActivo()) && (((Arbol) this.casillas[x][y]).getHojas() >= 100)) {
                        ((Arbol) this.casillas[x][y]).cortar();
                        ((Casa) this.casillas[13][9]).ponerTroncos();
                        this.inventario.setMateriales(((Casa) this.casillas[13][9]).getTroncos());
                    }
                }
            }
        }

    }

    private void gameRender() {
        if (this.buffImage == null) {// create the buffer 
            this.buffImage = createImage(PWIDTH, PHEIGHT);
            if (this.buffImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                this.buffGraph = this.buffImage.getGraphics();
                System.out.println("ok");
            }
        }

        this.buffGraph.setColor(Color.white);
        this.buffGraph.fillRect(0, 0, PWIDTH, PHEIGHT);
        this.pintarCasillas();
    }

    private void pintarCasillas() {

        // Pintar casillas
        for (int x = 0; x < this.casillas.length; x++) {
            for (int y = 0; y < this.casillas[0].length; y++) {
                if (!this.casillas[x][y].getActivo()) {
                    this.buffGraph.drawImage(this.casillas[x][y].getFrame(),
                            this.casillas[x][y].getX(), this.casillas[x][y].getY(),
                            this.casillas[x][y].getAncho(), this.casillas[x][y].getAlto(), this);
                } else {
                    this.buffGraph.drawImage(this.casillas[x][y].getImgActive(),
                            this.casillas[x][y].getX(), this.casillas[x][y].getY(),
                            this.casillas[x][y].getAncho(), this.casillas[x][y].getAlto(), this);
                }
            }
        }

        // Pintar hacha
        if (!this.axe.getAxeRecogida()) {
            this.buffGraph.drawImage(this.axe.getAxeImg(),
                    this.axe.getX(), this.axe.getY(), 100, 100, this);
        }

        // Pintar pio
        this.buffGraph.drawImage(this.pio.getPioImg(this.pio.getFrame()),
                this.pio.getX(), this.pio.getY(), this.pio.getAncho(), this.pio.getAlto(), this);

        // Pintar casa
        this.buffGraph.drawImage(this.casillas[13][9].getFrame(), this.casillas[13][9].getX(), this.casillas[13][9].getY(),
                this.casillas[13][9].getAncho(), this.casillas[13][9].getAlto(), this);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.buffImage != null) {
            g.drawImage(this.buffImage, 0, 0, null);
        }
    }

    @Override
    public void run() {
        while (true) {
            this.gameUpdate();
            this.gameRender();
            this.repaint();

            try {
                Thread.sleep(5); // sleep a bit            
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_I) {
            this.inventario.setVisible(!this.inventario.getAbierto());
            this.inventario.setAbierto(!this.inventario.getAbierto());
            this.requestFocus();
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            this.teclasControlPio[0] = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            this.teclasControlPio[1] = true;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            this.teclasControlPio[2] = true;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            this.teclasControlPio[3] = true;
        }

        if (key == KeyEvent.VK_E) {
            this.accion = true;
            //this.casillas[x][y].cortar();
        }
        /*else {
            this.pio.setVelocidadY(0);
            this.pio.setVelocidadX(0);
        }*/

    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*this.pio.setVelocidadY(0);
        this.pio.setVelocidadX(0);*/

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            this.teclasControlPio[0] = false;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            this.teclasControlPio[1] = false;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            this.teclasControlPio[2] = false;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            this.teclasControlPio[3] = false;
        }
    }

}
