package pack.ServerSide;

import java.io.IOException;

public class ServerStarter {

	// open socket on free port
	// this method starts thread that keeps socket alive
	public ServerStarter() throws IOException {
		int port = 7654;
		ServerSide serverSide = new ServerSide(port);
		serverSide.start();
	}
	// thread that continuously accepts connection requests
	// for each connection we start server worker thread that that serves client requests 
	
}
