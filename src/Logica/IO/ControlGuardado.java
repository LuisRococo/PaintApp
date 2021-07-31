package Logica.IO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ControlGuardado {

    public static boolean isExisteArchivo(File archivo) {
        return archivo.exists();
    }

    public static boolean guardarImagen(Color[][] imagen, File archivo) {
        if (archivo != null) {
            int ancho = imagen.length;
            int alto = imagen[0].length;
            BufferedImage buffImg = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
            Color aux;
            Color colorAlpha=new Color(0, 0, 0, 0);
            
            for (int i=0;i<alto;i++){
                for (int j=0; j<ancho;j++){
                    aux=imagen[j][i];
                    if (aux!=null){
                        buffImg.setRGB(j, i, aux.getRGB());
                    } else {
                        buffImg.setRGB(j, i, colorAlpha.getRGB());
                    }
                }
            }
            
            try {
                ImageIO.write(buffImg, "png", archivo);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ControlGuardado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static Color[][] cargarImagen(File archivo) {
        Color[][] imagen;
        BufferedImage buffImg;
        
        try {
            buffImg=ImageIO.read(archivo);
        } catch (IOException ex) {
            Logger.getLogger(ControlGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if (buffImg==null) return null;
        
        int ancho=buffImg.getWidth();
        int alto=buffImg.getHeight();
        imagen=new Color[ancho][alto];
        Color aux;
        for (int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                aux=new Color(buffImg.getRGB(j, i));
                if (aux.getAlpha()!=255){
                    imagen[j][i]=null;
                } else {
                    imagen[j][i]=aux;
                }
            }
        }
        
        return imagen;
    }

}
