package opengl.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Leonardo Villeth
 */
public class LoadModelos {
    
    /**
     * Arquivo obj DEVE TER COORD de TEXTURA ou VETORES NORMAIS
     * 
     * @param arqObj
     * @return
     * @throws IOException 
     */
    public static Modelo loadObj(File arqObj) throws IOException {
        Modelo m = new Modelo();
        
        BufferedReader br = new BufferedReader(new FileReader(arqObj));
        String linha;
        
        while ((linha = br.readLine()) != null) {                       
            String[] toks = linha.split(" ");
            
            switch(toks[0]){
                case "v":
                    m.addVertice(Float.parseFloat(toks[1]),
                                 Float.parseFloat(toks[2]),
                                 Float.parseFloat(toks[3]));
                    break;
                case "vt":
                    m.addVTextura(Float.parseFloat(toks[1]),
                                  Float.parseFloat(toks[2]));
                    break;
                case "vn":
                    m.addVNormal(Float.parseFloat(toks[1]),
                                 Float.parseFloat(toks[2]),
                                 Float.parseFloat(toks[3]));
                    break;
                case "f":
                    Face face = new Face();
                    // construo a face
                    for (int i = 1; i < toks.length; i++) {
                        String[] ar = toks[i].split("/");
                        
                        for (int j = 0; j < ar.length; j++) {
                            if(ar[j].equals(""))
                                continue;
                            
                            int indice = Integer.parseInt(ar[j]);
                            switch(j){
                                case 0:
                                    face.addVertice(indice);
                                    break;
                                case 1:
                                    face.addVTextura(indice);
                                    break;
                                case 2:
                                    face.addVNormal(indice);
                                    break;
                            }
                        }
                    }                 
                    // adiciono a face ao modelo
                    m.addFace(face);
                    break;
                
                default: 
                    // faz nada//
            }
            
        }
        
        return m;
    }

}
