
package Logica.Historial;

import Logica.DatosImagen;
import java.util.ArrayList;


public class BufferCambios {
    
    private final ArrayList<ICambio> cambios;

    public BufferCambios() {
        cambios=new ArrayList<>();
    }
    
    public void agregarCambio(ICambio cambioAgregar){
        cambios.add(cambioAgregar);
    }
    
    public void aplicarCambios(DatosImagen img){
        cambios.forEach((cambio) -> {
            cambio.aplicarCambio(img);
        });
    }
    
    public int getTamanoOcupado (){
        return cambios.size();
    }
    
}
