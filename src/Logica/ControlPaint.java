package Logica;

import Logica.CompGraficos.AbsCompGrafico;
import Logica.CompGraficos.Componentes.CompCuadricula;
import Logica.CompGraficos.Componentes.CompLienzo;
import Logica.CompGraficos.Componentes.CompVisualizar;
import Logica.CompGraficos.Componentes.DatosCuadricula;
import Logica.CompGraficos.DibujanteGrafico;
import Logica.CompGraficos.EnumComponente;
import Logica.Dibujante.Dibujante;
import Logica.Dibujante.EnumModo;
import Logica.Dibujante.TrabajoRelated.Trabajo;
import Logica.Herramienta.DatosPincel;
import Logica.Herramienta.EstuchePinceles;
import Logica.Herramienta.Visualizar.VisualizarPincel;
import Logica.Historial.Historial;
import Logica.IO.ControlGuardado;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JPanel;
import paintapp.FramePaint;

public class ControlPaint {

    private final EstuchePinceles estuche;
    private DatosPincel pincelActual;
    private DatosPincel pincelAnterior;
    private final Historial historial;
    private final DatosImagen imagen;
    private final Dibujante dibujante;
    private final Trabajo trabajo;
    private final Geometria geometria;
    private final CoordenadaLienzoHelper coorHelper;
    private final CoordenadaLienzoHelper coorHelperHover;
    private final DibujanteGrafico graficos;
    private final DatosCuadricula dtsCuadricula;
    private final DatosCuadricula dtsLineasGuias;
    private final JPanel panelLienzo;
    private final CompVisualizar visualizar;
    private final FramePaint frame;
    private File archivo;

    public ControlPaint(JPanel panelLienzo, FramePaint frame, int defaultAncho, int defaultAlto) {
        int maximoElementosHistorial = 25;
        int limiteCambBufferHist = 30;
        int tamanoPixel = 30;
        Color colorCuadricula = Color.BLACK;
        Color colorLineasGuias = Color.BLUE;
        archivo=null;

        this.panelLienzo = panelLienzo;
        this.frame=frame;
        estuche = new EstuchePinceles();
        pincelActual = estuche.getPincelBasico();
        pincelAnterior=pincelActual;

        geometria = new Geometria(tamanoPixel, panelLienzo.getWidth(), panelLienzo.getHeight());
        geometria.setAnchoPixeles(defaultAncho);
        geometria.setAltoPixeles(defaultAlto);
        coorHelper = new CoordenadaLienzoHelper(geometria);
        coorHelperHover=new CoordenadaLienzoHelper(geometria);
        imagen = new DatosImagen(defaultAncho, defaultAlto);

        historial = new Historial(maximoElementosHistorial, limiteCambBufferHist, imagen);

        dibujante = new Dibujante(imagen, historial, pincelActual);
        trabajo = new Trabajo(dibujante, this);
        trabajo.cambiarModoTrabajo(EnumModo.DIBUJO);

        CompCuadricula auxCompCuadricula;
        ArrayList<AbsCompGrafico> compGraficos = new ArrayList<>();
        compGraficos.add(new CompLienzo(geometria, 1, imagen));
        
        visualizar=new CompVisualizar(EnumComponente.VISUALIZAR, geometria, 2);
        compGraficos.add(visualizar);
        visualizar.setComponenteVisualizar(new VisualizarPincel(pincelActual));

        auxCompCuadricula = new CompCuadricula(EnumComponente.CUADRICULA, geometria, 3);
        compGraficos.add(auxCompCuadricula);
        dtsCuadricula = auxCompCuadricula.getDatos();
        dtsCuadricula.setColor(colorCuadricula);

        auxCompCuadricula = new CompCuadricula(EnumComponente.LINEAS_GUIAS, geometria, 4);
        compGraficos.add(auxCompCuadricula);
        dtsLineasGuias = auxCompCuadricula.getDatos();
        dtsLineasGuias.setColor(colorLineasGuias);
        dtsLineasGuias.setGrosor(2);
        dtsLineasGuias.setTamAncho(2);
        dtsLineasGuias.setTamAltura(3);
        
        graficos = new DibujanteGrafico(compGraficos, geometria);
    }
    
    //guarado
    public void setArchivo (File archivo){
        this.archivo=archivo;
    }
    
