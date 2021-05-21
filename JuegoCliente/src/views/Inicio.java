package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import utils.Assets;
import utils.Info;

import java.awt.Color;
import java.awt.Font;

import views.viewItems.BotonImagen;
import views.viewItems.ImageAsset;
import views.viewItems.JPanelImage;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblPersonajePos;
	private int personajepos = 0;
	private JLabel personajeImg;
	
	private JPanel ImagenPersonaje;
	private JTextField TfNombre;
	
	public Inicio(  ) {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel JugarPanel = new JPanelImage(Assets.fondos[0]);
		FlowLayout flowLayout = (FlowLayout) JugarPanel.getLayout();
		flowLayout.setVgap(10);
		
		add(JugarPanel, BorderLayout.SOUTH);
		
		BotonImagen Jugar = new BotonImagen(" Jugar ", Assets.ButtonC[0], Assets.ButtonC[1]);
		Jugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jugar();
			}
		});
		JugarPanel.add(Jugar);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(new Color(176, 224, 230));
		add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel PersonajePanel = new JPanel();
		PersonajePanel.setOpaque(false);
		MainPanel.add(PersonajePanel, BorderLayout.CENTER);
		PersonajePanel.setLayout(null);
		
		BotonImagen Siguiente = new BotonImagen(" > ", Assets.ButtonC[3]);
		Siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( personajepos < 23) {
					
					personajepos++;
					
					UpdatePersonajeImage();
					lblPersonajePos.setText("Personaje " + (personajepos + 1) );
				}
			}
		});
		Siguiente.setBounds(500, 218, 40, 50);
		PersonajePanel.add(Siguiente);
		
		BotonImagen Anterior = new BotonImagen(" < ", Assets.ButtonC[3]);
		Anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( personajepos > 0) {
					
					personajepos--;
					
					UpdatePersonajeImage();
					lblPersonajePos.setText("Personaje " + (personajepos + 1) );
				}
				
			}
		});
		Anterior.setBounds(260, 218, 40, 50);
		PersonajePanel.add(Anterior);
		
		ImagenPersonaje = new JPanel();
		ImagenPersonaje.setOpaque(false);
		ImagenPersonaje.setBounds(336, 178, 128, 128);
		PersonajePanel.add(ImagenPersonaje);
		ImagenPersonaje.setLayout(null);
		
		personajeImg = new JLabel( new ImageIcon( ImageAsset.PersonajeImage( personajepos ).getScaledInstance(128, 128, Image.SCALE_SMOOTH) ) );
		personajeImg.setBounds(0, 0, 128, 128);
		ImagenPersonaje.add(personajeImg);
        
		lblPersonajePos = new JLabel( "Personaje " + (personajepos + 1) );
		lblPersonajePos.setForeground(Color.WHITE);
		lblPersonajePos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonajePos.setFont(new Font("Dialog", Font.PLAIN, 18));
		MainPanel.add(lblPersonajePos, BorderLayout.SOUTH);
		
		JPanel NombrePanel = new JPanelImage(Assets.fondos[0]);
		add(NombrePanel, BorderLayout.NORTH);
		NombrePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10) );
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 22));
		NombrePanel.add(lblNombre);
		
		TfNombre = new JTextField();
		TfNombre.setHorizontalAlignment(SwingConstants.LEFT);
		TfNombre.setColumns(16);
		NombrePanel.add(TfNombre);
		
	}
	
	public void UpdatePersonajeImage()  {

		personajeImg.setIcon(( new ImageIcon( ImageAsset.PersonajeImage( personajepos ).getScaledInstance(128, 128, Image.SCALE_SMOOTH) ) ) ); 
		personajeImg.updateUI();
	}
	
	public void Jugar() {
		if ( TfNombre.getText().length() > 3 ) {
			Info.setSprite(personajepos);
			Info.setNombre(TfNombre.getText());	
			
			Info.getMain().toMain();
		}		
	}
}
