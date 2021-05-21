package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Assets {	
    public static Image[] fondos = new Image[3];
    public static Image Todos;
    public static Image[] playerCS = new Image[24];
    public static Image[] ButtonC = new Image[4];
    public static Image[] ButtonV = new Image[3];
    public static Image[] ButtonI = new Image[3];
    public static Image Cinta;
    public static Image Podio;
    
    //public static AudioClip pun;

      
    public static void loadAssets() {
    	
    	// Fondo
    	
    	for (int i = 0; i < fondos.length ; i++) {
    		fondos[i] = toImage("/assets/FondoMenu" + i + ".png");
    	}
    	
    	//Todos jugadores en grande
    	
    	Todos = toImage("/assets/All.png");

    	//Todos spritesheats de los Jugadores
    	
    	for (int i = 0; i < playerCS.length ; i++) {
    		playerCS[i] = toImage("/assets/jugador/P_" + (i + 1) + ".png");
    	}
    	
    	ButtonC[0] = toImage("/assets/ButtonC.png");
    	ButtonC[1] = toImage("/assets/ButtonCP.png");
    	ButtonC[2] = toImage("/assets/ButtonCXL.png");
    	ButtonC[3] = toImage("/assets/ButtonCL.png");
    	
    	ButtonI[0] = toImage("/assets/ButtonI.png");
    	ButtonI[1] = toImage("/assets/ButtonIP.png");
    	ButtonI[2] = toImage("/assets/ButtonIXL.png");
    	
    	ButtonV[0] = toImage("/assets/ButtonV.png");
    	ButtonV[1] = toImage("/assets/ButtonVP.png");
    	ButtonV[2] = toImage("/assets/ButtonVXL.png");
    	
    	Cinta = toImage("/assets/Cinta.png");
    	
    	Podio = toImage("/assets/Podio.png");
    	
    	
		
		
		//pun = Applet.newAudioClip(new File("assets/pun.wav").toURI().toURL());
    }
    
    public static Image toImage(String location) {
    	return new ImageIcon( Assets.class.getResource(location) ).getImage();
    }
}
