package model;

public class Player {

	String nick;
	String adress;
	boolean vote;
	
	int puntos;
	
	//Necesario para dibujarlo
	int character;
	int state;
	int posy = 440;
	int lastposy = 540;
	
	boolean idle;
	int spritepos;
	
	int spritestates = 3;
	
	
	public Player(String nick, int character) {
		super();
		this.nick = nick;
		this.character = character;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public int getLastposy() {
		return lastposy;
	}

	public void setLastposy(int lastposy) {
		this.lastposy = lastposy;
	}

	public boolean isIdle() {
		return idle;
	}

	public void setIdle(boolean idle) {
		this.idle = idle;
	}

	public int getSpritepos() {
		return spritepos;
	}

	public void setSpritepos(int spritepos) {
		this.spritepos = spritepos;
	}
	
	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}
	
	public boolean canVote() {
		return vote;
	}
	public void setVote(boolean vote) {
		this.vote = vote;
	}
	
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
	@Override
	public String toString() {
		return nick + " Puntos: " + puntos;
	}
	
	public void update() {
		
		posy = 440 - (puntos / 30);
		
		if( posy < lastposy ) {
			
			state = 2;
			spritepos++;
			if ( spritepos >= spritestates ) {
				spritepos = 0;
			}
			lastposy--;
			
		}else {
			state = 0;
			spritepos = 0;
		}
	}
	
	
}
