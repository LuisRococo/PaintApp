package Logica.Historial;

import Logica.DatosImagen;
import Logica.EnumLado;
import java.awt.Color;
import java.util.ArrayList;

public class Historial {

    private ArrayList<BufferCambios> historial;
    private BufferCambios bufferActual;
    private boolean cambioHecho;
    private final DatosImagen objetivo;
    private final DatosImagen base;
    private final int tamanoMaximo;
    private int posHistorial;
    private final int limiteCambiosBuffer;

    public Historial(int tamanoMaximo, int limiteCambBuffer ,DatosImagen objetivo) {
        this.tamanoMaximo = tamanoMaximo;
        this.historial = new ArrayList<>(tamanoMaximo);
        this.bufferActual = new BufferCambios();
        this.cambioHecho = false;
        this.objetivo = objetivo;
        this.base = objetivo.getCopiaObjeto();
        this.posHistorial = -1;
        this.limiteCambiosBuffer=limiteCambBuffer;
    }

    public void cambioPixel(Color color, int x, int y) {
        ICambio cambio = new CambioPixel(x, y, color);
        agregarCambio(cambio);
    }

    public void cambioTamano(EnumLado lado, int extra) {
        ICambio cambio = new CambioTamano(lado, extra);
        archivarCambios();
        agregarCambio(cambio);
    }
    
    public void cambioRellenar(Color clNuevo, int x, int y){
        ICambio cambio=new CambioRellenar(x, y, clNuevo);
        agregarCambio(cambio);
    }
    
    public void cambioCmbColor (Color clNuevo, int x, int y){
        ICambio cambio=new CambioCmbColor(x, y, clNuevo);
        agregarCambio(cambio);
    }
    
    public void cambioLimpiarLienzo (){
        ICambio cambio=new CambioBorrarTodo();
        agregarCambio(cambio);
    }
    
    private void agregarCambio (ICambio cambio){
        if (bufferActual.getTamanoOcupado()>limiteCambiosBuffer){
            archivarCambios();
        }
        bufferActual.agregarCambio(cambio);
        cambioHecho = true;
    }

    public void archivarCambios() {
        if (cambioHecho) {
            if ((posHistorial - 1) == tamanoMaximo) {
                recorrerUltimaPosicion();
                historial.add(posHistorial, bufferActual);
            } else {
                posHistorial++;
                historial.add(posHistorial, bufferActual);
                eliminarHistorialAdelantar();
            }

            bufferActual = new BufferCambios();
            cambioHecho = false;
        }
    }
    
    public void vaciarhistorial(){
        cambioHecho=false;
        bufferActual=null;
        historial=new ArrayList<>();
        posHistorial=-1;
    }

    public void adelantar() {
        if (cambioHecho) {
            archivarCambios();
        }
        if (posHistorial+1<historial.size()) {
            posHistorial++;
            aplicarCambiosHitorial();
        }
    }

    public void regresar() {
        if (cambioHecho) {
            archivarCambios();
        }
        if ((posHistorial - 1) > -2) {
            posHistorial--;
            aplicarCambiosHitorial();
        }
    }

    private void aplicarCambiosHitorial() {
        DatosImagen imgNueva = base.getCopiaObjeto();
        for (int i = 0; i <= posHistorial; i++) {
            historial.get(i).aplicarCambios(imgNueva);
        }
        objetivo.setArreglo(imgNueva.getCopiaArreglo());
    }

    private void recorrerUltimaPosicion() {
        historial.get(0).aplicarCambios(base);
        historial.remove(0);
    }

    private void eliminarHistorialAdelantar() {
        int eliminarDesde=posHistorial+1;
        for (int i=eliminarDesde;i<historial.size();i++){
            historial.remove(i);
            i--;
        }
    }
}
