
package Logica.Dibujante.TrabajoRelated;

import Logica.Dibujante.EnumModo;

public class ModoAgarrarColor extends AbsModoTrabajo{

    public ModoAgarrarColor(Trabajo trabajo) {
        super(EnumModo.OBTENER_COLOR, trabajo);
    }

    @Override
    public void accion(int x, int y) {
        trabajo.obtenerColor(x, y);
    }
    
}
