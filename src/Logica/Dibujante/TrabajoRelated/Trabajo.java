package Logica.Dibujante.TrabajoRelated;

import Logica.ControlPaint;
import Logica.Dibujante.Dibujante;
import Logica.Dibujante.EnumModo;
import java.awt.Color;

public class Trabajo {

    private AbsModoTrabajo modoActual;
    private final Dibujante dibujante;
    private final AbsModoTrabajo[] modos;
    private final ControlPaint control;

    public Trabajo(Dibujante dibujante, ControlPaint control) {
        this.dibujante = dibujante;
        this.control = control;

        modos = new AbsModoTrabajo[6];
        modos[0] = new ModoDibujo(this);
        modos[1] = new ModoBorrar(this);
        modos[2] = new ModoCmbColor(this);
        modos[3] = new ModoRellenar(this);
        modos[4] = new ModoAgarrarColor(this);
        modoActual = modos[0];
    }

    public void cambiarModoTrabajo(EnumModo modoNuevo) {
        for (AbsModoTrabajo modo : modos) {
            if (modo.compararModo(modoNuevo)) {
                modoActual = modo;
                break;
            }
        }
    }

    public EnumModo getModoActual() {
        return modoActual.modo;
    }

    public void accion(int x, int y) {
        modoActual.accion(x, y);
    }

    void dibujar(int x, int y) {
        dibujante.pintar(x, y);
    }

    void borrar(int x, int y) {
        dibujante.borrar(x, y);
    }

    void rellenar(int x, int y) {
        dibujante.rellenar(x, y);
    }

    void cambiarColor(int x, int y) {
        dibujante.cambiarColor(x, y);
    }

    void obtenerColor(int x, int y) {
        Color obtenido = dibujante.obtenerColor(x, y);
        control.setColorGUI(obtenido);
    }
}
