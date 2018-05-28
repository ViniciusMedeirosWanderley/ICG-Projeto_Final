package opengl.part.emi;

import java.util.Arrays;
import opengl.part.Particula;

/**
 *
 * @author Leonardo Villeth
 */
public class EmissorVertical extends Emissor {
    
    float tMax;
    
    float ri,gi,bi;
    float rf,gf,bf;
    float dr,dg,db;
    
    public EmissorVertical(int qtdParticulas, float dt, float pTamanho) {
        super(qtdParticulas, dt, pTamanho);
    }

    public EmissorVertical(float tMax, int qtdParticulas, float dt, float pTamanho) {
        super(qtdParticulas, dt, pTamanho);
        this.tMax = tMax;
        
        ri = 1f;
        gi = 1f;
        bi = 1f;        
        
        rf = 1f;
        gf = 1f;
        bf = 1f;
        
        dr = rf - ri;
        dg = gf - bi;
        db = bf - bi;        
    }
    
    public void setCor(float r, float g, float b){
        rf = ri = r;
        gf = gi = g;
        bf = bi = b;
    }
    
    public void setAlturaMax(float h){
        tMax = h;
    }

    @Override
    public void criarParticula(Particula p) {
        p.setPos(0, 0, 0);                                
        
        p.setCor(ri, gi, bi);
        p.setVida(0);        
        p.setVidaMax(tMax) ;        
        
        float vx, vy, vz;
        // Emite para cima
        vx = 0;        
        vz = 0;
        vy = 1.0f;                
        
        p.setVel(vx,vy,vz);        
    }
    
}
