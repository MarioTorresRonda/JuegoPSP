package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import utils.Assets;
import utils.Info;
import model.Lobby;
import model.Podio;
import model.Pregunta;
import model.Tablero;
import views.viewItems.BotonImagen;
import views.viewItems.JPanelImage;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Game extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField Mensaje;
	private JTextArea Chat;
	private JPanel panelCentral;
	private JPanel panelAbsoluto;
	private TableroView tableroView;
	private JLabel tiempo;
	private Pregunta pregunta;
	private PanelJugadores panelJugadores;
	

	public Game(Lobby _lobby ) {	
		
		setLayout(new BorderLayout(0, 0));
		
		panelCentral = new JPanel();
		panelCentral.setLayout( new BorderLayout(0, 0) );
		panelCentral.setBorder(null);
		add(panelCentral);
		
		panelAbsoluto = new JPanel();
		panelAbsoluto.setLayout( new BorderLayout(0, 0) );
		panelAbsoluto.setBorder(null);
		panelCentral.add(panelAbsoluto, BorderLayout.CENTER);
		
		tableroView = new TableroView();
		panelAbsoluto.add(tableroView, BorderLayout.CENTER);
		
		JPanel Botones = new JPanelImage(Assets.fondos[0]);
		panelCentral.add(Botones, BorderLayout.SOUTH);
		Botones.setLayout(new BorderLayout(0, 0));
		
		JPanel BotonesBox = new JPanel(); 
		BotonesBox.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		BotonesBox.setOpaque(false);
		Botones.add(BotonesBox, BorderLayout.EAST);
		
		JButton btnStart = new BotonImagen(" Start ", Assets.ButtonV[0], Assets.ButtonV[1]);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start();
				btnStart.setVisible(false);
			}
		});
		
		JButton btnClose = new BotonImagen(" Leave ", Assets.ButtonC[0], Assets.ButtonC[1]);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});
		
		BotonesBox.add(btnClose);
		
		tiempo = new JLabel("Tiempo: ?");
		tiempo.setFont(new Font("Dialog", Font.PLAIN, 18));
		tiempo.setForeground(Color.white);
		Botones.add(tiempo, BorderLayout.CENTER);
		
		if ( Info.getGameServer() != null ) {
			btnClose.setText("Close");
			BotonesBox.add(btnStart);
		}
		
		JPanel Chatlayout = new JPanel();
		add(Chatlayout, BorderLayout.EAST);
		Chatlayout.setLayout(new BorderLayout(0, 0));
		
		JPanel Input = new JPanel();
		Chatlayout.add(Input, BorderLayout.SOUTH);
		
		Mensaje = new JTextField();
		Mensaje.setColumns(10);
		Input.add(Mensaje);
		
		JButton btnEnviar = new JButton(">");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String msg = Mensaje.getText();
				if ( msg != "") {
					Info.getGameClient().chat(msg);
				}				
			}
		});
		Input.add(btnEnviar);
		
		JScrollPane ChatScroll = new JScrollPane();
		Chatlayout.add(ChatScroll, BorderLayout.CENTER);
		
		Chat = new JTextArea();
		Chat.setLineWrap(true);
		Chat.setTabSize(5);
		Chat.setText("");
		ChatScroll.setViewportView(Chat);		
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(new BorderLayout(0, 0));
		add(panel_1, BorderLayout.WEST);
		
		JTextPane lblcarrera = new JTextPane();
		lblcarrera.setText("Jugadores");
		lblcarrera.setEditable(false);
		lblcarrera.setOpaque(false);
		lblcarrera.setMargin( new Insets( 20, 10, 10, 20 ) );
		panel_1.add(lblcarrera, BorderLayout.SOUTH);
		
		panelJugadores = new PanelJugadores( Assets.Cinta );
		panel_1.add(panelJugadores, BorderLayout.CENTER);
		
		UpdatePregunta(new Pregunta("Esperando al jugador para empezar el turno", new String[] {"", "", "", ""}, 0) );
	}
	
	public TableroView getTableroView() {
		return tableroView;
	}

	public void AddText(String msg) {
		Chat.append(msg + "\n");		
	}
	
	public void Close() {
		
		Info.getLobbyClient().Close();
		
		if ( Info.getGameServer() != null ) {
			Info.getGameServer().Close();
			Info.setGameServer(null);
		}else {
			Info.getGameClient().Leave();
		}		
	}
	
	public void Start() {
		
		Info.getGameServer().StartGame();
		Info.getLobbyClient().Close();
		
	}
	
	public void setTablero( Tablero tablero ) {
		
		if ( pregunta == null ) {
			UpdatePregunta( tablero.getPregunta() );
		}
		
		if ( !pregunta.getPregunta().equals( tablero.getPregunta().getPregunta() ) ) {
			UpdatePregunta( tablero.getPregunta() );
		}
		
		panelJugadores.setJugadores( tablero.getPlayers() );
		panelJugadores.repaint();		
		
		tiempo.setText( "  Tiempo: " + tablero.getTiempo() / 60 + " " );
	}
	
	public void UpdatePregunta( Pregunta pregunta ) {
		tableroView.setPregunta(pregunta);
		tableroView.UpdateButons();
		tableroView.updateUI();
		
		this.pregunta = pregunta;
		
	}
	
	public void setPodio(Podio podio) {
		
		System.out.println( podio.toString() );
		
		PodioView podioView = new PodioView(podio);
		panelAbsoluto.removeAll();
		panelAbsoluto.add(podioView, BorderLayout.CENTER);
		panelAbsoluto.updateUI();		
	}

}
