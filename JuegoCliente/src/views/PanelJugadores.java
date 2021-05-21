package views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.Player;
import utils.Assets;
import views.viewItems.JPanelImage;

public class PanelJugadores extends JPanelImage {

	private static final long serialVersionUID = 1L;

	private BufferedImage bufferedImage;
	List<Player> jugadores;
	
	public PanelJugadores(Image _fondo) {
		super( _fondo );
		jugadores = new ArrayList<>();
		
	}

	public void setJugadores( List<Player> _jugadores ) {
		jugadores = _jugadores;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);	    
		Graphics bbg = bufferedImage.getGraphics();
	
		// draws sprites
		for (int i = 0; i < jugadores.size() ; i++ ) {
			
			Player player = jugadores.get(i);
			
			bbg.drawImage( Assets.playerCS[
                           	player.getCharacter()],
							5 + i * 20, 
							player.getLastposy() , 
							5 + i * 20 + 16,
							player.getLastposy() + 16,  
							player.getState() * 16 ,
							player.getSpritepos() * 17,
							player.getState() * 16 + 16,
							player.getSpritepos() * 17 + 17,
							null );
		}
        
        g.drawImage(bufferedImage, 0, 0, this);
      
	}
	
}
