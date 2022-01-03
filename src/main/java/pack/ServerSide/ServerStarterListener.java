package pack.ServerSide;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ServerStarterListener
 *
 */
public class ServerStarterListener implements ServletContextListener {

    
	@Override
    public void contextInitialized(ServletContextEvent sce)  {
    	ServletContext context = sce.getServletContext();
        try {
			context.setAttribute("serverStarter", new ServerStarter());
		} catch (IOException e) {
			e.printStackTrace();
		}
       
    }
	
}
