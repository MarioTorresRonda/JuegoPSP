package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Client;
import model.Hub;
import model.Lobby;
import model.Player;
import utils.Info;
import views.MainWindow;

public class LobbyClient extends Thread {

	MainWindow main;
	
	String nombre;
	String ip = "";
	
	
	InetSocketAddress direccion;
    Socket socket=new Socket();
    BufferedReader br = null;
    PrintWriter pw = null;
    private Thread hilo;
	
	Gson gson;
    
    public LobbyClient(MainWindow _main, String _nombre, String _ip, int _port) {

    	main = _main;
    	nombre = _nombre;
    	ip = _ip;
    	direccion = new InetSocketAddress(ip, _port);
    	
		gson = new Gson();
    	
		hilo = new Thread(this);
    	hilo.start();
    }
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		
		try {
			socket.connect(direccion);
			
			br = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			pw = new PrintWriter(socket.getOutputStream());
			
			send(nombre);	
			
			while ( true ) {
				try {
					LobbyClient.sleep(500);
				}catch (Exception e) {
					// TODO: handle exception
				}				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	


	public void send( String _cmd ) {
		
		pw.print(_cmd + "\n");
		pw.flush();		
	}
	
	public String read() {
		
		String msg = null;
		try {
			while( msg == null) {
				msg = br.readLine();
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	public Hub ListLobbys() {
		
		send("/list");
		Hub hub = gson.fromJson(read(), Hub.class );	
		return hub;
	}
	
	public String Join(Lobby lobby) {
		send("/join ?" + lobby.getHost() + " ");
		String ip = read();
		if ( ip != "error") {
			main.toGame( lobby );
			GameClient client = new GameClient(main, new Player( Info.getLobbyClient().getNombre(), Info.getSprite() ), ip );
			Info.setGameClient(client);
		}
		return "1";
	}
	
	public Lobby Create() {
		
		send("/create");
		Lobby lobby = gson.fromJson( read(), Lobby.class );
		if ( lobby != null) {
			GameServer server = new GameServer();
			Info.setGameServer(server);
			Join( lobby );
		}
		return lobby;
		
	}
	
	public void Close() {
		send("/close");
		
		System.out.println( read() );
	}
	
	public ArrayList<Client> Start(Lobby _lobby) {
		
		send("/list");
		
		Hub hub = gson.fromJson(read(), Hub.class );
		return hub.find( _lobby.getHost() ).getJugadores();
		
	}
	
	
}
