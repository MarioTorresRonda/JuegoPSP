package views;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Lobby;
import sockets.GameClient;
import sockets.GameServer;
import sockets.LobbyClient;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LobbyClient client;
	private Game game;
	private GameClient clientGame;
	private GameServer serverGame;

	public LobbyClient getClient() {
		return client;
	}
	
	public GameClient getClientGame() {
		return clientGame;
	}

	public void setClientGame(GameClient clientGame) {
		this.clientGame = clientGame;
	}

	public GameServer getServerGame() {
		return serverGame;
	}

	public void setServerGame(GameServer serverGame) {
		this.serverGame = serverGame;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public static void main(String[] args) {
		try {
			MainWindow frame = new MainWindow();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		client = new LobbyClient(this, "prueba2", "192.168.1.20", 6000);
		
		toMain();
	}
	
	public void toGame(Lobby lobby) {
		
		Game game = new Game(this, lobby);
		this.game = game;
		UpdateWindow();
		contentPane.add(game, BorderLayout.CENTER);
		contentPane.updateUI();	
	}
	
	public void toMain() {
		
		Main main= new Main(this);
		UpdateWindow();
		contentPane.add(main, BorderLayout.CENTER);
		contentPane.updateUI();	
	}
	
	public void UpdateWindow() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
