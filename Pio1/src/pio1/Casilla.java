/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pio1;

import java.awt.Image;

/**
 *
 * @author miaad
 */
public class Casilla {

    private int x;
    private int y;
    private int alto;
    private int ancho;
    private Image frame;
    private Image imgActive;
    private boolean activo;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.ancho = 100;
        this.alto = 100;
        this.activo = false;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setFrame(Image frame) {
        this.frame = frame;
    }

    public Image getFrame() {
        return this.frame;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImgActive() {
        return this.imgActive;
    }

    public void setImgActive(Image imgActive) {
        this.imgActive = imgActive;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

}
