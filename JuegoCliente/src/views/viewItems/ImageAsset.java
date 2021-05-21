package views.viewItems;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import utils.Assets;

public class ImageAsset {

	
	public static BufferedImage DrawImage( Image img, int ImageW, int ImageH, int CutW, int CutH, int PosX, int PosY ) {
		
		//Centrar imagen
		int offsetx = ( ImageW - CutW ) / 2;
		int offsety = ( ImageH - CutH ) / 2;
		
		
		BufferedImage output = new BufferedImage(ImageW, ImageH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(img , offsetx , offsety, CutW + offsetx, CutH + offsety, PosX, PosY, PosX + CutW, PosY + CutH, null);
        
        return output;
		
	}
	
	public static BufferedImage PersonajeImage( int num ) {
		
		int ANCHO = 48;
		int ALTO = 51;
		int columnas = 6; //personajes por fila
		int filas = num / columnas;
				
		int posx = ANCHO * (num % 6);
		int posy = ALTO * filas;
        
        return ImageAsset.DrawImage(Assets.Todos, 64, 64, ANCHO, ALTO, posx, posy);
	}
	
}
