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
public class Arbol extends Casilla {

    private int x;
    private int y;
    private double hojas;
    private Image[] arbolImg;
    private int ancho;
    private int alto;

    public Arbol(int x, int y) {
        super(x, y);

        this.arbolImg = new Image[4];
        this.cargarImagenes();
        super.setFrame(this.arbolImg[this.arbolImg.length - 1]);
        super.setImgActive(this.arbolImg[3]);

        this.hojas = 100;
    }

    private void cargarImagenes() {
        try {
            this.arbolImg[0] = ImageIO.read(new File("img/tronco.png"));
            this.arbolImg[1] = ImageIO.read(new File("img/arbol.png"));
            this.arbolImg[2] = ImageIO.read(new File("img/troncoACTIVE.png"));
            this.arbolImg[3] = ImageIO.read(new File("img/arbolACTIVE.png"));
        } catch (IOException e) {
            System.out.println("Imatge Arbol no trobada");
        }
    }

    public void cortar() {
        this.hojas = 0;
        System.out.println("hojas: " + this.hojas);
        super.setFrame(this.arbolImg[0]);
        super.setImgActive(this.arbolImg[2]);
    }

    public void crecer() {
        if (this.hojas < 100) {
            this.hojas += 0.5;
            System.out.println("hojas: " + this.hojas);
        } else {
            super.setFrame(this.arbolImg[1]);
            super.setImgActive(this.arbolImg[3]);
        }
    }
    
    public double getHojas() {        
        return this.hojas;
    }

   /* public Image getFrame() {
        return super.getFrame();
    }*/
    
}