    public File getArchivo (){
        return archivo;
    }
    
    public Color[][] getImagenArray(){
        return imagen.getCopiaArreglo();
    }
    
    public void setImagenCargada (Color [][] imagenArray){
        imagen.setArreglo(imagenArray);
        geometria.setAltoPixeles(imagen.getAlto());
        geometria.setAnchoPixeles(imagen.getAncho());
        panelLienzo.repaint();
    }

    //Pinceles
    public void cambiarColor(Color color) {
        if (color != null) {
            pincelActual.setColor(color);
            coorHelper.setUltimaCoordenada(-1, 1);
        }
    }

    public Color getColorPincel() {
        return pincelActual.getColor();
    }

    public String[] getNombrePinceles() {
        return estuche.getNombresPinceles();
    }

    public void setPincel(String nombre, boolean remplazarAnterior) {
        DatosPincel pincelCambiar = estuche.buscarPincel(nombre);
        if (pincelCambiar != null) {
            pincelCambiar.setColor(pincelActual.getColor());
            pincelActual = pincelCambiar;
            if (remplazarAnterior) pincelAnterior=pincelActual;
            visualizar.setComponenteVisualizar(new VisualizarPincel(pincelActual));
            dibujante.setPincel(pincelActual);
            panelLienzo.repaint();
        }
    }

    //historial
    public void retrocederCambio() {
        historial.regresar();
        geometria.setAltoPixeles(imagen.getAlto());
        geometria.setAnchoPixeles(imagen.getAncho());
        panelLienzo.repaint();
    }

    public void avanzarCambio() {
        historial.adelantar();
        geometria.setAltoPixeles(imagen.getAlto());
        geometria.setAnchoPixeles(imagen.getAncho());
        panelLienzo.repaint();
    }

    //Modo y accion

    public void accion(int x, int y) {
        int[] coor = coorHelper.getCoordenadas(x, y);
        int xCoor = coor[0];
        int yCoor = coor[1];
        if (!coorHelper.isCoorRepetida(xCoor, yCoor)) {
            coorHelper.setUltimaCoordenada(xCoor, yCoor);
            trabajo.accion(xCoor, yCoor);
            visualizar.cambiarPosicion(xCoor, yCoor);
            panelLienzo.repaint();
        }
    }

    public void hover(int x, int y) {
        int[] coor = coorHelperHover.getCoordenadas(x, y);
        int xCoor = coor[0];
        int yCoor = coor[1];
        if (!coorHelperHover.isCoorRepetida(xCoor, yCoor)) {
            coorHelperHover.setUltimaCoordenada(xCoor, yCoor);
            visualizar.cambiarPosicion(xCoor, yCoor);
            panelLienzo.repaint();
        }
    }

    public String[] getModosTrabajo() {
        return EnumModo.getEnumsString();
    }

    public void setModoTrabajo(String modo) {
        EnumModo enumModo = EnumModo.getModo(modo);
        if (enumModo != null) {
            if (!enumModo.equals(EnumModo.DIBUJO) && !enumModo.equals(EnumModo.BORRAR)){
                this.setPincel(estuche.getPincelBasico().getNombre(), false);
            } else {
                this.setPincel(pincelAnterior.getNombre(), true);
            }
            coorHelper.setUltimaCoordenada(-1, -1);
            trabajo.cambiarModoTrabajo(enumModo);
        }
    }

    public void accionTerminada() {
        historial.archivarCambios();
    }
    
    public void avisarCambioModo (){
        frame.avisoCambioDeModo();
    }
    
    public String getModoActual (){
        return trabajo.getModoActual().toString();
    }
    
    public void setColorGUI (Color cl){
        if (cl==null){
            cl=graficos.getColorFondo();
        }
        trabajo.cambiarModoTrabajo(EnumModo.DIBUJO);
        frame.avisoCambioDeModo();
        frame.cambiarColor(cl);
    }
    
    public void limpiarImagen (){
        dibujante.limpiarLienzo();
    }
    
    public int getPorcentageActual (){
        return geometria.getZoom();
    }

    //Grafico
    public void pintar(Graphics g) {
        graficos.dibujar(g);
    }

    public String[] getNombreCompGraficos() {
        return EnumComponente.getEnumsString();
    }

