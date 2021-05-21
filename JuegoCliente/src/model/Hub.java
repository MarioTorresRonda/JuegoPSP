package model;

import java.util.ArrayList;
import java.util.List;

public class Hub {

	List<Lobby> lobbys;

	public Hub() {
		lobbys = new ArrayList<Lobby>();
	}
	
	public List<Lobby> getLobbys() {
		return lobbys;
	}

	public void setLobbys(List<Lobby> lobbys) {
		this.lobbys = lobbys;
	}
	
	public Lobby find(String host) {
		for( Lobby lobby : lobbys) {
			if (lobby.getHost().equals(host) ) {
				return lobby;
			}
		}
		
		return null;
	}
	
}
