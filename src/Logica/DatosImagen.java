package Logica;

import java.awt.Color;

public class DatosImagen {

    private Color[][] imagen;

    public DatosImagen(int ancho, int alto) {
        imagen = new Color[ancho][alto];
    }

    public void pintarPixel(Color cl, int x, int y) {
        imagen[x][y] = cl;
    }

    public void borrarPixel(int x, int y) {
        imagen[x][y] = null;
    }

    public boolean isCoorValida(int x, int y) {
        return (x >= 0 && x < imagen.length) && (y >= 0 && y < imagen[0].length);
    }

    public boolean cambiarTamano(EnumLado lado, int espacioExtra) {
        int anchoOrg = imagen.length;
        int altoOrg = imagen[0].length;
        int nuevoAncho=anchoOrg;
        int nuevoAlto=altoOrg;
        Color[][] nuevo;
        int xPos = 0;
        int yPos = 0;
        int nuevaDim;

        if (lado.equals(EnumLado.ABAJO) || lado.equals(EnumLado.ARRIBA)) {
            nuevaDim = altoOrg + espacioExtra;
            nuevoAlto=nuevaDim;
        } else {
            nuevaDim = anchoOrg + espacioExtra;
            nuevoAncho=nuevaDim;
        }
        
        if (nuevaDim < 1) {
            return false;
        }
        nuevo = new Color[nuevoAncho][nuevoAlto];

        switch (lado) {
            case ARRIBA:
                yPos = espacioExtra;
                break;
            case IZQUIERDA:
                xPos = espacioExtra;
                break;
        }
        rellenaNuevoTamano(nuevo, xPos, yPos);
        this.imagen = nuevo;
        return true;
    }

    private void rellenaNuevoTamano(Color[][] nuevo, int xPos, int yPos) {
        int xPosAnt = xPos;
        int yPosAnt = yPos;

        for (int i = 0; i < imagen[0].length; i++) {
            if (yPosAnt >= 0 && yPosAnt < nuevo[0].length) {
                for (Color[] imagen1 : imagen) {
                    if (xPosAnt>=0 && xPosAnt<nuevo.length) {
                        nuevo[xPosAnt][yPosAnt] = imagen1[i];
                    }
                    xPosAnt++;
                }
            }
            yPosAnt++;
            xPosAnt=xPos;
        }
    }

    public Color getPixel(int x, int y) {
        return imagen[x][y];
    }

    public int getAncho() {
        return imagen.length;
    }

    public int getAlto() {
        return imagen[0].length;
    }

    public Color[][] getCopiaArreglo() {
        Color[][] copia = new Color[imagen.length][imagen[0].length];
        for (int i = 0; i < copia[0].length; i++) {
            for (int j = 0; j < copia.length; j++) {
                copia[j][i] = imagen[j][i];
            }
        }
        return copia;
    }

    public DatosImagen getCopiaObjeto() {
        DatosImagen copia = new DatosImagen(getAncho(), getAlto());
        for (int i = 0; i < copia.getAlto(); i++) {
            for (int j = 0; j < copia.getAncho(); j++) {
                copia.pintarPixel(getPixel(j, i), j, i);
            }
        }
        return copia;
    }

    public void setArreglo(Color[][] nuevo) {
        this.imagen = nuevo;
    }
    
    public void limpiarImagen (){
        for (int i=0;i<imagen[0].length;i++){
            for (Color[] imagen1 : imagen) {
                imagen1[i] = null;
            }
        }
    }

}
