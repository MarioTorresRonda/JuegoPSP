package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Client;
import model.Hub;
import model.Lobby;

public class ServerMain extends Thread {
	
	private ServerSocket serverSocket; 
	private Socket socket;
	private BufferedReader brForJoin;
	private static final int PORTSERVER = 6000;
	
	private List<Client> clients = new ArrayList<Client>();
	private Hub hub = new Hub();
	
	Gson gson;
	
	public ServerMain() {
		try {
			serverSocket = new ServerSocket(PORTSERVER);			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		gson = new Gson();
		
		setName("Server");
		start();	
	}
	
	@Override
	public void run() {
		
		System.out.println("Servidor central iniciado");
		
		while ( true ) {
			try {
				socket = serverSocket.accept();
				
				brForJoin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String nick = brForJoin.readLine();
				
				Client clientConnected = new Client(socket, nick);
				clientConnected.setAdress( socket.getInetAddress().getHostAddress() );
				clients.add(clientConnected);
				ServerClient serverClient= new ServerClient(clientConnected);
				serverClient.start();

				System.out.println( "se ha unido el usuario " + nick + " con la direccion: " + clientConnected.getAdress() );
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("error 1");
				System.exit(1); // Problemas => a la calle
			}
		}
	}
	
	private class ServerClient extends Thread{
		
		private Client client;
		private BufferedReader br;
		private PrintWriter pw;

		public ServerClient(Client client) {
			super( client.getNick() );
			this.client = client;
			try {
				br = new BufferedReader(new InputStreamReader( client.getSocket().getInputStream() ));
				pw = new PrintWriter( client.getSocket().getOutputStream() );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String msg = null;
			// Estamos atentos a lo que nos mande el server,
			// con los mensajes del resto de clientes
			while ( !client.getSocket().isClosed() ) {
				try {
					msg = br.readLine();
					if (msg!=null) {
						if (msg.startsWith("/create")) {
							if ( !client.isHost() ) {
								Lobby lobby = new Lobby( client.getAdress() );
								hub.getLobbys().add(lobby);
								lobby.AddJugadores(client);
								client.setHost(true);
								client.setLobby(lobby);
								send( gson.toJson( lobby ) );
							}else {
								send( "error" );
							}

						}else if (msg.startsWith("/join ")) {
							
							String host = 
									msg.substring( 
											msg.indexOf("?") + 1, 
											msg.substring( msg.indexOf("?") + 1, msg.length() ).indexOf(" ") + msg.indexOf("?") + 1 
									);
							
							Lobby lobby = hub.find(host);
							if ( lobby != null) {
								lobby.AddJugadores(client);
								System.out.println(client.getNick() + " joined " + lobby.getHost()  );
								client.setLobby( lobby );
								send( client.getLobby().getHost() );
							}else {
								send("error");
							}
							
							
						}else if(msg.startsWith("/close") ) {
							if ( client.isHost() ) {
								close( client.getLobby() );
								send( "1" );
							}else if (client.getLobby() != null){
								client.getLobby().RemoveJugadors(client);
								send( "2" );
							}else {
								send("error");
							}
						}else if (msg.startsWith("/list" )) {
							String lobbysJson = gson.toJson( hub );
							send( lobbysJson );
						}
							
					}
				} catch (IOException e) {
					try {
						br.close();
						pw.close();	
						if ( client.isHost() ) {
							close( client.getLobby() );
						}
						if ( client.getLobby() != null)  {
							client.getLobby().RemoveJugadors(client);
						}
						clients.remove(client);
						client.getSocket().close();
						System.out.println("se ha ido " + client.getNick() );
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			}
		}
		
		public void send( String _cmd ) {
			
			pw.print(_cmd + "\n");
			pw.flush();		
		}
		
		public void close( Lobby lobby ) {
			
			for( Client client : lobby.getJugadores() ) {
				client.setLobby(null);								
			}
			
			hub.getLobbys().remove( lobby );
			
			client.setHost(false);
		}
		
	}	
}



