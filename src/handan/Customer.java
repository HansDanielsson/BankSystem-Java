/**
 * Klass som definierar en kund.
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.util.ArrayList;

public class Customer extends Account {
	private String customerName;
	private String customerSurname;
	private String personalNumber;
	private ArrayList<Account> Accounts;

	// Konstruktor för en ny kund
	public Customer() {
		this("Unknown", "Unknown", "Unknown");
	}

	public Customer(String inCustomerName) {
		this(inCustomerName, "Unknown", "Unknown");
	}

	public Customer(String inCustomerName, String inCustomerSurname) {
		this(inCustomerName, inCustomerSurname, "Unknown");
	}

	// Används när en ny kund skapas med namn, efternamn och personnummer.
	public Customer(String inCustomerName, String inCustomerSurname, String inPersonalNumber) {
		customerName = inCustomerName;
		customerSurname = inCustomerSurname;
		personalNumber = inPersonalNumber;
		Accounts = new ArrayList<>();
	}
	
	public String getPersonalNumber() {
		return personalNumber;
	}

	/**
	 * Ändrar på Customer med givet inPersonalNumber
	 * Endast tillåtet att ändra på sin egen post.
	 * @param inName
	 * @param inSureName
	 * @param inPersonalNumber
	 * @return om fälten för Customer posten har ändrats
	 */
	public boolean changeCustomerName(String inName, String inSureName, String inPersonalNumber) {
		boolean result = false;
		// Kontrollera om personnumret matchar det nuvarande personnumret.
		// If-satsen kan vara onödig
		if (personalNumber.equals(inPersonalNumber)) {
			if (!inName.isEmpty()) {
				customerName = inName;
				result = true;
			}
			if (!inSureName.isEmpty()) {
				customerSurname = inSureName;
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return personalNumber + " " + customerName + " " + customerSurname;
	}
}
