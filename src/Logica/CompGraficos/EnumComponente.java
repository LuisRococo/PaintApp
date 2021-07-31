
package Logica.CompGraficos;


public enum EnumComponente {
    
    CUADRICULA, LINEAS_GUIAS, LIENZO, SELECCION, VISUALIZAR;
    
    public static EnumComponente getModo(String modo){
        switch(modo){
            case "CUADRICULA":
                return CUADRICULA;
            case "LINEAS_GUIAS":
                return LINEAS_GUIAS;
            case "LIENZO":
                return LIENZO;
            case "VISUALIZAR":
                return VISUALIZAR;
            default:
                return null;
        }
    }
    
    public static String[] getEnumsString (){
        String[] regresar={
            "CUADRICULA","LINEAS_GUIAS","LIENZO", "VISUALIZAR"
        };
        return regresar;
    }
}
