
package Logica.Herramienta;

import java.util.ArrayList;

public class EstuchePinceles {
    
    private final ArrayList<DatosPincel> pinceles;

    public EstuchePinceles() {
        pinceles=new ArrayList<>();
        
        boolean[][] matriz=new boolean[1][1];
        matriz[0][0]=true;
        DatosPincel pincelBasico=new DatosPincel("Clasico", matriz, 0, 0);
        pinceles.add(pincelBasico);
        agregarMasPinceles();
    }
    
    private void agregarMasPinceles (){
        boolean[][] matriz={{false, true, false},
                            {true, true, true},
                            {false, true, false}};
        DatosPincel pincel=new DatosPincel("Cruz", matriz, 1, 1);
        this.agregarPincel(pincel);
        
        matriz=new boolean[3][3];
            matriz[0][0]=true; matriz[1][0]=false; matriz[2][0]=true;
            matriz[0][1]=false; matriz[1][1]=true; matriz[2][1]=false;
            matriz[0][2]=true; matriz[1][2]=false; matriz[2][2]=true;
        pincel=new DatosPincel("Equiz", matriz, 1, 1);
        this.agregarPincel(pincel);
        
        matriz=new boolean[3][3];
            matriz[0][0]=true; matriz[1][0]=true; matriz[2][0]=true;
            matriz[0][1]=true; matriz[1][1]=true; matriz[2][1]=true;
            matriz[0][2]=true; matriz[1][2]=true; matriz[2][2]=true;
        pincel=new DatosPincel("3x3", matriz, 1, 1);
        this.agregarPincel(pincel);
        
        matriz=new boolean[5][5];
        for (int i=0;i<matriz[0].length;i++){
            for (boolean[] matriz1 : matriz) {
                matriz1[i] = true;
            }
        }
        pincel=new DatosPincel("5x5", matriz, 2, 2);
        this.agregarPincel(pincel);
        
        matriz=new boolean[8][8];
            matriz[0][0]=true; matriz[1][0]=true; matriz[2][0]=false;matriz[3][0]=false; matriz[4][0]=true; matriz[5][0]=true;
            matriz[0][1]=true; matriz[1][1]=true; matriz[2][1]=false;matriz[3][1]=false; matriz[4][1]=true; matriz[5][1]=true;
            matriz[0][2]=false; matriz[1][2]=false; matriz[2][2]=true;matriz[3][2]=true; matriz[4][2]=false; matriz[5][2]=false;
            matriz[0][3]=false; matriz[1][3]=true; matriz[2][3]=true;matriz[3][3]=true; matriz[4][3]=true; matriz[5][3]=false;
            matriz[0][4]=false; matriz[1][4]=true; matriz[2][4]=true;matriz[3][4]=true; matriz[4][4]=true; matriz[5][4]=false;
            matriz[0][5]=false; matriz[1][5]=true; matriz[2][5]=false;matriz[3][5]=false; matriz[4][5]=true; matriz[5][5]=false;
        pincel=new DatosPincel("Creeper", matriz, 2, 2);
        this.agregarPincel(pincel);
    }
    
    public DatosPincel getPincelBasico (){
        return pinceles.get(0);
    }
    
    public DatosPincel buscarPincel (String nombre){
        for (DatosPincel pincel : pinceles) {
            if (pincel.getNombre().equals(nombre)){
                return pincel;
            }
        }
        return null;
    }
    
    public String[] getNombresPinceles(){
        String[] nombres=new String[pinceles.size()];
        for (int i=0;i<nombres.length;i++){
            nombres[i]=pinceles.get(i).getNombre();
        }
        return nombres;
    }
    
    public void agregarPincel (DatosPincel agregar){
        String nombrePincel=agregar.getNombre();
        String sufijo="";
        int numero=0;
        for (int i=0;i<pinceles.size();i++){
            if (isNombrePincelExistente(nombrePincel+sufijo)){
                i=0;
                numero++;
                sufijo="("+String.valueOf(numero)+")";
            }
        }
        nombrePincel+=sufijo;
        agregar.setNombre(nombrePincel);
        pinceles.add(agregar);
    }
    
    private boolean isNombrePincelExistente (String nombre){
        if (pinceles.stream().anyMatch((pincel) -> (pincel.getNombre().equals(nombre)))) {
            return true;
        }
        return false;
    }
}
