/**
 * Klass som definierar en lista med kunder.
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.util.ArrayList;
import java.util.List;

public class BankLogic extends Customer {

  private List<Customer> bankCustomer = new ArrayList<>();

  @Override
  public boolean changeCustomerName(String name, String surname, String pNo) {
    boolean result = false;
    if (!((name.isEmpty()) && (surname.isEmpty()))) {
      for (Customer customer : bankCustomer) {
        if (customer.getPersonalNumber().equals(pNo)) {
          result = customer.changeCustomerName(name, surname, pNo);
          break;
        }
      }
    }
    return result;
  }

  public String closeAccount(String pNo, int accountId) {
    String result = null;
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(pNo)) {
        for (Account acc : customer.accounts) {
          if (acc.getAccountNumber() == accountId) {
            result = acc.toString(false) + " " + acc.calculateInterest();
            customer.accounts.remove(acc);
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

  public int createSavingsAccount(String pNo) {
    int result = -1;
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(pNo)) {
        if (customer.accounts == null) {
          customer.accounts = new ArrayList<Account>();
        }
        Account newAccount = new Account(0, 2.4, true);
        customer.accounts.add(newAccount);
        result = newAccount.getAccountNumber();
        break;
      }
    }
    return result;
  }

  public List<String> deleteCustomer(String pNo) {
    List<String> result = null;
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(pNo)) {
        ArrayList<String> deList = new ArrayList<>();
        deList.add(customer.toString());
        if (customer.accounts != null) {
          for (Account acc : customer.accounts) {
            deList.add(acc.toString(false) + " " + acc.calculateInterest());
          }
          while (!customer.accounts.isEmpty()) {
            customer.accounts.removeLast();
          }
        }
        bankCustomer.remove(customer);
        result = deList;
        break;
      }
    }
    return result;
  }

  public boolean deposit(String pNo, int accountId, int amount) {
    boolean result = false;
    if (amount > 0) {
      for (Customer customer : bankCustomer) {
        if (customer.getPersonalNumber().equals(pNo)) {
          for (Account acc : customer.accounts) {
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

  public String getAccount(String pNo, int accountId) {
    String result = null;
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(pNo)) {
        for (Account acc : customer.accounts) {
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

  public List<String> getCustomer(String pNo) {
    List<String> customerInfo = null;
    for (Customer customer : bankCustomer) {
      if (customer.getPersonalNumber().equals(pNo)) {
        ArrayList<String> getList = new ArrayList<>();
        getList.add(customer.toString());
        if (customer.accounts != null) {
          for (Account acc : customer.accounts) {
            getList.add(acc.toString());
          }
        }
        customerInfo = getList;
        break;
      }
    }
    return customerInfo;
  }

  public boolean withdraw(String pNo, int accountId, int amount) {
    boolean result = false;
    if (amount > 0) {
      for (Customer customer : bankCustomer) {
        if (customer.getPersonalNumber().equals(pNo)) {
          for (Account acc : customer.accounts) {
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