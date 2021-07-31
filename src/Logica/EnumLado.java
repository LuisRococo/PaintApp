
package Logica;

public enum EnumLado {
    
    ARRIBA, ABAJO, DERECHA, IZQUIERDA;
    
    public static EnumLado getEnum(String str){
        switch(str){
            case "ARRIBA":
                return ARRIBA;
            case "ABAJO":
                return ABAJO;
            case "DERECHA":
                return DERECHA;
            case "IZQUIERDA":
                return IZQUIERDA;
            default:
                return null;
        }
    }
    
    public static String[] getNombres (){
        String nombres[]=new String[4];
        nombres[0]=ARRIBA.toString();
        nombres[1]=ABAJO.toString();
        nombres[2]=DERECHA.toString();
        nombres[3]=IZQUIERDA.toString();
        return nombres;
    }
}
