package model;

public class Player {

	String nick;
	String adress;
	boolean vote;
	
	public boolean canVote() {
		return vote;
	}
	public void setVote(boolean vote) {
		this.vote = vote;
	}
	
	int puntos;
	
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public void addPuntos(int _puntos) {
		puntos += _puntos;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public Player(String nick, String adress) {
		super();
		this.nick = nick;
		this.adress = adress;
	}
	@Override
	public String toString() {
		return nick + " Puntos: " + puntos;
	}
	
	
	
	
}
