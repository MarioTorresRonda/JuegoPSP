package model;

public class Podio {

	Player[] players = new Player[3];
	
	public Podio( Player[] _players ) {
		players = _players;
	}

	public Player[] getPlayers() {
		return players;
	}	
}
