import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static class Account {
        private final String currency;
        private double balance;
        private final boolean frozen;

        private Account(String currency, double balance, boolean frozen) {
            this.currency = currency;
            this.balance = balance;
            this.frozen = frozen;
        }
    }

    private final Map<String, Account> accounts = new HashMap<>();

    public AccountService() {
        accounts.put("ACC-USD", new Account("USD", 1000.00, false));
        accounts.put("ACC-EUR", new Account("EUR", 500.00, false));
        accounts.put("FROZEN-EUR", new Account("EUR", 250.00, true));
    }

    public String getCurrency(String accountId) {
        Account account = accounts.get(accountId);
        return account == null ? null : account.currency;
    }

    public boolean debit(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null || account.frozen || amount <= 0 || account.balance < amount) {
            System.out.println("Debit rejected: source account is missing, frozen, or has insufficient funds.");
            return false;
        }
        account.balance -= amount;
        System.out.printf("Debited %.2f %s from %s.%n", amount, account.currency, accountId);
        return true;
    }

    public boolean credit(String accountId, double amount, String currency) {
        Account account = accounts.get(accountId);
        if (account == null || account.frozen || !account.currency.equals(currency) || amount < 0) {
            System.out.println("Credit rejected: destination account is invalid, frozen, or uses another currency.");
            return false;
        }
        account.balance += amount;
        System.out.printf("Credited %.2f %s to %s.%n", amount, currency, accountId);
        return true;
    }

    // Refund is deliberately separate from an ordinary credit: it restores the exact debit
    // to the original account even if normal incoming transfers are restricted.
    public void refundDebit(String accountId, double exactAmount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalStateException("Cannot refund: source account disappeared.");
        }
        account.balance += exactAmount;
        System.out.printf("Refunded exactly %.2f %s to %s.%n",
                exactAmount, account.currency, accountId);
    }

    public double balanceOf(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Unknown account: " + accountId);
        }
        return account.balance;
    }
}
