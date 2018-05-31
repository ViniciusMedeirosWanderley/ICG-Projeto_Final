package opengl.part;

import static com.jogamp.opengl.GL.GL_LINEAR;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import opengl.part.emi.Emissor;
import opengl.part.emi.EmissorEsfera;
import opengl.part.emi.EmissorVertical;

/**
 *
 * @author Leonardo Villeth
 */
public class Firework extends SistemaParticulas{
    
    EmissorEsfera esfera;
    EmissorVertical vertical;
    
    private float[] init_pos;
    
    public Firework(int[] textura, float temporizador, float pTamanho) {
        super(textura, temporizador, pTamanho);
        // Adiciona emissor vertical
        EmissorVertical ev = new EmissorVertical(2, 1, temporizador*2, pTamanho);                
        super.adicionarEmissor(ev);
        //ev.setRepeat(false);   
        this.vertical = ev;
        
        // Adiciona emissor esfera
        EmissorEsfera ee = new EmissorEsfera(180, temporizador, pTamanho/2);///3        
        ee.setOn(false);        
        //ee.setRepeat(false);
        super.adicionarEmissor(ee);        
        this.esfera = ee;
    }
    
    public void setAltura(float h){
        vertical.setAlturaMax(h);
    }
    
    public void setExplosao(float size){
        esfera.setVida(size);
    }
    
    public void setRepeat(boolean repeat){
        esfera.setRepeat(repeat);
        vertical.setRepeat(repeat);
    }
    
    public void incializarParticulas(){
        emissores.forEach((emissor) -> {
            emissor.initParticulas();
        });    
    }
    
    public void setCor(float r, float g, float b){
        vertical.setCor(r, g, b);
        esfera.setCor(r, g, b);
    }

    @Override
    public void draw() {
        gl.glEnable(GL_TEXTURE_2D);
        gl.glBindTexture(GL_TEXTURE_2D, textura[0]);
        gl.glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
        
        for (Emissor e : emissores) {            
            if(!e.getOn()){
                continue;
            }
            
            Particula[] _particulas = e.getParticulas();                        
            float tam = e.getTAM();                        
            int contador = 0;
            
            for (int i = 0; i < _particulas.length; i++) {
                Particula p = _particulas[i];                                    
                                
                // So exibe Vertical uma vez 
                if(e instanceof EmissorVertical){                      
                    if(p.getVida() > p.getVidaMax()){                          
                        vertical.setOn(false);                        
                        esfera.setOn(true);                        
                        setPos( p.getPos()[0] + getPos()[0],
                                p.getPos()[1] + getPos()[1],
                                p.getPos()[2] + getPos()[2]);
                        break;
                    }
                }else{
                    if(p.getVida() > p.getVidaMax()){                                                  
                        contador++;
                        if(contador >= _particulas.length){                            
                            esfera.setOn(false);
                            vertical.setOn(true);
                            setPos(init_pos[0],init_pos[1],init_pos[2]);
                            esfera.update();
                        }                        
                    }
                }
                
                float[] pos = p.getPos();
                float x = pos[0];
                float y = pos[1];
                float z = pos[2];                                       
                
                x += getPos()[0];
                y += getPos()[1];
                z += getPos()[2];
                
                float[] cor = p.getCor();                                
                float r = cor[0];
                float g = cor[1];
                float b = cor[2];                
                
                if(e instanceof EmissorVertical){                    
                    gl.glColor4f(r, g, b, 1.0f);
                }else{
                    gl.glColor4f(r, g, b, p.getVidaMax()-p.getVida());
                }                
                
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

    @Override
    public void setPos(float x, float y, float z) {
        super.setPos(x, y, z);
        if(init_pos == null)
            init_pos = new float[]{x,y,z};
    }
    
    
    
    
}
