/**
 * Klass som definierar en lista med kunder.
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.util.List;

public class BankLogic extends Customer {

	public List<String> getAllCustomers() {

	}

	public boolean createCustomer(String name, String surname, String pNo) {
		return false;
	}

	public List<String> getCustomer(String pNo) {

	}

	public int createSavingsAccount(String pNo) {
		return 0;
	}

	public String getAccount(String pNo, int accountId) {
		return "hej";
	}

	public boolean deposit(String pNo, int accountId, double amount) {
		return false;
	}

	public boolean withdraw(String pNo, int accountId, int amount) {
		return false;
	}

	public String closeAccount(String pNo, int accountId) {
		return "hej";
	}

	public List<String> deleteCustomer(String pNo) {
		return null;
	}

}