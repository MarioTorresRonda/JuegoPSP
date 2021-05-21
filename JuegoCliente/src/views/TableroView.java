package views;

import javax.swing.JPanel;

import model.Pregunta;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;
import utils.Assets;
import utils.Info;
import views.viewItems.BotonImagen;
import views.viewItems.JPanelImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextPane;

public class TableroView extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	MigLayout migLayout;
	private JPanelImage panel;
	private Thread hilo;
	
	BotonImagen[] botones = new BotonImagen[] { new BotonImagen(Assets.ButtonI[2]), new BotonImagen(Assets.ButtonI[2]),new BotonImagen(Assets.ButtonI[2]),new BotonImagen(Assets.ButtonI[2])};
	JTextPane[] jlabel = new JTextPane[] { new JTextPane() ,new JTextPane() ,new JTextPane() ,new JTextPane() };
	JTextPane Pregunta;
	Pregunta pregunta;
	
	boolean vote;
	
	public TableroView() {
		
		migLayout = new MigLayout("", "[225px][225]", "[100px][100px][100px]");
		
		setLayout( migLayout );
		
		jlabel[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
				if (vote ) {
					vote = false;
					Info.getGameClient().choose( 0 );
					showRespuesta(0);
				}
            }
        });
		
		jlabel[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
				if (vote ) {
					vote = false;
					Info.getGameClient().choose( 1 );
					showRespuesta(1);
				}
            }
        });
		
		jlabel[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
				if (vote ) {
					vote = false;
					Info.getGameClient().choose( 2 );
					showRespuesta(2);
				}
            }
        });
		
		jlabel[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
				if (vote ) {
					vote = false;
					Info.getGameClient().choose( 3 );
					showRespuesta(3);
				}
            }
        });
		
		
		add(botones[0], "cell 0 1,grow");
		add(botones[1], "cell 1 1,grow");
		add(botones[2], "cell 0 2,grow");
		add(botones[3], "cell 1 2,grow");
		
		for( int i = 0; i < botones.length;i++) {
			StyledDocument doc = jlabel[i].getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
			
			jlabel[i].setFont(new Font("Dialog", Font.BOLD, 16));
			jlabel[i].setOpaque(false);
			jlabel[i].setEditable(false);
			jlabel[i].setMargin( new Insets(40, 0, 0, 0) );
			botones[i].add( jlabel[i] );
		}
		
		
		panel = new JPanelImage( Assets.fondos[2] );
		add(panel, "cell 0 0 2 1,grow");
		panel.setLayout(new BorderLayout(0, 0));
		
		Pregunta = new JTextPane();
		Pregunta.setMargin(new Insets(10, 10, 10, 10));
		Pregunta.setForeground(new Color(255, 255, 255));
		Pregunta.setFont(new Font("Dialog", Font.BOLD, 18));
		Pregunta.setAutoscrolls(false);
		Pregunta.setOpaque(false);
		Pregunta.setEditable(false);
		
		StyledDocument doc = Pregunta.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		panel.add(Pregunta, BorderLayout.CENTER);

		hilo = new Thread(this);
		hilo.setName("Server");
		hilo.start();
		
	}
	
	public void showRespuesta(int boton) {
		
		if ( boton != pregunta.getRespuestaCorrecta() ) {
			botones[ boton ].setImage( Assets.ButtonC[2] );
		}
		botones[ pregunta.getRespuestaCorrecta()].setImage( Assets.ButtonV[2] );
	}
	
	public void UpdateButons() {
		for( BotonImagen boton : botones) {
			boton.setImage( Assets.ButtonI[2] );
		}
		vote = true;
	}
	
	public void ReSize() {
		
		panel.ReSize();
		
		int height = ( getSize().height) / 3;
		int width = ( getSize().width) / 2;
		
		migLayout.setColumnConstraints( "["+width+"px]["+width+"px]" );
		migLayout.setRowConstraints( "["+height+"px]["+height+"px]["+height+"px]" );
		
		for( int i = 0; i < botones.length;i++) {
			botones[i].Resize();
		}	
		
	}
	
	public void setPregunta( Pregunta _pregunta ) {
		
		pregunta = _pregunta;
		Pregunta.setText( "\n" + pregunta.getPregunta() );
		for( int i = 0; i < botones.length;i++) {
			jlabel[i].setText( pregunta.getRespuestas()[i]  );
		}		
	}

	@Override
	public void run() {
		while(true) {
			ReSize();
		}
		
	}

}
