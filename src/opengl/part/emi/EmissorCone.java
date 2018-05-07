package opengl.part.emi;

import java.util.Random;
import opengl.part.Particula;

/**
 *
 * @author Leonardo Villeth
 */
public class EmissorCone extends Emissor{
    
    // produz numeros aleatorios
    protected Random rand;

    public EmissorCone(int qtdParticulas, float dt, float pTamanho) {
        super(qtdParticulas, dt, pTamanho);
        
        rand = new Random();
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
            
            // Atualiza a vida da particula 
            // Se expirou a vida, recria a particula
            p.setVida(p.getVida() + dt);            
            if(p.getVida() > p.getVidaMax()){
                criarParticula(p);
            }
        }
        //ordenaParticulasDesc();
    }

    @Override
    public void criarParticula(Particula p) {        
        p.setPos(0, 0, 0);                        
        // Usar valores 0 e 1 apenas remove o "blur"
        //float r = rand.nextInt(2);
        //float g = rand.nextInt(2);
        //float b = rand.nextInt(2);        
        
        float r = Math.abs(randFloat());
        float g = Math.abs(randFloat());
        float b = Math.abs(randFloat());
        
        p.setCor(r, g, b);
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
    
    /**
     * 
     * @return um float aletorio no intervalo [-1,1]
     */
        protected float randFloat(){
        return (-1 + rand.nextFloat() * (+1 - -1));
    }
    
}
