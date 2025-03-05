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
   * Rutin som byter namnet på en befintlig kund med pNo.
   *
   * @param name
   * @param surname
   * @param pNo
   * @return om bytet är utfört.
   */
  public boolean changeCustomerName(String name, String surname, String pNo) {
    boolean result = false;
    if (!((name.isEmpty()) && (surname.isEmpty()))) {
      Customer changeCustomer = getSearchCustomer(pNo);
      if (changeCustomer != null) {
        result = changeCustomer.changeCustomerName(name, surname);
      }
    }
    return result;
  }

  /**
   * Rutin för att stänga ett konto för en kund
   *
   * @param pNo
   * @param accountId
   * @return "kontonr belopp kontotyp ränta"
   */
  public String closeAccount(String pNo, int accountId) {
    String result = null;
    Customer closeCustomer = getSearchCustomer(pNo);
    if (closeCustomer != null) {
      Account acc = getSearchAccount(closeCustomer.getAccounts(), accountId);
      if (acc != null) {
        result = acc.infoAccount() + " " + acc.calculateInterest();
        closeCustomer.getAccounts().remove(acc);
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
    if (getSearchCustomer(pNo) != null) {
      result = false;
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
   * @return -1 = Hittar inte pNo, annars kontonummer
   */
  public int createSavingsAccount(String pNo) {
    int result = -1;
    Customer customer = getSearchCustomer(pNo);
    if (customer != null) {
      if (customer.getAccounts() == null) {
        customer.setAccounts();
      }
      Account newAccount = new Account("Sparkonto", 0, 2.4, true); // Här räknas kontonummer.
      customer.getAccounts().add(newAccount);
      result = newAccount.getAccountNumber();
    }
    return result;
  }

  /**
   * Rutin som tar bort en kund och dess konton Returnerar en oföränderlig lista
   * med resultat
   *
   * @param pNo
   * @return "pNr f-Namn E-namn, KontoNr Typ Saldo Kr,..."
   */
  public List<String> deleteCustomer(String pNo) {
    List<String> result = null;
    Customer customer = getSearchCustomer(pNo);
    if (customer != null) {
      // Skapa en ny lista med kundens data och konton
      ArrayList<String> deList = new ArrayList<>();
      deList.add(customer.toString());
      if (customer.getAccounts() != null) { // Kund har konton
        for (Account acc : customer.getAccounts()) {
          deList.add(acc.infoAccount() + " " + acc.calculateInterest());
        }
        // Ta bort alla konton och radera kunden från listan
        while (!customer.getAccounts().isEmpty()) {
          customer.getAccounts().removeLast();
        }
      }
      bankCustomer.remove(customer);
      result = List.copyOf(deList);
    }
    return result;
  }

  /**
   * Gör en insättning på konto med kontonummer som tillhör kunden med personnr
   *
   * @param pNo
   * @param accountId
   * @param amount
   * @return True om det gick bra
   */
  public boolean deposit(String pNo, int accountId, int amount) {
    boolean result = false;
    if (amount > 0) {
      Customer customer = getSearchCustomer(pNo);
      if (customer != null) {
        Account acc = getSearchAccount(customer.getAccounts(), accountId);
        if (acc != null) {
          result = acc.deposit(amount);
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
    Customer customer = getSearchCustomer(pNo);
    if (customer != null) {
      Account acc = getSearchAccount(customer.getAccounts(), accountId);
      if (acc != null) {
        result = acc.toString();
      }
    }
    return result;
  }

  /**
   * Rutin som returnerar en lista med strängar som innehåller alla kunder
   * Returnerar en oföränderlig lista med kunder
   *
   * @return , finns inga kunder blir den tom lista []
   */
  public List<String> getAllCustomers() {
    List<String> customerNames = new ArrayList<>();
    for (Customer customer : bankCustomer) {
      customerNames.add(customer.toString());
    }
    return List.copyOf(customerNames);
  }

  /**
   * Rutin som tar fram en kunds information och denns konton. Returnerar en
   * oföränderlig lista med resultat
   *
   * @param pNo
   * @return lista på poster.
   */
  public List<String> getCustomer(String pNo) {
    List<String> customerInfo = null;
    Customer customer = getSearchCustomer(pNo);
    if (customer != null) {
      ArrayList<String> getList = new ArrayList<>();
      getList.add(customer.toString());
      if (customer.getAccounts() != null) {
        for (Account acc : customer.getAccounts()) {
          getList.add(acc.toString());
        }
      }
      customerInfo = List.copyOf(getList);
    }
    return customerInfo;
  }

  /**
   * Hjälpmetod som letar reda på ett konto
   *
   * @param accounts         , Lista med konton
   * @param theAccountNumber , som söks upp
   * @return result , pekare till konto om det finns.
   */
  private Account getSearchAccount(List<Account> accounts, int theAccountNumber) {
    Account result = null;
    for (Account acc : accounts) {
      if (acc.getAccountNumber() == theAccountNumber) {
        result = acc;
        break;
      }
    }
    return result;
  }

  /**
   * Hjälpmetod som letar reda på en kund med hjälp av pNr som är unikt.
   *
   * @param theSearchNo
   * @return pekare till kundens post om den finns.
   */
  private Customer getSearchCustomer(String theSearchNo) {
    Customer result = null;
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(theSearchNo)) {
        result = customer;
        break;
      }
    }
    return result;
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
      Customer customer = getSearchCustomer(pNo);
      if (customer != null) {
        Account acc = getSearchAccount(customer.getAccounts(), accountId);
        if (acc != null) {
          result = acc.withdraw(amount);
        }
      }
    }
    return result;
  }
}