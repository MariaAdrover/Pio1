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
public class Hierba extends Casilla {
    private Image hierbaImg;

    public Hierba(int x, int y) {
        super(x, y);
        this.cargarImagen();
        super.setFrame(this.hierbaImg);
    }

    private void cargarImagen() {
        try {
            this.hierbaImg = ImageIO.read(new File("img/hierba.png"));
            super.setImgActive(ImageIO.read(new File("img/hierbaACTIVE.png")));
        } catch (IOException e) {
            System.out.println("Imatge no trobada");
        }
    }

    /*public Image getFrame() {
        return super.getFrame();
    }*/
}
