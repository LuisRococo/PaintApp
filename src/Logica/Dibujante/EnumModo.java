
package Logica.Dibujante;


public enum EnumModo {
    
    DIBUJO, BORRAR, RELLENAR, CAMBIAR_COLOR, OBTENER_COLOR;
    
    
    public static EnumModo getModo(String modo){
        switch(modo){
            case "DIBUJO":
                return DIBUJO;
            case "BORRAR":
                return BORRAR;
            case "RELLENAR":
                return RELLENAR;
            case "CAMBIAR_COLOR":
                return CAMBIAR_COLOR;
            case "OBTENER_COLOR":
                return OBTENER_COLOR;
            default:
                return null;
        }
    }
    
    public static String[] getEnumsString (){
        String[] regresar={
            "DIBUJO","BORRAR","RELLENAR",
            "CAMBIAR_COLOR", "OBTENER_COLOR"
        };
        return regresar;
    }
}
