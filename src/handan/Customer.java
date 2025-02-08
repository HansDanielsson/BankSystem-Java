/**
 * Klass som definierar en kund.
 * @author Hans Danielsson, handan-2
 */
package handan;

public class Customer extends Account {
	String customerName;
	String customerSurname;
	String personalNumber;

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
	}

	public boolean changeCustomerName(String inName, String inSurename, String inPersonalNumber) {
		// Kontrollera om personnumret matchar det nuvarande personnumret.
		if (personalNumber.equals(inPersonalNumber)) {
			customerName = inName;
			customerSurname = inSurename;
			return true;
		} else {
			return false;
		}
	}

	public boolean testAccount() {
		Account account = new Account();
		return true;
	}
}
