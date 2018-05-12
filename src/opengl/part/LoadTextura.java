package opengl.part;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import opengl.MeuOpenGL;

/**
 *
 * @author Leonardo Villeth
 */
public class LoadTextura {
    
    static GL2 gl = MeuOpenGL.gl2;
    
    /**
     * Combina os canais de uma imagem RGB com outra imagem que 
     * representa o Alpha
     * 
     * @param arqRgb Imagem com os canais RGB
     * @param arqAlpha Imagem com o canal Alpha
     * @return Um array de pixels no formato RGBA
     * @throws IOException 
     */
    private static byte[] addAlphaChannel(File arqRgb, File arqAlpha) throws IOException{
        BufferedImage imgRGB = ImageIO.read(arqRgb);
        BufferedImage imgAlpha = ImageIO.read(arqAlpha);
        byte[] pixels = new byte[imgRGB.getWidth() * imgRGB.getHeight() * 4];        
        
        byte[] rgb = ((DataBufferByte) imgRGB.getRaster().getDataBuffer()).getData();
        byte[] alpha = ((DataBufferByte) imgAlpha.getRaster().getDataBuffer()).getData();
        
        for (int y = 0; y < imgRGB.getHeight(); y++) {
            for (int x = 0; x < imgRGB.getWidth(); x++) {
                //adiciona o rgb
                for (int i = 0; i < 3; i++) {                                        
                    pixels[4 * (y * imgRGB.getWidth() + x) + i] = rgb[3 * (y * imgRGB.getWidth() + x) + i];
                    
                            //->pixels[3 * (y * image->width + x) + j];
                }                
                //adiciona o alpha                
                pixels[4 * (y * imgRGB.getWidth() + x) + 3] = alpha[3 * (y * imgRGB.getWidth() + x)];                            
            }            
            
        }                
        
        return pixels;
    }
    
    // add rgb channel
    private static byte[] addRGBChannel(File arqRgb) throws IOException{
        BufferedImage imgRGB = ImageIO.read(arqRgb);
        
        byte[] pixels = new byte[imgRGB.getWidth() * imgRGB.getHeight() * 4];        
        
        byte[] rgb = ((DataBufferByte) imgRGB.getRaster().getDataBuffer()).getData();
        
        
        for (int y = 0; y < imgRGB.getHeight(); y++) {
            for (int x = 0; x < imgRGB.getWidth(); x++) {
                //adiciona o rgb
                for (int i = 0; i < 3; i++) {                                        
                    pixels[4 * (y * imgRGB.getWidth() + x) + i] = rgb[3 * (y * imgRGB.getWidth() + x) + i];
                    
                            //->pixels[3 * (y * image->width + x) + j];
                }                
                //adiciona o alpha                
                pixels[4 * (y * imgRGB.getWidth() + x) + 3] = 1;                            
                
            }            
            
        }                
        
        return pixels;
    }
    
    
    
    /**
     * Combina os canais de uma imagem RGB com outra imagem que 
     * representa o Alpha e retorna a textura no formato RGBA
     * 
     * @param arqRgb Imagem com os canais RGB
     * @param arqAlpha Imagem que sera baseado o canal Alpha
     * @return array com o id da textura
     * @throws IOException 
     */
    public static int[] loadTexturaAlpha(File arqRgb, File arqAlpha) throws IOException {
        int[] texturaId = new int[1];
        byte[] pixels = LoadTextura.addAlphaChannel(arqRgb, arqAlpha);
        BufferedImage img = ImageIO.read(arqRgb);
        
        gl.glGenTextures(1, texturaId, 0);
        gl.glBindTexture(GL.GL_TEXTURE_2D, texturaId[0]);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, img.getWidth(), img.getHeight(), 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, ByteBuffer.wrap(pixels));
        
        return texturaId;    
    }
    
    public static int[] loadTexturaGrass(File arqRgb) throws IOException {
        int[] texturaId = new int[1];
        byte[] pixels = LoadTextura.addRGBChannel(arqRgb);
        BufferedImage img = ImageIO.read(arqRgb);
        
        gl.glGenTextures(1, texturaId, 0);
        gl.glBindTexture(GL.GL_TEXTURE_2D, texturaId[0]);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, img.getWidth(), img.getHeight(), 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, ByteBuffer.wrap(pixels));
        
        return texturaId;    
    }
    
    
}
