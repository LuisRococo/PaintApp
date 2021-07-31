package Logica.Dibujante;

import Logica.DatosImagen;
import Logica.Herramienta.DatosPincel;
import Logica.Historial.Historial;
import java.awt.Color;

public class Dibujante {

    private final DatosImagen imagen;
    private DatosPincel pincel;
    private final Historial historial;

    public Dibujante(DatosImagen imagen, Historial historial, DatosPincel pincel) {
        this.imagen = imagen;
        this.historial = historial;
        this.pincel = pincel;
    }

    public void pintar(int x, int y) {
        Color color = pincel.getColor();
        Color[][] dibujo = pincel.getPincelado();
        int xDibujo = x - pincel.getxOffset();
        int xDibujoFin = xDibujo + dibujo.length;
        int yDibujo = y - pincel.getyOffset();
        int yDibujoFin = yDibujo + dibujo[0].length;

        int xPincel = 0;
        int yPincel = 0;

        for (int i = yDibujo; i < yDibujoFin; i++) {
            for (int j = xDibujo; j < xDibujoFin; j++) {
                if (imagen.isCoorValida(j, i) && dibujo[xPincel][yPincel] != null) {
                    imagen.pintarPixel(color, j, i);
                    historial.cambioPixel(color, j, i);
                }
                xPincel++;
            }
            yPincel++;
            xPincel = 0;
        }
    }

    public void borrar(int x, int y) {
        Color[][] dibujo = pincel.getPincelado();
        int xDibujo = x - pincel.getxOffset();
        int xDibujoFin = xDibujo + dibujo.length;
        int yDibujo = y - pincel.getyOffset();
        int yDibujoFin = yDibujo + dibujo[0].length;

        int xPincel = 0;
        int yPincel = 0;

        for (int i = yDibujo; i < yDibujoFin; i++) {
            for (int j = xDibujo; j < xDibujoFin; j++) {
                if (imagen.isCoorValida(j, i) && dibujo[xPincel][yPincel] != null) {
                    imagen.pintarPixel(null, j, i);
                    historial.cambioPixel(null, j, i);
                }
                xPincel++;
            }
            yPincel++;
            xPincel = 0;
        }
    }

    public void rellenar(int x, int y) {
        if (imagen.isCoorValida(x, y)) {
            Color color = pincel.getColor();
            Color colorCambiar = imagen.getPixel(x, y);
            try {
                if (colorCambiar != null) {
                    if (colorCambiar != color) {
                        historial.cambioRellenar(color, x, y);
                        floodRellenar(color, colorCambiar, x, y);
                    }
                } else {
                    historial.cambioRellenar(color, x, y);
                    floodRellenarObjNull(color, x, y);
                }
            } catch (StackOverflowError e) {}
        }
    }

    private void floodRellenar(Color clNuevo, Color clCambiar, int x, int y) {
        if (imagen.isCoorValida(x, y)) {
            Color clPixel = imagen.getPixel(x, y);
            if (clPixel != null && clPixel.equals(clCambiar)) {
                imagen.pintarPixel(clNuevo, x, y);
                floodRellenar(clNuevo, clCambiar, x + 1, y);
                floodRellenar(clNuevo, clCambiar, x - 1, y);
                floodRellenar(clNuevo, clCambiar, x, y + 1);
                floodRellenar(clNuevo, clCambiar, x, y - 1);
            }
        }
    }

    private void floodRellenarObjNull(Color clNuevo, int x, int y) {
        if (imagen.isCoorValida(x, y)) {
            Color clPixel = imagen.getPixel(x, y);
            if (clPixel == null) {
                imagen.pintarPixel(clNuevo, x, y);
                floodRellenarObjNull(clNuevo, x + 1, y);
                floodRellenarObjNull(clNuevo, x - 1, y);
                floodRellenarObjNull(clNuevo, x, y + 1);
                floodRellenarObjNull(clNuevo, x, y - 1);
            }
        }
    }

    public void cambiarColor(int x, int y) {
        if (imagen.isCoorValida(x, y)) {
            Color color = pincel.getColor();
            Color colorCambiar = imagen.getPixel(x, y);
            historial.cambioCmbColor(color, x, y);
            if (colorCambiar != null) {
                cambiarColorAux(color, colorCambiar, x, y);
            } else {
                cambiarColorObjNull(color, x, y);
            }
        }
    }

    private void cambiarColorAux(Color clNuevo, Color cambiar, int x, int y) {
        if (imagen.isCoorValida(x, y)) {
            for (int i = 0; i < imagen.getAlto(); i++) {
                for (int j = 0; j < imagen.getAncho(); j++) {
                    if (imagen.isCoorValida(j, i)) {
                        if (imagen.getPixel(j, i) != null && imagen.getPixel(j, i).equals(cambiar)) {
                            imagen.pintarPixel(clNuevo, j, i);
                        }
                    }
                }
            }
        }
    }

    private void cambiarColorObjNull(Color clNuevo, int x, int y) {
        if (imagen.isCoorValida(x, y)) {
            for (int i = 0; i < imagen.getAlto(); i++) {
                for (int j = 0; j < imagen.getAncho(); j++) {
                    if (imagen.isCoorValida(j, i) && imagen.getPixel(j, i) == null) {
                        imagen.pintarPixel(clNuevo, j, i);
                    }
                }
            }
        }
    }

    public void limpiarLienzo() {
        imagen.limpiarImagen();
        historial.cambioLimpiarLienzo();
    }

    public Color obtenerColor(int x, int y) {
        if (imagen.isCoorValida(x, y)) {
            return imagen.getPixel(x, y);
        } else {
            return null;
        }
    }

    public void setPincel(DatosPincel pincel) {
        this.pincel = pincel;
    }

}
