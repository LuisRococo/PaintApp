
package Logica.CompGraficos.Componentes;

import Logica.CompGraficos.AbsCompGrafico;
import Logica.CompGraficos.EnumComponente;
import Logica.Geometria;
import Logica.Herramienta.Visualizar.IVisualizar;
import java.awt.Graphics;


public class CompVisualizar extends AbsCompGrafico{

    private int x, y;
    private IVisualizar visualizar;
    public CompVisualizar(EnumComponente componente, Geometria geometria, int orden) {
        super(componente, geometria, orden);
    }

    public void setComponenteVisualizar(IVisualizar nuevo){
        this.visualizar=nuevo;
    }
    
    @Override
    public void dibujar(Graphics g) {
        if (visualizar!=null){
            visualizar.visualizar(g, geometria, x, y);
        }
    }
    
    public void cambiarPosicion (int x,  int y){
        this.x=x;
        this.y=y;
    }
    
    
}
