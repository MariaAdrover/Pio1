package pio1;

import java.awt.Container;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Pio1 extends JFrame{
    
    public Pio1() {
        super("Pio 1");
        Container contenedor = this.getContentPane();
        Tablero tablero = new Tablero(this);
        contenedor.add(tablero);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Pio1 game = new Pio1();
    }
    
}
