
package Logica.Dibujante.TrabajoRelated;

import Logica.Dibujante.EnumModo;

public abstract class AbsModoTrabajo {
    
    protected final EnumModo modo;
    protected final Trabajo trabajo;

    public AbsModoTrabajo(EnumModo modo, Trabajo trabajo) {
        this.modo = modo;
        this.trabajo = trabajo;
    }
    
    public abstract void accion (int x, int y);
    
    public boolean compararModo(EnumModo modoComparar){
        return this.modo.equals(modoComparar);
    }

}
