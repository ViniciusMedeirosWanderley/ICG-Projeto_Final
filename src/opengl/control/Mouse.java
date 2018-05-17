/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opengl.control;

import com.jogamp.opengl.awt.GLJPanel;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mouse  implements MouseMotionListener ,MouseListener{    

    private static int xCentral ;
    private static int yCentral ; 
    private  JFrame mouseFrame = null;
    private  JPanel mousePanel = null; // painel em que os eventos de mouse ocorrem
    //private final JLabel statusBar; // exibe informaç?es do evento
    private  Camera cam = null;
      
    public Mouse(JFrame mFrame, Camera camera){
            mouseFrame = mFrame;// frame em que os eventos de mouse ocorrem
            cam = camera;
        }

    public Mouse(JPanel mPanel ,Camera camera) {
        mousePanel = mPanel;
        cam = camera;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
        int xAtual = me.getX();
        int yAtual = me.getY();
        Dimension tela = mousePanel.getSize();  
        //if(xAtual > )Dimension tela = mousePanel.getSize();  
        xCentral = (int) tela.width/2;
        yCentral = (int) tela.height/2;
        
        if(xAtual > xCentral){
            cam.rotacionarY(1.0f);
        }else if(xAtual < xCentral){
            cam.rotacionarY(-1.0);
        }
        
        System.out.println("Dragged" + " in " + "X = " + xAtual + " Y = " + yAtual); 
        System.out.println("Dragged" + " in " + "X = " + tela.height + " Y = " + tela.height); 
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {     
       // System.out.println("Moved" +"in" + "X = " + x + " Y = " + y);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
        /*
        int xAtual = me.getX();
        int yAtual = me.getY();
        Dimension tela = mousePanel.getSize();  
        xCentral = (int) tela.width/2;
        yCentral = (int) tela.height/2;
        
        if(xAtual > xCentral){
            cam.rotacionarY(1.0f);
        }else if(xAtual < xCentral){
            cam.rotacionarY(-1.0);
        }
        
        System.out.println("Clicked" + " in " + "X = " + xAtual + " Y = " + yAtual); 
        System.out.println("Clicked" + " in " + "X = " + tela.height + " Y = " + tela.height); 
        
        */
    }

    @Override
    public void mousePressed(MouseEvent me) {
       // System.out.println("Pressed" +"in" + "X = " + x + " Y = " + y);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // System.out.println("Released" +"in" + "X = " + x + " Y = " + y);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println("O mouse entrou na tela");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        System.out.println("O mouse saiu da tela");
    }
   
}
