
package Logica.Historial;

import Logica.DatosImagen;
import Logica.EnumLado;

public class CambioTamano implements ICambio{

    private final EnumLado lado;
    private final int tamExtra;

    public CambioTamano(EnumLado lado, int tamExtra) {
        this.lado = lado;
        this.tamExtra = tamExtra;
    }
    
    @Override
    public void aplicarCambio(DatosImagen imgCambiar) {
        imgCambiar.cambiarTamano(lado, tamExtra);
    }
    
}
