
package Logica.Historial;

import Logica.DatosImagen;


public class CambioBorrarTodo implements ICambio{
    
    @Override
    public void aplicarCambio(DatosImagen imgCambiar) {
        imgCambiar.limpiarImagen();
    }
    
    
}
