/**
 * Klass som definierar en lista med kunder.
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.util.ArrayList;
import java.util.List;

public class BankLogic extends Customer {

  private List<Customer> bankCustomer = new ArrayList<>();

  /**
   * Rutin som byter namnet på en kund med pNo
   *
   * @param name
   * @param surname
   * @param pNo
   * @return om bytet är utfört.
   */
  public boolean changeCustomerName(String name, String surname, String pNo) {
    boolean result = false;
    if (!((name.isEmpty()) && (surname.isEmpty()))) {
      for (Customer customer : bankCustomer) {
        if (customer.getPersonalNumber().equals(pNo)) {
          result = customer.changeCustomerName(name, surname);
          break;
        }
      }
    }
    return result;
  }

  /**
   * Rutin för att stänga ett konto för en kund
   *
   * @param pNo
   * @param accountId
   * @return sträng "kontonr belopp kontotyp ränta"
   */
  public String closeAccount(String pNo, int accountId) {
    String result = null;
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(pNo)) {
        for (Account acc : customer.getAccounts()) {
          if (acc.getAccountNumber() == accountId) {
            result = acc.toString(false) + " " + acc.calculateInterest();
            customer.getAccounts().remove(acc);
            break;
          }
        }
      }
    }
    return result;
  }

  /**
   * Rutin för att skapa en ny kund
   *
   * @param name
   * @param surname
   * @param pNo
   * @return om kund är ny
   */
  public boolean createCustomer(String name, String surname, String pNo) {
    boolean result = true;
    // Kontroll att kunden inte finns redan.
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(pNo)) {
        result = false;
        break;
      }
    }
    if (result) {
      // Ny kund till listan
      bankCustomer.add(new Customer(name, surname, pNo));
    }
    return result;
  }

  /**
   * Skapar ett konto för person pNo
   *
   * @param pNo
   * @return
   */
  public int createSavingsAccount(String pNo) {
    int result = -1;
    for (Customer customer : bankCustomer) {
      // Leta reda på kund med pNo
      if (customer.getPersonalNumber().equals(pNo)) {
        if (customer.getAccounts() == null) {
          customer.setAccounts();
        }
        Account newAccount = new Account(0, 2.4, true);
        customer.getAccounts().add(newAccount);
        result = newAccount.getAccountNumber();
        break;
      }
    }
    return result;
  }

  /**
   * Rutin som tar bort en kund och dess konton
   *
   * @param pNo
   * @return
   */
  public List<String> deleteCustomer(String pNo) {
    List<String> result = null;
    for (Customer customer : bankCustomer) {
      // Leta reda på kunden
      if (customer.getPersonalNumber().equals(pNo)) {
        // Skapa en ny lista med kundens data och konton
        ArrayList<String> deList = new ArrayList<>();
        deList.add(customer.toString());
        if (customer.getAccounts() != null) {
          for (Account acc : customer.getAccounts()) {
            deList.add(acc.toString(false) + " " + acc.calculateInterest());
          }
          // Ta bort alla konton och radera kunden från listan
          while (!customer.getAccounts().isEmpty()) {
            customer.getAccounts().removeLast();
          }
        }
        bankCustomer.remove(customer);
        result = deList;
        break;
      }
    }
    return result;
  }

  /**
   * Gör en insättning på konto med kontonummer som tillhör kunden med personnr
   *
   * @param pNo
   * @param accountId
   * @param amount
   * @return
   */
  public boolean deposit(String pNo, int accountId, int amount) {
    boolean result = false;
    if (amount > 0) {
      for (Customer customer : bankCustomer) {
        // Leta reda på kund med pNo
        if (customer.getPersonalNumber().equals(pNo)) {
          for (Account acc : customer.getAccounts()) {
            // Leta reda på konto med accountId
            if (acc.getAccountNumber() == accountId) {
              result = acc.deposit(amount);
              break;
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Rutin som returnerar en String som innehåller "kontonr saldo typ ränta"
   *
   * @param pNo
   * @param accountId
   * @return om accountid = kundens konto
   */
  public String getAccount(String pNo, int accountId) {
    String result = null;
    for (Customer customer : bankCustomer) {
      // Leta reda på kund med pNo
      if (customer.getPersonalNumber().equals(pNo)) {
        for (Account acc : customer.getAccounts()) {
          // Leta reda på konto med accountId
          if (acc.getAccountNumber() == accountId) {
            result = acc.toString();
            break;
          }
        }
      }
    }
    return result;
  }

  /**
   * Rutin som returnerar en lista med strängar som innehåller alla kunder
   *
   * @return , finns inga kunder blir den tom lista []
   */
  public List<String> getAllCustomers() {
    List<String> customerNames = new ArrayList<>();
    for (Customer customer : bankCustomer) {
      customerNames.add(customer.toString());
    }
    return customerNames;
  }

  /**
   * Rutin som tar fram en kunds information och denns konton.
   *
   * @param pNo
   * @return listan enligt beskrivning.
   */
  public List<String> getCustomer(String pNo) {
    List<String> customerInfo = null;
    for (Customer customer : bankCustomer) {
      // Leta reda på kund med pNo
      if (customer.getPersonalNumber().equals(pNo)) {
        ArrayList<String> getList = new ArrayList<>();
        getList.add(customer.toString());
        if (customer.getAccounts() != null) {
          for (Account acc : customer.getAccounts()) {
            getList.add(acc.toString());
          }
        }
        customerInfo = getList;
        break;
      }
    }
    return customerInfo;
  }

  /**
   * Gör ett uttag på kontot för en kund.
   *
   * @param pNo
   * @param accountId
   * @param amount
   * @return
   */
  public boolean withdraw(String pNo, int accountId, int amount) {
    boolean result = false;
    if (amount > 0) {
      for (Customer customer : bankCustomer) {
        // Leta reda på kund med pNo
        if (customer.getPersonalNumber().equals(pNo)) {
          for (Account acc : customer.getAccounts()) {
            // Leta reda på konto med accountId
            if (acc.getAccountNumber() == accountId) {
              result = acc.withdraw(amount);
              break;
            }
          }
        }
      }
    }
    return result;
  }
}