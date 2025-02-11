/**
 * Klass som definierar ett bankkonto.
 * @author Hans Danielsson, handan-2
 */
package handan;

// Importsatser
import java.math.BigDecimal;

// Klassdeklarationer för Kontot
public class Account {

	// Variabler för enskilda konton
	private int accountNumber; // 1001, 1002, 1003, 1004
	private String accountType;
	private BigDecimal balance;
	private BigDecimal interestRate;

	// Variabler som är gemensamt för alla konton
	private static int lastAssignedNumber = 1000;
	private static String accountName = "Sparkonto";

	// Konstruktor för ett nytt bankkonto
	public Account() {
		this(0, 2.4);
	}

	public Account(int balance) {
		this(balance, 2.4);
	}

	public Account(int inBalance, double inInterestRate) {
		accountNumber = lastAssignedNumber++;
		accountType = accountName;
		balance = BigDecimal.valueOf(inBalance);
		interestRate = BigDecimal.valueOf(inInterestRate);
	}

	public boolean deposit(int amount) {
		if (amount > 0) {
			balance = balance.add(BigDecimal.valueOf(amount));
			return true;
		} else {
			return false;
		}
	}

	public boolean withdraw(int amount) {
		if (amount > 0 && amount <= balance.intValue()) {
			balance = balance.subtract(BigDecimal.valueOf(amount));
			return true;
		} else {
			return false;
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal calculateInterest() {
		return balance.multiply(interestRate).divide(BigDecimal.valueOf(100));
	}

	@Override
	public String toString() {
		return accountNumber + " " + balance + " kr " + accountType + " " + interestRate + "%";
	}
}