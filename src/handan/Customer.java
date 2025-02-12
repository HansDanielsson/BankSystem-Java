/**
 * Klass som definierar en kund.
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.util.List;

public class Customer extends Account {
  private String customerName;
  private String customerSurname;
  private String personalNumber;
  public List<Account> accounts;

  // Konstruktor för en ny kund
  public Customer() {
    this("Unknown", "Unknown", "Unknown");
  }

  // Används när en ny kund skapas med namn, efternamn och personnummer.
  public Customer(String inCustomerName, String inCustomerSurname, String inPersonalNumber) {
    customerName = inCustomerName;
    customerSurname = inCustomerSurname;
    personalNumber = inPersonalNumber;
    accounts = null;
  }

  /**
   * Ändrar på kunden med givet inPersonalNumber Endast tillåtet att ändra på sin
   * egen post. Kontroll har redan utförts.
   *
   * @param inName
   * @param inSureName
   * @param inPersonalNumber
   * @return om variablerna har ändrats
   */
  public boolean changeCustomerName(String inName, String inSureName, String inPersonalNumber) {
    boolean result = false;
    // Byter endast om det är någon information
    if (!inName.isEmpty()) {
      customerName = inName;
      result = true;
    }
    if (!inSureName.isEmpty()) {
      customerSurname = inSureName;
      result = true;
    }
    return result;
  }

  /**
   * Hämtar personnummer
   *
   * @return personalNumber
   */
  public String getPersonalNumber() {
    return personalNumber;
  }

  @Override
  public String toString() {
    return personalNumber + " " + customerName + " " + customerSurname;
  }
}
