package Logica.Historial;

import Logica.DatosImagen;
import java.awt.Color;

public class CambioCmbColor implements ICambio {

    private final int x;
    private final int y;
    private final Color clNuevo;

    public CambioCmbColor(int x, int y, Color clNuevo) {
        this.x = x;
        this.y = y;
        this.clNuevo = clNuevo;
    }

    @Override
    public void aplicarCambio(DatosImagen imgCambiar) {
        cambiarColor(x, y, imgCambiar);
    }

    public void cambiarColor(int x, int y,DatosImagen imagen) {
        if (imagen.isCoorValida(x, y)) {
            Color colorCambiar = imagen.getPixel(x, y);
            if (colorCambiar != null) {
                cambiarColorAux(clNuevo, colorCambiar, x, y, imagen);
            } else {
                cambiarColorObjNull(clNuevo, x, y, imagen);
            }
        }
    }

    private void cambiarColorAux(Color clNuevo, Color cambiar, int x, int y, DatosImagen imagen) {
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

    private void cambiarColorObjNull(Color clNuevo, int x, int y, DatosImagen imagen) {
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

}
