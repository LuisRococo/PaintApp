
package Logica.Herramienta.Visualizar;

import Logica.Geometria;
import Logica.Herramienta.DatosPincel;
import java.awt.Color;
import java.awt.Graphics;

public class VisualizarPincel implements IVisualizar{

    private final DatosPincel pincel;
    public VisualizarPincel(DatosPincel pincel) {
        this.pincel = pincel;
    }
    
    @Override
    public void visualizar(Graphics g, Geometria geometria, int x, int y) {
        int tamPixel=geometria.getTamPixel();
        int xPosORG=(-geometria.getxOffset()+x*tamPixel)-pincel.getxOffset()*tamPixel;
        int xPos=xPosORG;
        int yPos=(-geometria.getyOffset()+y*tamPixel)-pincel.getyOffset()*tamPixel;
        Color pincelada[][]=pincel.getPincelado();
        
        g.setColor(pincel.getColor());
        for (int i=0;i<pincelada[0].length;i++){
            for (Color[] pincelada1 : pincelada) {
                if (pincelada1[i] != null) {
                    g.fillRect(xPos, yPos, tamPixel, tamPixel);
                }
                xPos+=tamPixel;
            }
            yPos+=tamPixel;
            xPos=xPosORG;
        }
    }
    
}
