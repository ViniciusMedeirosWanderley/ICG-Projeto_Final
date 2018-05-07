package opengl.part;

import static com.jogamp.opengl.GL.GL_LINEAR;
import static com.jogamp.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;
import static com.jogamp.opengl.GL.GL_SRC_ALPHA;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import static com.jogamp.opengl.GL2ES2.GL_ONE_MINUS_CONSTANT_ALPHA;
import opengl.part.emi.Emissor;
import opengl.part.emi.EmissorCone;

/**
 *
 * @author Leonardo Villeth
 */
public class Fogueira extends SistemaParticulas{
    
    float posx;
    float posy;
    float posz;
    
    float icorx;
    float icory;
    float icorz;
    
    float fcorx;
    float fcory;
    float fcorz;
    
    float cordx;
    float cordy;
    float cordz;
    
    public Fogueira(int[] textura, float temporizador, float pTamanho) {
        super(textura, temporizador, pTamanho);
        
        Emissor e = new EmissorFogueira(200, temporizador, pTamanho);
        e.initParticulas();
        emissores.add(e);        
        
        posx = 0f;
        posy = 0f;
        posz = -2f;
        
        //rgb(255,215,0)
        icorx = 1f;
        icory = 165f/255f;
        icorz = 0;
        
        //rgb(255,69,0)
        fcorx = 1f;
        fcory = 69f/255f;
        fcorz = 0;
        
        cordx = fcorx - icorx;
        cordy = fcory - icory;
        cordz = fcorz - icorz;
    }
    
    public void setPos(float x, float y, float z){
        posx = x;
        posy = y;
        posz = z;
    }

    @Override
    public void draw() {
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
                                
                // cria um efeito que parece Bloom
                if((x*x + z*z) >= 1.0f)
                    gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                else {
                    gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                }
                
                x += posx;
                y += posy;
                z += posz;
                
                float[] cor = p.getCor();                                
                float r = cor[0];
                float g = cor[1];
                float b = cor[2];                
                
                gl.glColor4f(r, g, b, p.getVidaMax()-p.getVida());
                
                gl.glBegin(GL_TRIANGLE_STRIP);                    
                    gl.glTexCoord2d(1, 1);
                    gl.glVertex3f(x + tam, y + tam, z); // Top Right
                
                    gl.glTexCoord2d(0, 1);                
                    gl.glVertex3f(x - tam, y + tam, z); // Top Left
                
                    gl.glTexCoord2d(1, 0);
                    gl.glVertex3f(x + tam, y - tam, z); // Bottom Right
                
                    gl.glTexCoord2d(0, 0);
                    gl.glVertex3f(x - tam, y - tam, z); // Bottom Left
                gl.glEnd();    
            }            
        }
        gl.glDisable(GL_TEXTURE_2D);        
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
    
    
    
    private class EmissorFogueira extends EmissorCone{
    
        public EmissorFogueira(int qtdParticulas, float dt, float pTamanho) {
            super(qtdParticulas, dt, pTamanho);
        }

        @Override
        public void criarParticula(Particula p) {
            float minx = posx - 0.1f;
            float maxx = posx + 0.1f;
            float x = (minx + rand.nextFloat() * (maxx - minx));
            
            float minz = posz - 0.1f;
            float maxz = posz + 0.1f;
            float z = (minz + rand.nextFloat() * (maxz - minz));
            
            p.setPos(x, 0, z);                                    
            
            float r = icorx;
            float g = icory;
            float b = icorz;

            p.setCor(r, g, b);
            p.setVida(0);
            p.setVidaMax(rand.nextFloat());        

            float vx, vy, vz;
            // Emite em forma de cone
            vx = randFloat()/4;            
            vz = randFloat()/4;      
            vy = 1.0f;                    

            p.setVel(vx,vy,vz);
        }

        @Override
        public void update() {
            for (int i = 0; i < particulas.length; i++) {
                Particula p = particulas[i];
                float[] pos = p.getPos();
                float[] vel = p.getVel();

                // adiciono o vetor velocidade (escalado ao step) a posicao
                pos[0] += vel[0] * dt;
                pos[1] += vel[1] * dt;
                pos[2] += vel[2] * dt;            
                
                p.getCor()[0] += cordx * dt;
                p.getCor()[1] += cordy * dt;
                p.getCor()[2] += cordz * dt;                
                
                // Atualiza a vida da particula 
                // Se expirou a vida, recria a particula
                p.setVida(p.getVida() + dt);            
                if(p.getVida() > p.getVidaMax()){
                    criarParticula(p);
                }
            }
        }
    
    }
}
