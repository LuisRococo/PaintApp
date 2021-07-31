
package Logica.CompGraficos;

import Logica.Geometria;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class DibujanteGrafico {
    
    private ArrayList<AbsCompGrafico> compActivos;
    private final ArrayList<AbsCompGrafico> componentesGraficos;
    private Color colorFondo=new Color(255, 255, 233);
    private final Geometria geometria;

    public DibujanteGrafico(ArrayList<AbsCompGrafico> componentesGraficos, Geometria geo) {
        this.componentesGraficos=componentesGraficos;
        this.compActivos=new ArrayList<>();
        this.geometria=geo;
        for (int i=0;i<componentesGraficos.size();i++){
            compActivos.add(componentesGraficos.get(i));
        }
    }
    
    public void dibujar(Graphics g){
        g.setColor(colorFondo);
        g.fillRect(0, 0, geometria.getAnchoPanel(), geometria.getAltoPanel());
        compActivos.forEach((comp) -> {
            comp.dibujar(g);
        });
    }
    
    public void desactivarComponente(EnumComponente desactivar){
        for (AbsCompGrafico comp: compActivos){
            if (comp.compararComponente(desactivar)){
                compActivos.remove(comp);
                break;
            }
        }
    }
    
    public void activarComponente(EnumComponente activar){
        if (!isComponenteActivo(activar)){
            AbsCompGrafico compActivar=getComponente(activar);
            compActivos.add(compActivar);
            compActivos=AbsCompGrafico.organizarListaPorPrioridad(compActivos);
        }
    }
    
    public boolean isComponenteActivo(EnumComponente isActivo){
        for (int i=0;i<compActivos.size();i++){
            if (compActivos.get(i).compararComponente(isActivo)){
                return true;
            }
        }
        return false;
    }
    
    private AbsCompGrafico getComponente (EnumComponente buscar){
        AbsCompGrafico regresar=null;
        for (int i=0;i<componentesGraficos.size();i++){
            if (componentesGraficos.get(i).compararComponente(buscar)){
                regresar=componentesGraficos.get(i);
                break;
            }
        }
        return regresar;
    }
    
    public void setColorFonfo(Color nuevo){
        colorFondo=nuevo;
    }
    
    public Color getColorFondo(){
        return colorFondo;
    }
}
