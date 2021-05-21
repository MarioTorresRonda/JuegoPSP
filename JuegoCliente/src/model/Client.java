package model;

import java.net.Socket;

public class Client {

	String nick;
	String adress;
	boolean host;
	
	transient Socket socket;
	transient Lobby Lobby;

	public Client(Socket socket, String nick) {
		
		this.socket = socket;
		this.nick = nick;
		this.host = false;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Lobby getLobby() {
		return Lobby;
	}

	public void setLobby(Lobby lobby) {
		Lobby = lobby;
	}
	
	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@Override
	public String toString() {
		return "Client [socket=" + socket + ", nick=" + nick + "]";
	}
	
	
}
