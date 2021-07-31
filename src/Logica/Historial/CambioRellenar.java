package Logica.Historial;

import Logica.DatosImagen;
import java.awt.Color;

public class CambioRellenar implements ICambio {

    private final int x;
    private final int y;
    private final Color colorNuevo;

    public CambioRellenar(int x, int y, Color colorNuevo) {
        this.x = x;
        this.y = y;
        this.colorNuevo = colorNuevo;
    }

    @Override
    public void aplicarCambio(DatosImagen imgCambiar) {
        rellenar(x, y, imgCambiar);
    }

    //CODIGO REPETIDO ABAJO
    public void rellenar(int x, int y, DatosImagen imagen) {
        if (imagen.isCoorValida(x, y)) {
            Color colorCambiar = imagen.getPixel(x, y);
            try {
                if (colorCambiar != null) {
                    if (colorCambiar != colorNuevo) {
                        floodRellenar(colorNuevo, colorCambiar, x, y, imagen);
                    }
                } else {
                    floodRellenarObjNull(colorNuevo, x, y, imagen);
                }
            } catch (StackOverflowError e) {}
        }
    }

    private void floodRellenar(Color clNuevo, Color clCambiar, int x, int y, DatosImagen imagen) {
        if (imagen.isCoorValida(x, y)) {
            Color clPixel = imagen.getPixel(x, y);
            if (clPixel != null && !clPixel.equals(clCambiar) && clPixel.equals(clCambiar)) {
                imagen.pintarPixel(clNuevo, x, y);
                floodRellenar(clNuevo, clCambiar, x + 1, y, imagen);
                floodRellenar(clNuevo, clCambiar, x - 1, y, imagen);
                floodRellenar(clNuevo, clCambiar, x, y + 1, imagen);
                floodRellenar(clNuevo, clCambiar, x, y - 1, imagen);
            }
        }
    }

    private void floodRellenarObjNull(Color clNuevo, int x, int y, DatosImagen imagen) {
        if (imagen.isCoorValida(x, y)) {
            Color clPixel = imagen.getPixel(x, y);
            if (clPixel == null) {
                imagen.pintarPixel(clNuevo, x, y);
                floodRellenarObjNull(clNuevo, x + 1, y, imagen);
                floodRellenarObjNull(clNuevo, x - 1, y, imagen);
                floodRellenarObjNull(clNuevo, x, y + 1, imagen);
                floodRellenarObjNull(clNuevo, x, y - 1, imagen);
            }
        }
    }

}
