package Logica.CompGraficos.Componentes;

import Logica.CompGraficos.AbsCompGrafico;
import Logica.CompGraficos.EnumComponente;
import Logica.DatosImagen;
import Logica.Geometria;
import java.awt.Color;
import java.awt.Graphics;

public class CompLienzo extends AbsCompGrafico {

    private final DatosImagen imagen;

    public CompLienzo(Geometria geometria, int orden, DatosImagen imagen) {
        super(EnumComponente.LIENZO, geometria, orden);
        this.imagen = imagen;
    }

    @Override
    public void dibujar(Graphics g) {
        int xCoorImg = 0;
        int yCoorImg = 0;
        int anchoImagen = imagen.getAncho();
        int altoImagen = imagen.getAlto();

        int anchoPanel = geometria.getAnchoPanel();
        int altoPanel = geometria.getAltoPanel();

        int xPosDibujado = xCoorImg * geometria.getTamPixel() - geometria.getxOffset();
        int yPosDibujado = yCoorImg * geometria.getTamPixel() - geometria.getyOffset();
        int tamPixel = geometria.getTamPixel();
        int aux = xPosDibujado;
        Color color;

        for (int i = yCoorImg; i < altoImagen && yPosDibujado <= altoPanel; i++) {
            for (int j = yCoorImg; j < anchoImagen && xPosDibujado <= anchoPanel; j++) {
                    color = imagen.getPixel(j, i);
                    if (color != null) {
                        g.setColor(color);
                        g.fillRect(xPosDibujado, yPosDibujado, tamPixel, tamPixel);
                    }
                
                xPosDibujado += tamPixel;
            }
            yPosDibujado += tamPixel;
            xPosDibujado = aux;
        }
    }

}
