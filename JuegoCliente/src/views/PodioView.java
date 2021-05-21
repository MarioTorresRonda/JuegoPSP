package views;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Player;
import model.Podio;
import utils.Assets;
import views.viewItems.ImageAsset;
import views.viewItems.JPanelImage;

public class PodioView extends JPanelImage {

	
	private static final long serialVersionUID = 1L;

	public PodioView( Podio podio ) {

		super( Assets.Podio );
		setLayout(null);
		
		if ( podio.getPlayers()[0] != null ) {
		
			Player player = podio.getPlayers()[0];
			
			JLabel personajeImg = new JLabel( new ImageIcon( ImageAsset.PersonajeImage( player.getCharacter() ).getScaledInstance(128, 128, Image.SCALE_SMOOTH) ) );
			personajeImg.setBounds(230, 120, 128, 128);
			add(personajeImg);
			
		}
		
		if ( podio.getPlayers()[1] != null ) {
			
			Player player = podio.getPlayers()[1];
			
			JLabel personajeImg = new JLabel( new ImageIcon( ImageAsset.PersonajeImage( player.getCharacter() ).getScaledInstance(128, 128, Image.SCALE_SMOOTH) ) );
			personajeImg.setBounds(366, 202, 128, 128);
			add(personajeImg);
			
		}
		
		if ( podio.getPlayers()[2] != null ) {
			
			Player player = podio.getPlayers()[2];
			
			JLabel personajeImg = new JLabel( new ImageIcon( ImageAsset.PersonajeImage( player.getCharacter() ).getScaledInstance(128, 128, Image.SCALE_SMOOTH) ) );
			personajeImg.setBounds(122, 276, 128, 128);
			add(personajeImg);
			
		}
	}
}
