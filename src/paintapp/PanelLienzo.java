
package paintapp;

import Logica.ControlPaint;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelLienzo extends JPanel{
    
    private ControlPaint control;

    public PanelLienzo() {}
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        control.pintar(g);
    }
    
    public void setControl (ControlPaint control){
        this.control=control;
    }
}
