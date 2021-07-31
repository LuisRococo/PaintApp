
package Logica.CompGraficos.Componentes;

import Logica.CompGraficos.AbsCompGrafico;
import Logica.CompGraficos.EnumComponente;
import Logica.Geometria;
import java.awt.Graphics;

public class CompCuadricula extends AbsCompGrafico{

    private final DatosCuadricula cuadricula;
    
    public CompCuadricula(EnumComponente componente, Geometria geometria, int orden) {
        super(componente, geometria, orden);
        cuadricula=new DatosCuadricula();
    }
    
    public DatosCuadricula getDatos(){
        return cuadricula;
    }
    
    @Override
    public void dibujar(Graphics g) {
        int intervaloVertical=cuadricula.getTamAltura()*geometria.getTamPixel();
        int intervaloHorizontal=cuadricula.getTamAncho()*geometria.getTamPixel();
        
        int posIniDib;
        int posFinDib;
        int posIniLinea;
        int posFinLinea;
        int posCuadricula;
        
        posCuadricula=cuadricula.getxOffset()*geometria.getTamPixel();
        posIniDib=-geometria.getxOffset()+posCuadricula;
        posFinDib=(geometria.getAnchoPixeles()*geometria.getTamPixel())-geometria.getxOffset();
        posIniLinea=-geometria.getyOffset();
        posFinLinea=geometria.getAltoPixeles()*geometria.getTamPixel();
        g.setColor(cuadricula.getColor());
        for (int i=posIniDib;i<=geometria.getAnchoPanel() && i<=posFinDib;i+=intervaloHorizontal){
            g.fillRect(i, posIniLinea, cuadricula.getGrosor(), posFinLinea);
        }
        
        posCuadricula=cuadricula.getyOffset()*geometria.getTamPixel();
        posIniDib=-geometria.getyOffset()+posCuadricula;
        posFinDib=(geometria.getAltoPixeles()*geometria.getTamPixel())-geometria.getyOffset();
        posIniLinea=-geometria.getxOffset();
        posFinLinea=geometria.getAnchoPixeles()*geometria.getTamPixel();
        for (int i=posIniDib;i<=geometria.getAltoPanel()&& i<=posFinDib;i+=intervaloVertical){
            g.fillRect(posIniLinea, i, posFinLinea , cuadricula.getGrosor());
        }
    }

    //ORIGINAL
//    @Override
//    public void dibujar(Graphics g) {
//        int intervaloVertical=cuadricula.getTamAltura()*geometria.getTamPixel();
//        int intervaloHorizontal=cuadricula.getTamAncho()*geometria.getTamPixel();
//        
//        int posIniDib;
//        int posFinDib;
//        int posIniLinea;
//        int posFinLinea;
//       
//        posIniDib=-geometria.getxOffset()+cuadricula.getxOffset();
//        posFinDib=(geometria.getAnchoPixeles()*geometria.getTamPixel())-geometria.getxOffset();
//        posIniLinea=-geometria.getyOffset();
//        posFinLinea=geometria.getAltoPixeles()*geometria.getTamPixel();
//        g.setColor(cuadricula.getColor());
//        for (int i=posIniDib;i<=geometria.getAnchoPanel() && i<=posFinDib;i+=intervaloHorizontal){
//            g.fillRect(i, posIniLinea, cuadricula.getGrosor(), posFinLinea);
//        }
//        
//        posIniDib=-geometria.getyOffset()+cuadricula.getyOffset();
//        posFinDib=(geometria.getAltoPixeles()*geometria.getTamPixel())-geometria.getyOffset();
//        posIniLinea=-geometria.getxOffset();
//        posFinLinea=geometria.getAnchoPixeles()*geometria.getTamPixel();
//        for (int i=posIniDib;i<=geometria.getAnchoPanel() && i<=posFinDib;i+=intervaloVertical){
//            g.fillRect(posIniLinea, i, posFinLinea , cuadricula.getGrosor());
//        }
//    }
    
}
