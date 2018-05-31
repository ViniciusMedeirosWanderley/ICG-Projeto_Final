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
    
    private boolean on;
    private boolean repeat;
    
    public Emissor(int qtdParticulas, float dt, float pTamanho) {        
        this.dt = dt;
        this.TAM = pTamanho;
        this.on = true;
        this.repeat = true;
        
        particulas = new Particula[qtdParticulas];
    }
    
    public void update(){
        for (int i = 0; i < particulas.length; i++) {          
            Particula p = particulas[i];
            float[] pos = p.getPos();
            float[] vel = p.getVel();
            
            // Se expirou a vida, recria a particula                
            if(p.getVida() > p.getVidaMax()){
                if(repeat)
                    criarParticula(p);
            }
            
            // adiciono o vetor velocidade (escalado ao step) a posicao
            pos[0] += vel[0] * dt;
            pos[1] += vel[1] * dt;
            pos[2] += vel[2] * dt;            
            
            // Atualiza a vida da particula             
            p.setVida(p.getVida() + dt);        
        }
        //ordenaParticulasCresc();
        //ordenaParticulasDesc();
    }
    
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
    
    public void ordenaParticulasCresc(){        
        Arrays.sort(particulas, (Particula p1, Particula p2) -> {
            float z1 = p1.getPos()[2];
            float z2 = p2.getPos()[2];
            
            if(z1 > z2) return 1;
            if(z1 < z2) return -1;
            return 0;
        });
    }
    
    public void ordenaParticulasDesc(){        
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
    
    public boolean getOn(){
        return this.on;
    }
    
    public void setOn(boolean on){
        this.on = on;
    }
    
    public boolean getRepeat(){
        return this.repeat;
    }
    
    public void setRepeat(boolean repeat){
        this.repeat = repeat;
    }
    
}
