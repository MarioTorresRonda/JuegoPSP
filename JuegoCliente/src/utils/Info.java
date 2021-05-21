package utils;

import sockets.GameClient;
import sockets.GameServer;
import sockets.LobbyClient;
import views.Game;
import views.Main;
import views.MainWindow;

public class Info {

	//Views
	static MainWindow main;
	static Main mainPanel;
	static Game gamePanel;

	
	//Player
	static String Nombre;
	static int Sprite;
	
	//Sockets
	static LobbyClient lobbyClient;
	static GameClient gameClient;
	static GameServer gameServer;
	
	public static GameClient getGameClient() {
		return gameClient;
	}
	public static void setGameClient(GameClient gameClient) {
		Info.gameClient = gameClient;
	}
	public static GameServer getGameServer() {
		return gameServer;
	}
	public static void setGameServer(GameServer gameServer) {
		Info.gameServer = gameServer;
	}
	public static LobbyClient getLobbyClient() {
		return lobbyClient;
	}
	public static void setLobbyClient(LobbyClient lobbyClient) {
		Info.lobbyClient = lobbyClient;
	}
	public static String getNombre() {
		return Nombre;
	}
	public static void setNombre(String nombre) {
		Nombre = nombre;
	}
	public static int getSprite() {
		return Sprite;
	}
	public static void setSprite(int jugador) {
		Sprite = jugador;
	}
	public static MainWindow getMain() {
		return main;
	}
	public static void setMain(MainWindow main) {
		Info.main = main;
	}
	public static Main getMainPanel() {
		return mainPanel;
	}
	public static void setMainPanel(Main mainPanel) {
		Info.mainPanel = mainPanel;
	}
	public static Game getGamePanel() {
		return gamePanel;
	}
	public static void setGamePanel(Game gamePanel) {
		Info.gamePanel = gamePanel;
	}
	
	
	
}
