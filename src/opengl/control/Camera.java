package opengl.control;

import static com.jogamp.opengl.math.VectorUtil.*;

/**
 *
 * @author Leonardo Villeth
 */
public class Camera {    
    
    private float[] pos;
    private float[] look;
    private float[] up;    
    
    private double dir;

    public Camera() {
        pos  = new float[3];
        look = new float[3];
        up   = new float[3];        
        dir  = 0;
    }
    
    public float getDir(){
        float ang = ((float)dir)/360.0f;        
        return ang;
    }
    
    public void rotacionarY(double theta){         
        dir = (dir + theta) % 360;
        
        float[] direcao = new float[3];
        direcao = subVec3(direcao, pos, look);
        
        double ang;
        
        if(Math.abs(dir) > 180)
            ang = angleVec3(VEC3_UNIT_Z_NEG, direcao);
        else
            ang = angleVec3(VEC3_UNIT_Z, direcao);
        
        if(dir < 0)
            ang = -ang;
        
        ang += Math.toRadians(theta);        
        
        if(Math.abs(dir) == 180)
            ang = Math.PI;
        else if(dir == 0)
            ang = 0;
        
        if(Math.abs(dir) > 180){                
            look[0] = (float)(-Math.sin(ang) + pos[0]);
            look[2] = (float)(pos[2] + Math.cos(ang));            
        }else{        
            look[0] = (float)(Math.sin(ang) + pos[0]);
            look[2] = (float)(pos[2] - Math.cos(ang));            
        }
        
        System.out.println("Direcao: "+dir+"\n");
    }
    
    public void transladar(double dx, double dy, double dz){
        pos[0] += dx;
        pos[1] += dy;
        pos[2] += dz;
    }    
    
    public void moveFrente(){
        float[] direcao = new float[3];
        subVec3(direcao, look, pos);        
        
        float[] norm = normalizeVec3(direcao);     
        
        scaleVec3(norm, norm, 0.1f);
        
        addVec3(pos, pos, norm);
        addVec3(look, look, norm);  
    }    
    
    public void moveTras(){
        float[] direcao = new float[3];
        subVec3(direcao, look, pos);        
        
        float[] norm = normalizeVec3(direcao);        
        
        scaleVec3(norm, norm, 0.1f);
        
        subVec3(pos, pos, norm);
        subVec3(look, look, norm);  
    }    
    
    public void moveEsq(){
        float[] direcao = new float[3];
        subVec3(direcao, look, pos);        
        
        float[] esquerda = new float[3];
        crossVec3(esquerda, up, direcao);
        
        float[] norm = normalizeVec3(esquerda);    
        
        scaleVec3(norm, norm, 0.1f);
        
        addVec3(pos, pos, norm);
        addVec3(look, look, norm);  
    }
    
    public void moveDir(){
        float[] direcao = new float[3];
        subVec3(direcao, look, pos);        
        
        float[] direita = new float[3];
        crossVec3(direita, direcao, up);
        
        float[] norm = normalizeVec3(direita);
        
        scaleVec3(norm, norm, 0.1f);
        
        addVec3(pos, pos, norm);
        addVec3(look, look, norm);  
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

    public float[] getLook() {
        return look;
    }

    public void setLook(float[] look) {
        this.look = look;
    }
    
    public void setLook(float x, float y, float z) {
        look[0] = x;
        look[1] = y;
        look[2] = z;
    }

    public float[] getUp() {
        return up;
    }

    public void setUp(float[] up) {
        this.up = up;
    }
    
    public void setUp(float x, float y, float z) {
        up[0] = x;
        up[1] = y;
        up[2] = z;
    }
    
    
}
