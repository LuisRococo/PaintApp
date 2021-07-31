
package Logica.Dibujante.TrabajoRelated;

import Logica.Dibujante.EnumModo;

public class ModoDibujo extends AbsModoTrabajo{

    public ModoDibujo(Trabajo trabajo) {
        super(EnumModo.DIBUJO, trabajo);
    }
    
    @Override
    public void accion(int x, int y) {
        trabajo.dibujar(x, y);
    }
    
    
}