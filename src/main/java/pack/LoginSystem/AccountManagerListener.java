package pack.LoginSystem;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class AccountManagerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	ServletContext context = sce.getServletContext();
        context.setAttribute("accountManager", new AccountManager());       
    }
	
}