
package Logica.Herramienta;

import java.awt.Color;

public class DatosPincel {
    
    private final Color[][] pincelado;
    private final boolean[][] matriz;
    private Color color;
    private final int xOffset;
    private final int yOffset;
    private String nombre;

    public DatosPincel(String nombre, boolean[][] matriz, int xOffset, int yOffset) {
        this.matriz = matriz;
        this.color=Color.ORANGE;
        this.xOffset=xOffset;
        this.yOffset=yOffset;
        this.nombre=nombre;
        this.pincelado=new Color[matriz.length][matriz[0].length];
        setColor(color);
    }

    public Color[][] getPincelado() {
        return pincelado;
    }

    public boolean[][] getMatriz() {
        return matriz;
    }

    public Color getColor() {
        return color;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre (String nombre){
        this.nombre=nombre;
    }

    public void setColor(Color color) {
        this.color=color;
        for (int i=0;i<pincelado[0].length;i++){
            for (int j=0;j<pincelado.length;j++){
                if (matriz[j][i]){
                    pincelado[j][i]=color;
                }
            }
        }
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
    
}
