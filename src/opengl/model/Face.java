package opengl.model;

import java.util.ArrayList;

/**
 *
 * @author Leonardo Villeth
 */
public class Face {
    
    // GUARDA OS INDICES DOS ARRAYS DE VERTICES TEXTURAS E NORMAIS 
    // QUE ESTAO ASSOCIADOS
    
    private ArrayList<Integer> vs;
    private ArrayList<Integer> vts;
    private ArrayList<Integer> vns;

    public Face() {
        vs  = new ArrayList<>(3);
        vts = new ArrayList<>();
        vns = new ArrayList<>();
    }
    
    public void addVertice(int v){
        vs.add(v);
    }
    
    public void addVTextura(int t){
        vts.add(t);
    }
    
    public void addVNormal(int n){
        vns.add(n);
    }

    public ArrayList<Integer> getVertices() {
        return vs;
    }

    public void setVertices(ArrayList<Integer> v) {
        this.vs = v;
    }

    public ArrayList<Integer> getVTexturas() {
        return vts;
    }

    public void setVTexturas(ArrayList<Integer> vt) {
        this.vts = vt;
    }

    public ArrayList<Integer> getVNormais() {
        return vns;
    }

    public void setVNormais(ArrayList<Integer> vn) {
        this.vns = vn;
    }
    
    
    
    
}
