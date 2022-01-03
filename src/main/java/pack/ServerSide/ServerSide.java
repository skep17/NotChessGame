package pack.ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerSide extends Thread {
	
	private final int port;
	Map<String, Integer> loggedInUsers;
	Map<String, ArrayList<ServerServant>> userServantMap;
	Set<ServerServant> allServerServants;
	
	public ServerSide(int port) {
		this.port = port;
		loggedInUsers = new HashMap<String, Integer>();
		userServantMap = new HashMap<String, ArrayList<ServerServant>>();
		allServerServants = new HashSet<ServerServant>();
	}
	
	public Set<ServerServant> getAllServerServants(){
		return this.allServerServants;
	}
	
	@Override
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(this.port);
			while(true) {
					System.out.println("pending client side huh");
					Socket clientSocket = serverSocket.accept();
					System.out.println("into client side huh");
					ServerServant servant = new ServerServant(this,clientSocket);
					allServerServants.add(servant);
					servant.start();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
