import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccount {
  private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);
  private final String accountNumber;
  private String ownerName;
  private double balance;

  public BankAccount(String accountNumber, String ownerName) {
    this.accountNumber = accountNumber;
    this.ownerName = ownerName;
    this.balance = 0.0;
  }

  public BankAccount(String accountNumber, String ownerName, double initialBalance) {
    this.accountNumber = accountNumber;
    this.ownerName = ownerName;
    if (initialBalance < 0) {
      logger.error("Attempted to create account {} with invalid balance: {}", accountNumber, initialBalance);
      this.balance = 0.0;
    } else {
      this.balance = initialBalance;
      logger.info("Account {} created successfully for {} with balance {}", accountNumber, ownerName, initialBalance);
    }
  }

  public void deposit(double amount) {
    if (amount <= 0) {
      logger.warn("Invalid deposit attempt of {} on account {}", amount, accountNumber);
      throw new IllegalArgumentException("The deposit amount must be greater than 0.");
    }
    this.balance += amount;
    logger.info("Deposited {} to account {}. New balance: {}", amount, accountNumber, balance);
  }

  public boolean withdraw(double amount) {
    if (amount <= 0) {
      logger.warn("Invalid withdrawal attempt: {} on account {}", amount, accountNumber);
      throw new IllegalArgumentException("The withdrawal amount must be greater than 0.");
    }
    if (amount > this.balance) {
      logger.error("Withdrawal failed: Insufficient funds. Account: {}, Balance: {}, Requested: {}", accountNumber, balance, amount);
      return false;
    }
    this.balance -= amount;
    logger.info("Withdrew {} from account {}. New balance: {}", amount, accountNumber, balance);
    return true;
  }

  public double getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }
}
