package pack.LoginSystem;

import junit.framework.TestCase;

public class AccountManagerTest  extends  TestCase  {
	
	public void testInitialConditions() {
		AccountManager manager  = new AccountManager();
		assertTrue(manager.accountExsits("Molly"));
		assertTrue(manager.accountExsits("Patrick"));
		assertFalse(manager.accountExsits("asdfasdfaserq341"));
	}
	
	public void testInitialLogin() {
		AccountManager manager  = new AccountManager();
		assertTrue(manager.loginPermitted("Molly","FloPup"));
		assertTrue(manager.loginPermitted("Patrick","1234"));
		assertFalse(manager.loginPermitted("Patrick","FloPup"));
		assertFalse(manager.loginPermitted("Molly","1234"));
		assertFalse(manager.loginPermitted("Molly","rwevczd"));
		assertFalse(manager.loginPermitted("Mollys","rwevczd"));
		assertFalse(manager.loginPermitted("Patrickwer","rwevczd"));
	}
	
	
	public void testCreateAccount() {
		AccountManager manager  = new AccountManager();
		
		assertFalse(manager.createAccount("Molly","FloPup"));
		assertFalse(manager.createAccount("Patrick","1234"));
		assertFalse(manager.createAccount("Patrick","1"));
		
		assertTrue(manager.createAccount("Julia","1"));
		assertFalse(manager.createAccount("Julia","1"));
		assertTrue(manager.loginPermitted("Julia","1"));
		assertTrue(manager.accountExsits("Julia"));
		
		assertTrue(manager.createAccount("mosq","1"));
		assertFalse(manager.createAccount("mosq","1"));
		assertTrue(manager.loginPermitted("mosq","1"));
		assertTrue(manager.accountExsits("mosq"));
		
	}
}