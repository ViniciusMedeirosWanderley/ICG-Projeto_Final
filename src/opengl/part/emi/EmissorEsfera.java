package opengl.part.emi;

import static com.jogamp.opengl.math.VectorUtil.*;
import static java.lang.Math.*;
import opengl.part.Particula;

/**
 *
 * @author Leonardo Villeth
 */
public class EmissorEsfera extends Emissor {

    float ri,gi,bi;
    double fi = 0;//-90    
    double theta = 0;
    
    float tMax;

    public EmissorEsfera(int qtdParticulas, float dt, float pTamanho) {
        super(qtdParticulas, dt, pTamanho);
        
        ri = 1;
        gi = 1;
        bi = 1;
        
        tMax = 1.0f;
    }
    
    public void setCor(float r, float g, float b){
        ri = r;
        gi = g;
        bi = b;
    }
    
    public void setVida(float v){
        tMax = v;
    }

    @Override
    public void criarParticula(Particula p) {
        p.setPos(0, 0, 0);                                        
        p.setCor(ri, gi, bi);        
        p.setVida(0);        
        p.setVidaMax(tMax) ;                
        
        // Emite em esfera                
        
        double firad = toRadians(fi);
        double thetarad = toRadians(theta);
        float vx;
        float vy;
        float vz;
        
        double sinfi = sin(firad);
        double cosfi = cos(firad);
        double sino  = sin(thetarad);
        double coso  = cos(thetarad);        
        
        vx = (float)(sinfi * coso);
        vz = (float)(sinfi * sino);
        vy = (float)(cosfi);    
        
        float r = (float)random();        
        r*=1.5f;        
        vx*=r;
        vy*=r;
        vz*=random();        
        
        p.setVel(vx,vy,vz);        
        
        theta = (theta + 1) % 180;
        fi = (fi + 10) % 180; //(fi + 10) % 90;
    }
    
}
