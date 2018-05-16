package opengl.part;

import static com.jogamp.opengl.math.VectorUtil.*;

/**
 *
 * @author Leonardo Villeth
 */
public class Particula {
    
    private float[] pos;
    private float[] vel;
    private float[] cor; //RGB
    private float vida; // quanto tempo ja viveu
    private float vidaMax; // tempo maximo de vida    

    public Particula() {
        pos = new float[3];
        vel = new float[3];
        cor = new float[3];        
    }

    public float[] getPos() {
        return pos;
    }

    public void setPos(float[] pos) {
        this.pos = pos;
    }
    
    public void setPos(float x, float y, float z) {
        pos[0] = x;
        pos[1] = y;
        pos[2] = z;
    }

    public float[] getVel() {
        return vel;
    }

    public void setVel(float[] vel) {
        this.vel = vel;
    }
    
    public void setVel(float x, float y, float z) {
        vel[0] = x;
        vel[1] = y;
        vel[2] = z;
    }

    public float[] getCor() {
        return cor;
    }

    public void setCor(float[] cor) {
        this.cor = cor;
    }
    
    public void setCor(float r, float g, float b) {
        cor[0] = r;
        cor[1] = g;
        cor[2] = b;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public float getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(float vidaMax) {
        this.vidaMax = vidaMax;
    }
    
}
