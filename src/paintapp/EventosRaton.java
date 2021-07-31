package paintapp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;

public class EventosRaton implements MouseInputListener, MouseWheelListener{

    private final FramePaint frame;
    private boolean isLeftClick=false;

    public EventosRaton(FramePaint frame) {
        this.frame = frame;
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        frame.cambioZoom((int)e.getPreciseWheelRotation());
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            isLeftClick=true;
            frame.atenderClick(e.getX(), e.getY());
        } else {
            isLeftClick=false;
            frame.moverLienzo(e.getX(), e.getY());
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        frame.hoverFuera();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        frame.avisarClickTerminado();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (isLeftClick){
            frame.atenderClick(e.getX(), e.getY());   
        } else {
            frame.moverLienzo(e.getX(), e.getY());
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        frame.avisarHover(e.getX(), e.getY());
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }    
    
}
