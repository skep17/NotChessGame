package pack.LoginSystem;

import java.util.HashMap;
import java.util.Map;

import pack.SQLPackage.SqlQueries;

public class AccountManager {
	
	private Map<String, String> credentialsMap;
	private String _lastAdded;  // will be deleted after testing
	
	
	public AccountManager() {
		credentialsMap = new HashMap<String, String>();
		credentialsMap.put("Patrick", "1234");
		credentialsMap.put("Molly", "FloPup");
	}
	
	public boolean accountExsits(String username) {
		return  credentialsMap.keySet().contains(username);
	}
	
	public boolean loginPermitted(String username, String password) {
		if(accountExsits(username))
			return credentialsMap.get(username).equals(password);
		return false;
	}
	
	public boolean createAccount(String username, String password) {
		Cracker cracker = new Cracker();
		String hashedUserame =  cracker.translateToSHA(username);
		String hashedPassword =  cracker.translateToSHA(password);
	
		if(accountExsits(username)) return false;
		_lastAdded = hashedUserame;
		credentialsMap.put(hashedUserame, hashedUserame);
		
		return true;
	}	
	
	// needs to be polished after registering is done
	// as well as cracker needs some modification so that everything is neet :)
	public String getLastAddedPerson() {
		return  _lastAdded;
	}
	
}