    public boolean isCompGraficoActivo(String comp) {
        EnumComponente enumComp = EnumComponente.getModo(comp);
        if (enumComp != null) {
            return graficos.isComponenteActivo(enumComp);
        } else {
            return false;
        }
    }

    public void activarComponenteGrafico(String comp) {
        EnumComponente enumComp = EnumComponente.getModo(comp);
        if (enumComp != null) {
            graficos.activarComponente(enumComp);
        }
    }

    public void desacComponenteGrafico(String comp) {
        EnumComponente enumComp = EnumComponente.getModo(comp);
        if (enumComp != null) {
            graficos.desactivarComponente(enumComp);
        }
    }
    
    public void setColoFondoPanel (Color cl){
        graficos.setColorFonfo(cl);
        panelLienzo.repaint();
    }
    
    public Color getColorFondoPanel (){
        return graficos.getColorFondo();
    }

    //Config
    public void reiniciarPosicionLienzo (){
        geometria.setxOffset(0);
        geometria.setyOffset(0);
        panelLienzo.repaint();
    }
    
    public void cambioTamanoPanel(int ancho, int alto) {
        geometria.setAnchoPanel(ancho);
        geometria.setAltoPanel(alto);
        panelLienzo.repaint();
    }

    public Geometria getConfiguracion() {
        return geometria;
    }

    public void cambiarZoom(int nuevoZoom) {
        geometria.setZoom(nuevoZoom);
        panelLienzo.repaint();
    }

    public void cambiarPosLienzo(int xOffset, int yOffset) {
        geometria.setxOffset(geometria.getxOffset() + xOffset);
        geometria.setyOffset(geometria.getyOffset() + yOffset);
        panelLienzo.repaint();
    }

    public String[] getLadosAgregarDimension() {
        return EnumLado.getNombres();
    }

    public boolean cambiarTamanoLienzo(String lado, int agregar) {
        EnumLado ladoEnum = EnumLado.getEnum(lado);
        if (ladoEnum != null) {
            if (imagen.cambiarTamano(ladoEnum, agregar)) {
                historial.cambioTamano(ladoEnum, agregar);
                historial.archivarCambios();
                geometria.setAltoPixeles(imagen.getAlto());
                geometria.setAnchoPixeles(imagen.getAncho());
                panelLienzo.repaint();
                return true;
            }
        }
        return false;
    }

    public int[] getDimensionesImagen() {
        int[] dim = {geometria.getAnchoPixeles(), geometria.getAltoPixeles()};
        return dim;
    }

    public DatosCuadricula getDatosCuadricula() {
        return this.dtsCuadricula;
    }

    public DatosCuadricula getDatosLnGuias() {
        return this.dtsLineasGuias;
    }

    public void setDatosCuadricula(Color color, int grosor) {
        dtsCuadricula.setColor(color);
        dtsCuadricula.setGrosor(grosor);
        panelLienzo.repaint();
    }

    public void setDatosLineasGuias(Color color, int grosor, int xOffset, int yOffset, int ancho, int alto) {
        dtsLineasGuias.setColor(color);
        dtsLineasGuias.setGrosor(grosor);
        dtsLineasGuias.setxOffset(xOffset);
        dtsLineasGuias.setyOffset(yOffset);
        dtsLineasGuias.setTamAncho(ancho);
        dtsLineasGuias.setTamAltura(alto);
        panelLienzo.repaint();
    }

}

class CoordenadaLienzoHelper {

    private final Geometria geometria;
    private int xUltimoCoor = -1;
    private int yUltimoCoor = -1;

    public CoordenadaLienzoHelper(Geometria geometria) {
        this.geometria = geometria;
    }

    public int[] getCoordenadas(int x, int y) {
        int aux=x + geometria.getxOffset();
        int coorx = (x + geometria.getxOffset()) / geometria.getTamPixel();
        if (aux<0) coorx-=1;
        aux=y + geometria.getyOffset();
        int coory = (aux) / geometria.getTamPixel();
        if (aux<0) coory-=1;
        int[] coor = {coorx, coory};
        return coor;
    }

    public boolean isCoorRepetida(int xCoor, int yCoor) {
        return xCoor == xUltimoCoor && yCoor == yUltimoCoor;
    }

    public void setUltimaCoordenada(int xCoor, int yCoor) {
        this.xUltimoCoor = xCoor;
        this.yUltimoCoor = yCoor;
    }

}
