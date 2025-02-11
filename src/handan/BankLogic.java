/**
 * Klass som definierar en lista med kunder.
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.util.ArrayList;
import java.util.List;

public class BankLogic extends Customer {

	private ArrayList<Customer> bankCustomer = new ArrayList<Customer>();

	public List<String> getAllCustomers() {

	}

	public boolean createCustomer(String name, String surname, String pNo) {
		boolean result = true;
		for (Customer customer : bankCustomer) {
			if (customer.getPersonalNumber().equals(pNo)) {
				result = false;
				break;
			}
		}

		if (result) {
			Customer newCustomer = new Customer(name, surname, pNo);
			bankCustomer.add(newCustomer);
		}
		return result;
	}

	public List<String> getCustomer(String pNo) {

	}

	public boolean changeCustomerName(String name, String surname, String pNo) {
		boolean result = false;
		if ((name.length() == 0) && (surname.length() == 0)) {
			for (Customer customer : bankCustomer) {
				if (customer.getPersonalNumber().equals(pNo)) {
					customer.changeCustomerName(name, surname, pNo);
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public int createSavingsAccount(String pNo) {
		return 0;
	}

	public String getAccount(String pNo, int accountId) {
		return "hej";
	}

	public boolean deposit(String pNo, int accountId, int amount) {
		boolean result = true;
		if (amount < 0) {
			result = false;
		} else {

		}
		return result;
	}

	public boolean withdraw(String pNo, int accountId, int amount) {
		boolean result = true;
		if (amount < 0 || amount > balance.intValue()) {
			result = false;
		} else {

		}
		return result;
	}

	public String closeAccount(String pNo, int accountId) {
		return "hej";
	}

	public List<String> deleteCustomer(String pNo) {
		return null;
	}

}