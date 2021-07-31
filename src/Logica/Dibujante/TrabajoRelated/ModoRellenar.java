
package Logica.Dibujante.TrabajoRelated;

import Logica.Dibujante.EnumModo;

public class ModoRellenar extends AbsModoTrabajo{

    public ModoRellenar(Trabajo trabajo) {
        super(EnumModo.RELLENAR, trabajo);
    }
    
    @Override
    public void accion(int x, int y) {
        trabajo.rellenar(x, y);
    }
    
}
