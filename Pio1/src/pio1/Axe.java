
package pio1;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Axe {
    private Image axeImg;
    private boolean recogida;
    private int x;
    private int y;
    
    public Axe(int x, int y) {
        this.x = x;
        this.y = y;
        this.recogida = false;
        this.cargarImagenes();
    }
    
    private void cargarImagenes() {
        try {
            this.axeImg = ImageIO.read(new File("img/axe.png"));
        } catch(IOException e) {
            System.out.println("Imatge Pio no trobada");            
        }
    }
    
    public Image getAxeImg() {
        return this.axeImg;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void recoger() {        
        this.recogida = true;
    }
    
    public boolean getAxeRecogida() {
        return this.recogida;
    }
}
