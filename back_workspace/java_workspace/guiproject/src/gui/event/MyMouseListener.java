package gui.event;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MyMouseListener implements MouseListener{
    public void mouseClicked(MouseEvent e){
        System.out.println("click");
    }
    public void mouseEntered(MouseEvent e){
        System.out.println("enter");
    }
    public void mouseExited(MouseEvent e){
        System.out.println("exit");
    }
    public void mousePressed(MouseEvent e){
        System.out.println("press");
    }
    public void mouseReleased(MouseEvent e){
        System.out.println("release");
    }
}