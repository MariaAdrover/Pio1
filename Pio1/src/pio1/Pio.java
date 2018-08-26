package pio1;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pio {
    private int x;
    private int y;
    private int velocidadX;
    private int velocidadY;
    private Image[] pioImg;
    //private Image pioActual;
    private int frame;
    private int ancho;
    private int alto;
    
    public Pio(int ancho, int alto) {
        this.setX(0);
        this.setY(0);
        this.setAncho(ancho);
        this.setAlto(alto);
        
        this.pioImg = new Image[3];
        this.cargarImagenes();
        
        this.frame = 0;
        
        //this.pioActual = this.pioImg[this.frame]; 
    }
    
    private void cargarImagenes() {
        try {
            this.pioImg[0] = ImageIO.read(new File("img/p1.png"));
            this.pioImg[1] = ImageIO.read(new File("img/p2.png"));
            this.pioImg[2] = ImageIO.read(new File("img/p3.png"));
            //this.pioImg[3] = ImageIO.read(new File("img/p4.png"));
        } catch (IOException e) {
            System.out.println("Imatge Pio no trobada");
        }
    }
    
    /*public Image getPioActual() {
        return this.pioActual;
    }*/
    
    public void mover() {
        this.x += this.velocidadX;
        this.y += this.velocidadY;
        
        if (this.velocidadX != 0 || this.velocidadY != 0) {
            this.setFrame();
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setVelocidadX(int x) {
        this.velocidadX = x;
    }
    
    public void setVelocidadY(int y) {
        this.velocidadY = y;        
    }
    
    public void setFrame() {        
        if (this.frame < 2) {
            this.frame++;
        } else {
            this.frame = 0;
        }
        //this.pioActual = this.pioImg[this.frame];        
    }
    
    public int getFrame() {
        return this.frame;
    }
    
    public Image getPioImg(int frame) {
        return this.pioImg[frame];
    }
    
    private void setX(int x) {
        this.x = x;
        
    }
    
    private void setY(int y) {
        this.y = y;
    }
    
    public int getAncho() {
        return this.ancho;
    }
    
    public int getAlto() {
        return this.alto;
    }
    
    private void setAncho(int ancho) {
        this.ancho = ancho;
    }
    
    private void setAlto(int alto) {
        this.alto = alto;
    }
}
