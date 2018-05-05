package opengl.part;

import static com.jogamp.opengl.GL.GL_LINEAR;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.math.VectorUtil.*;
import java.util.Random;
import opengl.MeuOpenGL;

/**
 *
 * @author Leonardo Villeth
 */
public class SistemaParticulas {
    
    private final GL2 gl = MeuOpenGL.gl2;
    
    int[] textura;
    Particula[] particulas;
    // quanto tempo para ativar cada step da animacao (velocidade da animacao)
    float tempo_step;
    // produz numeros aleatorios
    Random rand;    
    // tamanho da particula
    float TAM = 0.08f;

    public SistemaParticulas(int[] textura, int qtdParticulas, float temporizador) {
        this.textura = textura;        
        this.tempo_step = temporizador;
        particulas = new Particula[qtdParticulas];                
        
        rand = new Random();
        
        for (int i = 0; i < qtdParticulas; i++) {
            Particula p = new Particula();
            criarParticula(p);
            particulas[i] = p;
        }
    }
    
    /**
     * 
     * @return um float aletorio no intervalo [-1,1]
     */
    private float randFloat(){
        return (-1 + rand.nextFloat() * (+1 - -1));
    }
    
    public final void criarParticula(Particula p){
        p.setPos(0, 0, 0);        
        p.setCor(255, 255, 255);
        p.setVida(0);
        p.setVidaMax(rand.nextFloat() + 1) ;        
        
        float vx, vy, vz;
        // Emite em forma de cone
        vx = randFloat()/4;
        //vy = randFloat();
        vz = randFloat()/4;      
        vy = 1.0f;        
        //if(vy < 0) vy = -vy;
        
        p.setVel(vx,vy,vz);
        
        //System.out.println("vel = "+vx + " "+ vy + " "+ vz);                                
    }
    
    //float ang = 0; (nao utilizado ainda)   
    public void step(){
        //ang += (0.5f * tempo_step) % (2*Math.PI);
        
        for (int i = 0; i < particulas.length; i++) {
            Particula p = particulas[i];
            float[] pos = p.getPos();
            float[] vel = p.getVel();
            
            // adiciono o vetor velocidade (escalado ao step) a posicao
            pos[0] += vel[0] * tempo_step;
            pos[1] += vel[1] * tempo_step;
            pos[2] += vel[2] * tempo_step;
            
            //System.out.println("pos = "+pos[0] + " "+ pos[1] + " "+ pos[2]);
            //System.out.println("vel = "+vel[0] + " "+ vel[1] + " "+ vel[2]);
            
            // Atualiza a vida da particula 
            // Se expirou a vida, recria a particula
            p.setVida(p.getVida() + tempo_step);            
            if(p.getVida() > p.getVidaMax()){
                criarParticula(p);
            }
        }
    }    
    
    public void draw(){
        gl.glEnable(GL_TEXTURE_2D);
        gl.glBindTexture(GL_TEXTURE_2D, textura[0]);
        gl.glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
        
         // Build Quad From A Triangle Strip
            for (int i = 0; i < particulas.length; i++) {
                Particula p = particulas[i];
                float[] pos = p.getPos();
                float x = pos[0];
                float y = pos[1];
                float z = pos[2];                                                
                
                // emitir cores variadas ainda com problemas
                // provavelmente precisa ordenar as particulas de tras pra frente antes de rasterizar
                float[] cor = p.getCor();
                gl.glColor4f(cor[0], cor[1], cor[2], 1f);
                
                gl.glBegin(GL_TRIANGLE_STRIP);
                    gl.glTexCoord2d(1, 1);
                    gl.glVertex3f(x + TAM, y + TAM, z - 5); // Top Right
                
                    gl.glTexCoord2d(0, 1);                
                    gl.glVertex3f(x - TAM, y + TAM, z - 5); // Top Left
                
                    gl.glTexCoord2d(1, 0);
                    gl.glVertex3f(x + TAM, y - TAM, z - 5); // Bottom Right
                
                    gl.glTexCoord2d(0, 0);
                    gl.glVertex3f(x - TAM, y - TAM, z - 5); // Bottom Left
                gl.glEnd(); 
            }               
        
        gl.glDisable(GL_TEXTURE_2D);        
    }
    
}
