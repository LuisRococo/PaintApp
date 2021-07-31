
package Logica;

public class Geometria {
 
    private int xOffset=0;
    private int yOffset=0;
    private int tamPixel;
    private final int tamPixelOrg;
    private int zoom=100;
    private int anchoPanel;
    private int altoPanel;
    private int anchoPixeles=1;
    private int altoPixeles=1;

    public Geometria(int tamPixelOrg, int altoPanel, int anchoPanel) {
        this.tamPixel = tamPixelOrg;
        this.tamPixelOrg = tamPixelOrg;
        this.altoPanel=altoPanel;
        this.anchoPanel=anchoPanel;
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

    public int getTamPixel() {
        return tamPixel;
    }

    public int getZoom() {
        return zoom;
    }
    
    

    public void setZoom(int zoom) {
        int tamNuevo=zoom*tamPixelOrg/100;
        if (tamNuevo<1){
            tamNuevo=1;
        }
        if (tamNuevo>150){
            tamNuevo=150;
        }
        this.zoom=zoom;
        this.tamPixel=tamNuevo;
    }
    
    private void cambiarTamPixelPorZoom(){
        this.tamPixel=zoom*tamPixelOrg/100;
    }

    public int getAnchoPanel() {
        return anchoPanel;
    }

    public void setAnchoPanel(int anchoPanel) {
        this.anchoPanel = anchoPanel;
    }

    public int getAltoPanel() {
        return altoPanel;
    }

    public void setAltoPanel(int altoPanel) {
        this.altoPanel = altoPanel;
    }

    public int getAnchoPixeles() {
        return anchoPixeles;
    }

    public void setAnchoPixeles(int anchoPixeles) {
        this.anchoPixeles = anchoPixeles;
    }

    public int getAltoPixeles() {
        return altoPixeles;
    }

    public void setAltoPixeles(int altoPixeles) {
        this.altoPixeles = altoPixeles;
    }
    
    
}
