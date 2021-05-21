package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Lobby;
import sockets.LobbyClient;
import utils.Assets;
import utils.Info;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		try {
			MainWindow frame = new MainWindow();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainWindow() {
		setResizable(false);
		
		Assets.loadAssets();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds( 0, 0, 800, 600 );
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(null);
		
		
		Info.setMain(this);
		Inicio inicio = new Inicio();
		contentPane.add(inicio, BorderLayout.CENTER);
		setContentPane( contentPane );
	}	
	
	public void toGame(Lobby lobby) {
		
		Game game = new Game(lobby);
		Info.setGamePanel(game);
		SetView( game );
	}
	
	public void toMain() {
		if ( Info.getMainPanel() == null)  {
			if ( Info.getLobbyClient() == null ) {
				Info.setLobbyClient(new LobbyClient(this, Info.getNombre() , "192.168.1.20", 6000));
			}
			Main main = new Main();
			Info.setMainPanel(main);
			SetView( main );
		}else {
			SetView( Info.getMainPanel() );
		}
	}
	
	public void SetView(JPanel view ) {
		contentPane.removeAll();
		contentPane.add(view, BorderLayout.CENTER);
		contentPane.updateUI();	
	}
}
