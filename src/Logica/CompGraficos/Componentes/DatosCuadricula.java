
package Logica.CompGraficos.Componentes;

import java.awt.Color;

public class DatosCuadricula {
    
    private int xOffset;
    private int yOffset;
    private int tamAncho;
    private int tamAltura;
    private Color color;
    private int grosor;

    public DatosCuadricula() {
        this.xOffset=0;
        this.yOffset=0;
        this.tamAltura=1;
        this.tamAncho=1;
        this.grosor=1;
        this.color=Color.BLACK;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getTamAncho() {
        return tamAncho;
    }

    public void setTamAncho(int tamAncho) {
        this.tamAncho = tamAncho;
    }

    public int getTamAltura() {
        return tamAltura;
    }

    public void setTamAltura(int tamAltura) {
        this.tamAltura = tamAltura;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getGrosor() {
        return grosor;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }
    
    
}
