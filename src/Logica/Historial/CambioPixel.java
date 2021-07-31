
package Logica.Historial;

import Logica.DatosImagen;
import java.awt.Color;

public class CambioPixel implements ICambio{
    
    private final int x;
    private final int y;
    private final Color color;

    public CambioPixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void aplicarCambio(DatosImagen imgCambiar) {
        imgCambiar.pintarPixel(color, x, y);
    }
    
    
    
}
