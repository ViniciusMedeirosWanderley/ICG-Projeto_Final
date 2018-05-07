package opengl;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.JFrame;

/**
 *
 * @author Leonardo Villeth
 */
public class Main {

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        capabilities.setDoubleBuffered(true);        
        
        GLCanvas canvas = new GLCanvas(capabilities);  
        //GLJPanel panel = new GLJPanel(capabilities);                
        
        JFrame frame = new JFrame("OpenGL com JOGL");                
        
        frame.setSize(800, 600);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);                
        
        canvas.addGLEventListener(new MeuOpenGL());
        //panel.addGLEventListener(new MeuOpenGL());
        
        //frame.add(panel);
        frame.add(canvas);
        
        FPSAnimator anim = new FPSAnimator(canvas, 30);
        //Animator anim = new Animator(canvas);
        anim.start();
        
        frame.setVisible(true);
    }
    
}
