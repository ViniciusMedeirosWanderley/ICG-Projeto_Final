package opengl.part;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_LINEAR;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.math.VectorUtil.*;
import java.util.ArrayList;
import java.util.Random;
import opengl.MeuOpenGL;
import opengl.part.emi.Emissor;
import opengl.part.emi.EmissorCone;

/**
 *
 * @author Leonardo Villeth
 */
public class SistemaParticulas {
    
    protected final GL2 gl = MeuOpenGL.gl2;
    
    int[] textura;    
    // quanto tempo para ativar cada step da animacao (velocidade da animacao)
    float tempo_step;
    // tamanho da particula
    float TAM;
    // emissores que compoe o sistema
    //Emissor[] emissores;        
    ArrayList<Emissor> emissores;    
    
    private float[] pos;

    public SistemaParticulas(int[] textura, float temporizador, float pTamanho) {
        this.textura = textura;        
        this.tempo_step = temporizador;        
        this.TAM = pTamanho;
        this.pos = new float[3];
        
        emissores = new ArrayList<>(5);
    }
    
    public void criarEmissorDefault(int qtd){
        Emissor e = new EmissorCone(qtd, tempo_step, TAM);
        emissores.add(e); 
        
        e.initParticulas();        
    }    
    
    public void adicionarEmissor(Emissor e){
        emissores.add(e);
    }
    
    //float ang = 0; (nao utilizado ainda)   
    public void step(){
        //ang += (0.5f * tempo_step) % (2*Math.PI);        
        for (Emissor e : emissores) {
            if(e.getOn()){                
               e.update();
            }
        }
    }    
    
    public void draw(){
        gl.glEnable(GL_TEXTURE_2D);
        gl.glBindTexture(GL_TEXTURE_2D, textura[0]);
        gl.glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
        
        for (Emissor e : emissores) {
            Particula[] _particulas = e.getParticulas();
            
            float tam = e.getTAM();
            
            for (int i = 0; i < _particulas.length; i++) {
                Particula p = _particulas[i];
                float[] pos = p.getPos();
                float x = pos[0];
                float y = pos[1];
                float z = pos[2];                                       
                
                float[] cor = p.getCor();                                
                float r = cor[0];
                float g = cor[1];
                float b = cor[2];
                
                //System.out.println("cor"+ i +" = "+r+" "+g+" "+b);                                                
                gl.glColor4f(r, g, b, p.getVidaMax()-p.getVida());
                
                gl.glBegin(GL_TRIANGLE_STRIP);                    
                    gl.glTexCoord2d(1, 1);
                    gl.glVertex3f(x + tam, y + tam, z - 5); // Top Right
                
                    gl.glTexCoord2d(0, 1);                
                    gl.glVertex3f(x - tam, y + tam, z - 5); // Top Left
                
                    gl.glTexCoord2d(1, 0);
                    gl.glVertex3f(x + tam, y - tam, z - 5); // Bottom Right
                
                    gl.glTexCoord2d(0, 0);
                    gl.glVertex3f(x - tam, y - tam, z - 5); // Bottom Left
                gl.glEnd();    
            }            
        }
        gl.glDisable(GL_TEXTURE_2D);
    }
    
    public void setPos(float x, float y, float z){
        this.pos[0] = x;
        this.pos[1] = y;
        this.pos[2] = z;
    }
    
    public float[] getPos(){
        return pos;
    }
    
}
