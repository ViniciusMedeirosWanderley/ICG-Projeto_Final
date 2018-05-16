package opengl.model;

import java.util.ArrayList;

/**
 *
 * @author Leonardo Villeth
 */
public class Modelo {
    
    private ArrayList<float[]> vs; //vertices
    private ArrayList<float[]> vts; //vertices da textura
    private ArrayList<float[]> vns; // vertices normais
    private ArrayList<Face> faces; //faces [v][vt][vn]

    public Modelo() {
        vs    = new ArrayList<>(3);
        vts   = new ArrayList<>();
        vns   = new ArrayList<>();
        faces = new ArrayList<>();
    }
    
    public void addVertice(float x, float y, float z){
        vs.add(new float[]{x,y,z});
    }
    
    public void addVTextura(float x, float y){
        vts.add(new float[]{x,y});
    }
    
    public void addVNormal(float x, float y, float z){
        vns.add(new float[]{x,y,z});
    }
    
    public void addFace(Face f){
        faces.add(f);
    }

    public ArrayList<float[]> getVertices() {
        return vs;
    }

    public void setVertices(ArrayList<float[]> vs) {
        this.vs = vs;
    }

    public ArrayList<float[]> getVTexts() {
        return vts;
    }

    public void setVTexts(ArrayList<float[]> vts) {
        this.vts = vts;
    }

    public ArrayList<float[]> getVNormais() {
        return vns;
    }

    public void setVNormais(ArrayList<float[]> vns) {
        this.vns = vns;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public void setFaces(ArrayList<Face> faces) {
        this.faces = faces;
    }
    
    
    
    
    
}

