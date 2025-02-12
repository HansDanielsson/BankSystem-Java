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
  private int accountNumber; // 1001, 1002, 1003, 1004
  private String accountType;

  private BigDecimal balance;
  private BigDecimal interestRate;

  // Konstruktor för ett nytt bankkonto
  public Account() {
    this(0, 2.4, false);
  }

  public Account(int balance) {
    this(balance, 2.4, false);
  }

  public Account(int inBalance, double inInterestRate, boolean addNumber) {
    if (addNumber) {
      lastAssignedNumber++;
    }
    accountNumber = lastAssignedNumber;
    accountType = accountName;
    balance = BigDecimal.valueOf(inBalance);
    interestRate = BigDecimal.valueOf(inInterestRate);
  }

  /**
   * Rutin som beräknar räntan på BigDecimal-modell enligt saldo*räntesats / 100.0
   * konverterar till double-tal
   *
   * @return räntanAccount
   */
  public String calculateInterest() {
    double numberInterest = balance.multiply(interestRate).divide(BigDecimal.valueOf(100)).doubleValue();
    return NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(numberInterest);
  }

  /**
   * Rutin som sätter in beloppet (amount) till saldo (balance) Möjligt att
   * if-sats är onödig
   *
   * @param amount
   * @return om insättning är > 0
   */
  public boolean deposit(int amount) {
    if (amount > 0) {
      balance = balance.add(BigDecimal.valueOf(amount));
      return true;
    }
    return false;
  }

  /**
   * Hämtar kontonummer
   *
   * @return
   */
  public int getAccountNumber() {
    return accountNumber;
  }

  @Override
  public String toString() {
    return toString(true);
  }

  public String toString(boolean percentOn) {
    String balanceStr = NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(balance);

    if (percentOn) {
      NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.of("SV", "SE"));
      percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
      String percentStr = percentFormat.format(interestRate.doubleValue() / 100.0);
      return accountNumber + " " + balanceStr + " " + accountType + " " + percentStr;
    }
    return accountNumber + " " + balanceStr + " " + accountType;
  }

  /**
   * Rutin som tar bort beloppet (amount) från saldo (balance) belopet ska vara >
   * 0 och att beloppet finns på saldo
   *
   * @param amount
   * @return om beloppet har minskat saldo
   */
  public boolean withdraw(int amount) {
    if (amount > 0 && amount <= balance.intValue()) {
      balance = balance.subtract(BigDecimal.valueOf(amount));
      return true;
    }
    return false;
  }
}