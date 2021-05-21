package model;

import java.util.ArrayList;

public class Tablero {
	
	Pregunta pregunta;
	ArrayList<Player> players;
	int tiempo;
	
	transient ArrayList<Pregunta> preguntas;
	
	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getTiempo() {
		return tiempo;
	}
	
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
	public void decreaseTiempo(int tiempo) {
		this.tiempo -= tiempo;
	}

	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}

	public Player find( String adress ) {
		for( Player player : players ) {
			if ( adress.equals( player.getAdress())) {
				return player;
			}
		}
		return null;
	}
	
	public Player[] winners() {
		Player[] podio = new Player[3];
		
		for(int i = 0; i < podio.length && i < players.size() ; i++) {
			for( Player player : players ) {
				if ( podio[i] == null) {
					podio[i] = player;
				}
				if ( podio[i].getPuntos() < player.getPuntos() ) {
					podio[i] = player;
				}
			}
		}
		return podio;
	}
	
	public boolean canVote() {
		for( Player player : players ) {
			if ( player.canVote() ) {
				return true;
			}
		}
		return false;
	}
	
	public Tablero(ArrayList<Player> _players) {		
		super();
		
		players = _players;
		
		preguntas = new ArrayList<>();
		preguntas.add( new Pregunta( "Serie protagonizada por un par de cient�ficos \n que comparten piso, y su vecina.", new String[] { "Bones", "Big-bang theory", "Cosmos", "Los cient�ficos locos" } , 1 ) );
		preguntas.add( new Pregunta( "�Cu�ntas pel�culas forman la saga de �Star Wars�?.", new String[] { "10", "11", "9", "12" } , 2 ) );
		preguntas.add( new Pregunta( "�Qui�nes eran los 3 mosqueteros?.", new String[] { "Dumas, Porthos y Aramis", "Athos, Asterix y Aramis", "Athos, Porthos, Aramis, D�Artagnan", "Athos, Porthos, D�Artagnan y Richelieu" } , 2 ) );
		preguntas.add( new Pregunta( "�C�mo se llama el t�o rico del pato Donald?", new String[] { "Cuchifrito", "Gilito", "T�o patito", "Menganito" } , 1 ) );
		preguntas.add( new Pregunta( "Un pez payaso y un pez cirujano son los \n protagonistas de esta peli.", new String[] { "El espanta tiburones", "Mar abierto", "Buscando a Nemo", "Me llamo Ralf" } , 2 ) );
		preguntas.add( new Pregunta( "Iron Man pertenece al universo cinematogr�fico de: ", new String[] { "Marvel", "Dc Comics", "Transformers", "Atresmedia" } , 0 ) );
		preguntas.add( new Pregunta( "�C�mo se llama el protagonista del se�or de los anillos?", new String[] { "Gollum", "Sauron", "Frodo", "Holstim" } , 2 ) );
		preguntas.add( new Pregunta( "�Cu�l es el t�tulo verdadero de esta famosa pel�cula?", new String[] { "La vida es turbia", "La vida es dura", "La vida es loca", "La vida es bella" } , 3 ) );
		preguntas.add( new Pregunta( "�C�mo se llama el protagonista de la famosa pel�cula \n de Tim Burton �Pesadilla antes de navidad�?", new String[] { "Jack Skelleton", "Jack Sparrow", "Jack Sparrago", "El conde de Montecristo" } , 0 ) );
		preguntas.add( new Pregunta( "Esta serie esta protagonizada por un grupo de j�venes detectives y su perro.", new String[] { "Smurfy Doo", "Kim Possible", "Scooby Doo", "El detective Conan" } , 0 ) );
		
		//Falta preguntas
		
		//preguntas.add( new Pregunta( "", new String[] { "", "", "", "" } , 0 ) );	
	}	
}
