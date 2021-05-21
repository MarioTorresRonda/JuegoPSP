package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import model.Lobby;
import model.Player;
import model.Pregunta;
import model.Tablero;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;

public class Game extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField Mensaje;
	private JPanel tableroView;
	private JTextArea Chat;
	private MainWindow main;
	private Pregunta pregunta;
	private TableroView view;
	private JList list;
	private JLabel tiempo;
	

	public Game(MainWindow _main, Lobby _lobby ) {
		
		main = _main;
		pregunta = new Pregunta("", null, 0);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		tableroView = new JPanel();
		panel.add(tableroView, BorderLayout.CENTER);
		tableroView.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Esperando a que la partida comience...");
		tableroView.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel Botones = new JPanel();
		panel.add(Botones, BorderLayout.SOUTH);
		Botones.setLayout(new BorderLayout(0, 0));
		
		JPanel BotonesBox = new JPanel();
		Botones.add(BotonesBox, BorderLayout.EAST);
		BotonesBox.setLayout(new BoxLayout(BotonesBox, BoxLayout.X_AXIS));
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start();
			}
		});
		
		JButton btnClose = new JButton("Leave");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});
		
		BotonesBox.add(btnClose);
		
		tiempo = new JLabel("Tiempo: ?");
		Botones.add(tiempo, BorderLayout.CENTER);
		
		if ( main.getServerGame() != null ) {
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
					main.getClientGame().chat(msg);
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
		add(panel_1, BorderLayout.WEST);
		
		list = new JList();
		panel_1.add(list);
	}
	
	public void AddText(String msg) {
		Chat.append(msg + "\n");		
	}
	
	public void Close() {
		
		main.getClient().Close();
		
		if ( main.getServerGame() != null ) {
			main.getServerGame().Close();
		}else {
			main.getClientGame().Leave();
		}
		
		main.setServerGame(null);
	}
	
	public void Start() {
		
		main.getServerGame().StartGame();
		main.getClient().Close();
		
	}
	
	public void setTablero( Tablero tablero ) {
		
		//Preguntas
		view.ReSize();
		tableroView.updateUI();
		tiempo.setText( "Tiempo: " + tablero.getTiempo() / 60 + " " );
		list.setListData(  tablero.getPlayers().toArray() );
		
	}
	
	public void UpdatePregunta( Pregunta pregunta ) {
		tableroView.removeAll();
		pregunta = pregunta;
		view = new TableroView( main, pregunta  );
		tableroView.add(view, BorderLayout.CENTER );
		view.UpdateButons();
	}

}
