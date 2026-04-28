import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class BankAccountTest {
  private BankAccount acc;
  @BeforeEach
  void setUp() {
    acc = new BankAccount("12345", "Nguyen Van A", 500.0);
  }
  @Test
  @DisplayName("Valid Deposits (EP)")
  void testValidDeposits() {
    acc.deposit(100.0);
    assertEquals(600.0, acc.getBalance());
  }
  @Test
  @DisplayName("Deposit Negative (EP)")
  void testDepositNegative() {
    assertThrows(IllegalArgumentException.class, () -> acc.deposit(-50.0));
  }
  @Test
  @DisplayName("Deposit Zero (EP)")
  void testDepositZero() {
    assertThrows(IllegalArgumentException.class, () -> acc.deposit(0.0));
  }
  @Test
  @DisplayName("Withdraw Valid (EP)")
  void testWithdrawValid() {
    boolean result = acc.withdraw(200.0);
    assertTrue(result);
    assertEquals(300.0, acc.getBalance());
  }
  @Test
  @DisplayName("Withdraw OverBalance (EP)")
  void testWithdrawOverBalance() {
    boolean result = acc.withdraw(600.0);
    assertFalse(result);
    assertEquals(500.0, acc.getBalance());
  }
  @Test
  @DisplayName("Withdraw ExactlyBalance (EP)")
  void testWithdrawExactlyBalance() {
    boolean result = acc.withdraw(500.0);
    assertTrue(result);
    assertEquals(0.0, acc.getBalance());
  }
  @Test
  @DisplayName("Withdraw Negative (EP)")
  void testWithdrawNegative() {
    assertThrows(IllegalArgumentException.class, () -> acc.withdraw(-10.0));
  }
  @Test
  @DisplayName("Check for consistency in the required sequence.")
  void testConsistencySequence() {
    BankAccount account = new BankAccount("88024", "Nguyen Van B", 0.0);
    assertEquals(0.0, account.getBalance());

    account.deposit(500.0);
    assertEquals(500.0, account.getBalance());

    account.withdraw(200.0);
    assertEquals(300.0, account.getBalance());

    assertFalse(account.withdraw(400.0));

    assertEquals(300.0, account.getBalance());
  }
}

