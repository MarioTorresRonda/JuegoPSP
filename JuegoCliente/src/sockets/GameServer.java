package sockets;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JTextArea;

import com.google.gson.Gson;

import model.Client;
import model.Hub;
import model.Player;
import model.Tablero;
import views.MainWindow;
import views.TableroView;

public class GameServer extends Thread {

	
	private static final int PORTSERVER = 6060;
	private static final int PORTCLIENT = 6969;
	private DatagramSocket socketUDP=null;
	private byte[] enviados;	
	private byte[] recibidos;
	private DatagramPacket dprecibo;
	private DatagramPacket dpenvio;
	private ArrayList<Player> players;
	private Thread hilo;
	private boolean fin = true;
	private MainWindow main;
	
	private Game game;
	
	private boolean GameStarted;
	
	Gson gson;
	
	public GameServer( MainWindow _main ) {
		System.out.println("Iniciando GameServer");
		main = _main;
		
		players = new ArrayList<Player>();
		try {
			socketUDP = new DatagramSocket(PORTSERVER);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		gson = new Gson();
		
		hilo = new Thread(this);
		hilo.setName("Server");
		hilo.start();
		
	}
	
	@Override
	public void run() {
		try {
			while (fin) {
				recibidos = new byte[1024];
				// Recibimos del Cliente
				dprecibo= new DatagramPacket(recibidos, recibidos.length);
				socketUDP.receive(dprecibo);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(recibidos);
				ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
				String cmd = (String) is.readObject();
				is.close();
				if ( cmd.startsWith("/choose ") ) {
					game.Choose( Integer.parseInt( cmd.substring(8) ), dprecibo.getAddress().getHostAddress() );
				}
				if ( cmd.startsWith("/join") ) {
					Player player = new Player( cmd.substring(5), dprecibo.getAddress().getHostAddress() );	
					addPlayer( player );
					enviarall("/chat Usuario " + player.getNick() + " se ha unido al servidor");
				}else if( cmd.startsWith("/chat") ) {
					enviarall(cmd);
				}else if( cmd.startsWith("/close") ) {
					for(Player player : players) {
						if ( player.getAdress().equals(dprecibo.getAddress().getHostAddress() )) {
							removePlayer(player);
							enviarall("/chat Usuario " + player.getNick() + " se ha ido del servidor");
							break;
						}
					}
				}
				
			}
		}catch (IOException | ClassNotFoundException e) {
			if ( fin ) {
				e.printStackTrace();
			}	
		}
		socketUDP.close();		
	}
	
	private void enviarall(String cmd) {
		// Enviamos a todos los Clientes
		for (Player player : players) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				InetAddress inet = InetAddress.getByName( player.getAdress() );
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(cmd);
				enviados = baos.toByteArray();
				dpenvio= new DatagramPacket(enviados, enviados.length, inet, PORTCLIENT);
				socketUDP.send(dpenvio);
				oos.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addPlayer(Player _player) {
		if ( !players.contains(_player) ) {
			players.add(_player);
		}
	}
	
	public void removePlayer(Player _player) {
		if ( players.contains(_player) ) {
			players.remove(_player);
		}		
	}
	
	public void Close() {
		
		enviarall("/close");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		fin = false;
		game.active = false;
		try {
			socketUDP.close();
		}catch (Exception e) {
		}
	}
	

	
	public void StartGame() {
		game = new Game();
	}

	private class Game{
		
		boolean active = true;	
		boolean started = false;
		int stage1;
		int stage2;
		int stage3;
		int stage4;
		Tablero tablero;
		
		public Game() {
			GameLoop loop = new GameLoop();
			tablero = new Tablero(players);
		}
		
		public void Choose(int boton, String adress) {
			Player player = tablero.find( adress );
			if ( player.canVote() ) {
				if ( tablero.getPregunta().getRespuestaCorrecta() == boton ) {
					player.addPuntos( tablero.getTiempo() );
				}
				player.setVote(false);
			}
			
		}
		
		public void Update() {
			
			if ( !started ) {
				
				if (stage1 == 0) {
					enviarall("/chat El host va a comenzar el juego...");
					stage1++;
					return;
				}
				
				if (stage1 < 60) {
					stage1++;	
					return;
				}
				
				if (stage2 == 0) {
					enviarall("/chat El juego comenzara en 3...");
					stage2++;
					return;
				}			
				
				if (stage2 < 60) {
					stage2++;
					return;
				}
				
				if (stage3 == 0) {
					enviarall("/chat El juego comenzara en 2...");
					stage3++;
					return;
				}			
				
				if (stage3 < 60) {
					stage3++;	
					return;
				}
				
				if (stage4 == 0) {
					enviarall("/chat El juego comenzara en 1...");
					stage4++;
					return;
				}				
				
				if (stage4 < 60) {
					stage4++;
					return;
				}
				
				if ( stage4 == 60) {
					enviarall("/chat El juego comienza");
					started = true;
					return;
					
				}
			}			
			
			if ( tablero.getTiempo() > 0 && tablero.canVote()) {
				
				tablero.decreaseTiempo(1);
				enviarall( "/game " + gson.toJson(tablero) );
			}else {
				
				for( Player player : tablero.getPlayers() ) {
					player.setVote(true);
				}
				
				tablero.setPregunta( tablero.getPreguntas().get( new Random().nextInt(tablero.getPreguntas().size())));
				tablero.setTiempo(60 * 30);
				enviarall("/pregunta " + gson.toJson( tablero.getPregunta() ) );
				enviarall("/chat Nueva pregunta!");		
			}			
		}
		
		private class GameLoop extends Thread {
			
			Thread hiloLoop;
			public final int FPS = 60;
			
			public GameLoop() {
				
				hiloLoop = new Thread(this);
				hiloLoop.setName("Server");
				hiloLoop.start();
				
			}
			
			@Override
			public void run() {
				
				try {
					
					while( active ) {			
						
						long time = System.currentTimeMillis();
						
						Update();
						
						time = (1000 / FPS) - (System.currentTimeMillis() - time);
						
						if (time > 0) {
							try {
								Thread.sleep(time);
							} catch (Exception e) {
							}
						}else {
						}	
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}		
	}	
}


