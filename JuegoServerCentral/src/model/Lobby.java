package model;

import java.util.ArrayList;

public class Lobby {

	String host;	
	ArrayList<Client> jugadores;
	
	public Lobby(String host) {
		this.host = host;
		jugadores = new ArrayList<>();
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public ArrayList<Client> getJugadores() {
		return jugadores;
	}
	public void AddJugadores(Client client) {
		if ( !jugadores.contains(client) ) {
			jugadores.add(client);
		}
	}
	public void RemoveJugadors(Client client) {
		jugadores.remove(client);
	}
	@Override
	public String toString() {
		return "Lobby [host=" + host + ", jugadores=" + jugadores + "]";
	}
	
	
	
	
}
