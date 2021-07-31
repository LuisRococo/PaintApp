
package Logica.Dibujante.TrabajoRelated;

import Logica.Dibujante.EnumModo;

public class ModoCmbColor extends AbsModoTrabajo{

    public ModoCmbColor(Trabajo trabajo) {
        super(EnumModo.CAMBIAR_COLOR, trabajo);
    }
    
    @Override
    public void accion(int x, int y) {
        trabajo.cambiarColor(x, y);
    }
    
    
    
}
