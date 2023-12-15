package streamoperations;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamOperations {


    // Get transaction in 2011 and sort by value.
    public List<Transaction> getTr2011()
    {
        List<Transaction> tr2011 =
                SetUp.getTransactions().stream()
                        .filter(transaction -> transaction.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(toList());
        return tr2011;
    }
    public List<String> getCities() {
        List<String> cities =
                SetUp.getTransactions().stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        return cities;
    }

  //Finds all traders from Cambridge and sort them by name
    public List<Trader> getTraders()
    {
        List<Trader> traders =
                SetUp.getTransactions().stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        return traders;
    }
    // Returns a string of all traders’ names sorted alphabetically
    //Note that this solution is inefficient (all Strings are repeatedly concatenated, which creates a new String object at each iteration).
    public String getNames()
    {
        String traderStr =
                SetUp.getTransactions().stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        return traderStr;
    }
    public boolean isAnyTraderInMilan()
    {

        boolean milanBased =
                SetUp.getTransactions().stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan"));
        return milanBased;
    }
    public String getNamesUsingJoining() {
        String traderStr =
                SetUp.getTransactions().stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(joining());
        return  traderStr;

    }
    // Prints all transactions’ values from the traders living in Cambridge
    public void printTransactionsFromCambridge()
    {
        SetUp.getTransactions().stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }
    public Optional<Integer> getHigestTransaction()
    {
        Optional<Integer> highestValue =
                SetUp.getTransactions().stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);
        return highestValue;
    }
    // Finds the transaction with the smallest value
    public Optional<Transaction> getSmallestTransaction()
    {
        Optional<Transaction> smallestTransaction =
                SetUp.getTransactions().stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);
        return smallestTransaction;
    }
    public StreamOperations() {
        Map<Currency, List<Transaction>> transactionsByCurrencies =
                new HashMap<>();
        for (Transaction transaction :SetUp.getTransactions()) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency =
                    transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies
                        .put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
    }
}
