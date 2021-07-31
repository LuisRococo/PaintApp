
package Logica.CompGraficos;

import Logica.Geometria;
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class AbsCompGrafico {
    
    protected final EnumComponente componente;
    protected final Geometria geometria;
    private final int ordenDePintado;

    public AbsCompGrafico(EnumComponente componente, Geometria geometria, int orden) {
        this.componente = componente;
        this.geometria = geometria;
        this.ordenDePintado=orden;
    }
    
    public abstract void dibujar(Graphics g);
    
    public boolean compararComponente(EnumComponente comparar){
        return componente.equals(comparar);
    }
    
    public static ArrayList<AbsCompGrafico> organizarListaPorPrioridad(ArrayList<AbsCompGrafico> organizar){
        ArrayList<AbsCompGrafico> organizado=new ArrayList<>();
        int menorOrden;
        int posCambiar;
        while(organizar.size()>0){
            posCambiar=0;
            menorOrden=organizar.get(0).ordenDePintado;
            for (int i=0;i<organizar.size();i++){
                if (organizar.get(i).ordenDePintado < menorOrden){
                    posCambiar=i;
                    menorOrden=organizar.get(i).ordenDePintado;
                }
            }
            organizado.add(organizar.remove(posCambiar));
        }
        return organizado;
    }
}
