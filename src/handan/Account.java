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
	BigDecimal balance;
	BigDecimal interestRate;
	int accountNumber;
	String accountName;

	// Variabler som är gemensamt för alla konton
	private static int lastAssignedNumber = 1000;
	private static String accountType = "Sparkonto";

	// Konstruktor för ett nytt bankkonto
	public Account() {
		this(BigDecimal.ZERO, BigDecimal.ZERO);
	}

	public Account(BigDecimal balance) {
		this(balance, BigDecimal.ZERO);
	}

	public Account(BigDecimal inBalance, BigDecimal inInterestRate) {
		balance = inBalance;
		interestRate = inInterestRate;
		accountNumber = lastAssignedNumber++;
		accountName = accountType;
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
}