package streamoperations;

import java.util.Currency;

public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    private final  Currency currency;
    public Transaction(Trader trader, int year, int value, Currency currency){
        this.trader = trader;
        this.year = year;
        this.value = value;
        this.currency=currency;
    }
    public Trader getTrader(){
        return this.trader;
    }
    public int getYear(){
        return this.year;
    }
    public int getValue(){
        return this.value;
    }
    public Currency getCurrency(){
        return this.currency;
    }
    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }

}
