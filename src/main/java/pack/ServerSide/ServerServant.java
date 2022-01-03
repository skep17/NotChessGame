package pack.ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;







public class ServerServant extends Thread {
	private final Socket clientSideSoket;
	private final ServerSide serverSide;
	private String login = null;
	private OutputStream outputStream;
	
	// assign socket on which this servant is attached
	public ServerServant(ServerSide serverSide,Socket clientSideSoket ) {
		this.clientSideSoket = clientSideSoket; 
		this.serverSide = serverSide;
	}
	// thread run method that continuously grants wishes of client until connection is closed
	@Override
	public void run() {
		try {
			System.out.print(isAlive());
			requestExcecutor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// request executor method that is in direct contact with client side
	
	private void requestExcecutor() throws IOException {
		InputStream inputStream = clientSideSoket.getInputStream();
		this.outputStream = clientSideSoket.getOutputStream();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		
		outputStream.write("Welcome to NHK\n".getBytes());
		while((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line);
			ArrayList<String> tokens =  new ArrayList<String>();
			while(tokenizer.hasMoreElements()) 
				tokens.add(tokenizer.nextToken());
			
			if(tokens.size() > 0) {
				
				String cmd = tokens.get(0);

				if("quit".equals(cmd)) {
					break;
				} else if ("login".equals(cmd)) {
					handleLogin(outputStream,tokens);
				} else {
					String msg = "unknown" + cmd + '\n';
					outputStream.write(msg.getBytes());
				}
			}
		}		
		
		clientSideSoket.close();
	}
	
	
	
	public void innerServerServantsMessageExchange(String msgString) throws IOException {
		outputStream.write(msgString.getBytes());
	}
	
	
	
	private void handleLogin(OutputStream outputStream,ArrayList<String> tokens) throws IOException {
		if (tokens.size() == 3) {
			String login = tokens.get(1);
			String password = tokens.get(2);
			
			
			if((login.equals("guest")&&password.equals("guest")) ||
					(login.equals("jim")&&password.equals("jim")) ) {
				this.login = login;
				String msg = "ok log in\n";
				msg += "you are " + login + "\n";
				outputStream.write(msg.getBytes());
				
				Set<ServerServant> allServerServants = serverSide.getAllServerServants();
				String onlineMessage = login + "is online now\n";
				allServerServants.forEach( x -> 
					{
						try {
							x.innerServerServantsMessageExchange(onlineMessage);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				
				
			} else {
				String msg = "error login\n";
				outputStream.write(msg.getBytes());
			}
		}
	}
	
	private class ServerBoradcaster {
		
		private final ServerSide serverSide;
		private final ServerServant servant;

		public ServerBoradcaster(ServerSide serverSide, ServerServant servant ) {
			this.serverSide = serverSide;
			this.servant = servant;
		}
		
		public void broadCastAll(String message) {
			serverSide.getAllServerServants().forEach(x ->
			{
				try {
					x.innerServerServantsMessageExchange(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} );
		}
		
		public void broadCastAllButMe(String message) {
			serverSide.getAllServerServants().stream()
			.filter(x -> !x.equals(this.servant)).forEach(x -> {
				try {
					x.innerServerServantsMessageExchange(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		
		public void getAllOnlineUsers() {
			//serverSide.getAllServerServants().stream().map( )
		}
	
	}

}
