package opengl.part.emi;

import java.util.Arrays;
import opengl.part.Particula;

/**
 *
 * @author Leonardo Villeth
 */
public abstract class Emissor {    
    
    protected Particula[] particulas;
    // tamanho da particula
    protected float TAM;
    // velocidade
    protected float dt;
    
    public Emissor(int qtdParticulas, float dt, float pTamanho) {        
        this.dt = dt;
        this.TAM = pTamanho;
        
        particulas = new Particula[qtdParticulas];
    }
    
    public abstract void update();
    
    public abstract void criarParticula(Particula p);    
    
    /**
     * Inicializa as particulas
     */
    public void initParticulas(){        
        for (int i = 0; i < particulas.length; i++) {
            Particula p = new Particula();
            criarParticula(p);
            particulas[i] = p;            
        }    
    }
    
    protected void ordenaParticulasDesc(){        
        Arrays.sort(particulas, (Particula p1, Particula p2) -> {
            float z1 = p1.getPos()[2];
            float z2 = p2.getPos()[2];
            
            if(z1 > z2) return -1;
            if(z1 < z2) return 1;
            return 0;
        });
    }

    public Particula[] getParticulas() {
        return particulas;
    }

    public float getTAM() {
        return TAM;
    }

    public void setTAM(float TAM) {
        this.TAM = TAM;
    }

    public float getDt() {
        return dt;
    }    
    
    public void setDt(float dt) {
        this.dt = dt;
    }
    
}
