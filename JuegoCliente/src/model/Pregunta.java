package model;

public class Pregunta {

	String pregunta;
	String[] respuestas = new String[4];
	int respuestaCorrecta;
	
	public String getPregunta() {
		return pregunta;
	}
	public String[] getRespuestas() {
		return respuestas;
	}
	public int getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
	public Pregunta(String pregunta, String[] respuestas, int respuestaCorrecta) {
		super();
		this.pregunta = pregunta;
		this.respuestas = respuestas;
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	
	
	
	
}
