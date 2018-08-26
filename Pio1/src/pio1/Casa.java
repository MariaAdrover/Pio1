/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pio1;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author miaad
 */
public class Casa extends Casilla {

    private int x;
    private int y;
    private int ancho;
    private int alto;

    private Image[] casaImg;
    private int troncos;

    public Casa(int x, int y) {
        super(x, y);
        this.ancho = 100;
        this.alto = 100;
        this.troncos = 0;
        this.casaImg = new Image[4];
        this.cargarImagenes();
        super.setFrame(this.casaImg[0]);
    }

    private void cargarImagenes() {
        try {
            this.casaImg[0] = ImageIO.read(new File("img/x.png"));
            this.casaImg[1] = ImageIO.read(new File("img/c1.png"));
            this.casaImg[2] = ImageIO.read(new File("img/c2.png"));
            this.casaImg[3] = ImageIO.read(new File("img/c3.jpg"));
        } catch (IOException e) {
            System.out.println("Imatge Arbol no trobada");
        }
    }

    public void ponerTroncos() {
        this.troncos += 1;
    }

    public int getTroncos() {
        return this.troncos;
    }

    public void mejorarCasa() {
        if (this.troncos >= 10) {
            super.setAncho(300);
            super.setAlto(300);
            super.setX(1100);
            super.setY(700);
            super.setFrame(this.casaImg[3]);
        } else if (this.troncos >= 5) {
            super.setAncho(200);
            super.setAlto(200);
            super.setX(1200);
            super.setY(800);
            super.setFrame(this.casaImg[2]);
        } else if (this.troncos >= 2) {
            super.setFrame(this.casaImg[1]);
        }
    }
}
