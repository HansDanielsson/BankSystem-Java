/**
 * Klass som definierar ett bankkonto.
 * @author Hans Danielsson, handan-2
 */
package handan;

// Importsatser
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

// Klassdeklarationer för Kontot
public class Account {

  // Variabler som är gemensamt för alla konton
  private static int lastAssignedNumber = 1000;
  private static String accountName = "Sparkonto";

  // Variabler för enskilda konton
  private int accountNumber; // 1001, 1002, 1003, 1004 osv.
  private String accountType;
  private BigDecimal balance;
  private BigDecimal interestRate;

  // Default Konstruktor för ett nytt bankkonto
  protected Account() {
    this(accountName, 0, 2.4, false);
  }

  /**
   * Konstruktor för nytt bankkonto
   *
   * @param theAccountType
   * @param theBalance
   * @param theInterestRate
   * @param addNumber
   */
  protected Account(String theAccountType, int theBalance, double theInterestRate, boolean addNumber) {
    if (addNumber) {
      lastAssignedNumber++; // Ska bara räknas upp med 1 ibland.
    }
    accountNumber = lastAssignedNumber;
    accountType = theAccountType;
    balance = BigDecimal.valueOf(theBalance);
    interestRate = BigDecimal.valueOf(theInterestRate);
  }

  /**
   * Rutin som beräknar räntan på BigDecimal-modell enligt saldo*räntesats / 100.0
   * konverterar till double-tal
   *
   * @return x xxx kr
   */
  protected String calculateInterest() {
    double numberInterest = balance.multiply(interestRate).divide(BigDecimal.valueOf(100)).doubleValue();
    return NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(numberInterest);
  }

  /**
   * Rutin som sätter in beloppet (amount) till saldo (balance) Kontroll har redan
   * utförts på amount > 0
   *
   * @param amount
   * @return true hela tiden för att amount > 0
   */
  protected boolean deposit(int amount) {
    try {
      balance = balance.add(BigDecimal.valueOf(amount));
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Hämtar kontonummer
   *
   * @return accountNumber
   */
  protected int getAccountNumber() {
    return accountNumber;
  }

  /**
   * Vid bearbetning av kontot med kontonummer saldo kontotyp.
   *
   * @return "kontonr saldo kontotyp <procent %>"
   */
  protected String infoAccount() {
    String balanceStr = NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(balance);
    return accountNumber + " " + balanceStr + " " + accountType;
  }

  private String makeAccountInfo(int theBalance, double theInterestRate) {
    String balanceStr = NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(theBalance);
    NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.of("SV", "SE"));
    percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
    String percentStr = percentFormat.format(theInterestRate / 100.0);
    return accountNumber + " " + balanceStr + " " + accountType + " " + percentStr;
  }

  /**
   * Vid utskrift av kontot med kontonummer saldo kontotyp, percent.
   *
   * @return "kontonr saldo kontotyp <procent %>"
   */
  @Override
  public String toString() {
    return makeAccountInfo(balance.intValue(), interestRate.doubleValue());
  }

  /**
   * Rutin som tar bort beloppet (amount) från saldo (balance) belopet ska vara >
   * 0 och att beloppet finns på saldo
   *
   * @param amount
   * @return om beloppet har minskat saldo
   */
  protected boolean withdraw(int amount) {
    if (amount > balance.intValue()) {
      return false;
    }

    try {
      balance = balance.subtract(BigDecimal.valueOf(amount));
    } catch (Exception e) {
      // Empty, ingen hantering
      return false;
    }
    return true;
  }
}