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
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import model.Player;
import model.Podio;
import model.Tablero;
import utils.Info;
import views.MainWindow;

public class GameClient extends Thread {

	private static final int PORTSERVER = 6060;
	private static final int PORTCLIENT = 6969;
	private DatagramSocket socketUDP=null;
	private byte[] enviados;	
	private byte[] recibidos;
	private DatagramPacket dprecibo;
	private DatagramPacket dpenvio;
	private Player jugador;
	private InetAddress direccion;
	private Thread hilo;
	private String ip;
	private MainWindow main;
	
	private boolean fin = true;
	
	Gson gson;
	
	public GameClient(MainWindow _main, Player _jugador, String _ip ) {
		
		ip = _ip;
		jugador = _jugador;
		main = _main;
		
		gson = new Gson();
		
		try {
			direccion = InetAddress.getByName(ip);
			socketUDP = new DatagramSocket(PORTCLIENT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		String player = gson.toJson(jugador);
		
		enviar("/join " + player);
		
		hilo = new Thread(this);
		hilo.start();
		
		System.out.println("abriendo cliente con: " + ip );
		
	}
	
	@Override
	public void run() {
		while (fin) {
			recibidos = new byte[1024];
			dprecibo= new DatagramPacket(recibidos, recibidos.length);
			try {
				socketUDP.receive(dprecibo);
				ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bais));
				String msg = (String) ois.readObject();
				bais.close();
				ois.close();
				if (msg.startsWith("/chat ")) {
					Info.getGamePanel().AddText( msg.substring(6) );
				}else if (msg.startsWith("/close")) {
					Close();
				}else if ( msg.startsWith("/game ")) {
					String tab = msg.substring(6);
					Tablero tablero = gson.fromJson(tab, Tablero.class);
					Info.getGamePanel().setTablero(tablero);
				}else if ( msg.startsWith("/podio ")) {
					String pod = msg.substring(7);
					Podio podio = gson.fromJson(pod, Podio.class);
					System.out.println("hey");
					Info.getGamePanel().setPodio(podio);
				}
			} catch (IOException e) {
				if ( fin ) {
					e.printStackTrace();
				}				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println("cerrando cliente de: " + ip );
	}
	
	
	public void enviar(String string) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(string);
			enviados = baos.toByteArray();
			dpenvio= new DatagramPacket(enviados, enviados.length, direccion, PORTSERVER);
			socketUDP.send(dpenvio);
			baos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void chat(String msg) {
		enviar("/chat " + jugador + ": "+ msg);
	}
	
	public void choose(int boton) {
		enviar("/choose " + boton);
		
	}
	
	public void Close() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		fin = false;
		try {
			socketUDP.close();
		}catch (Exception e) {
		}
		main.toMain();
	}
	
	public void Leave() {
		System.out.println("nos vamos");
		enviar("/close");
		Close();
	}
	
}
