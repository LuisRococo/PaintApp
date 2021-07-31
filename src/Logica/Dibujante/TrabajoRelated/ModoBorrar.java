
package Logica.Dibujante.TrabajoRelated;

import Logica.Dibujante.EnumModo;

public class ModoBorrar extends AbsModoTrabajo{

    public ModoBorrar(Trabajo trabajo) {
        super(EnumModo.BORRAR, trabajo);
    }
    
    @Override
    public void accion(int x, int y) {
        trabajo.borrar(x, y);
    }
    
    
}
