package streamoperations;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class SetUp {

    private static  List<Transaction> transactions;

    SetUp() {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300, Currency.getInstance("USD")),
                new Transaction(raoul, 2012, 1000,Currency.getInstance("EUR")),
                new Transaction(raoul, 2011, 400,Currency.getInstance("USD")),
                new Transaction(mario, 2012, 710,Currency.getInstance("INR")),
                new Transaction(mario, 2012, 700,Currency.getInstance("USD")),
                new Transaction(alan, 2012, 950,Currency.getInstance("INR"))
        );
    }
    public static List<Transaction> getTransactions()
    {
        return transactions;
    }
}
