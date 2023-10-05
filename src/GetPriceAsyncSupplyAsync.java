import java.util.concurrent.Future;

public class GetPriceAsyncSupplyAsync {



    public static void main(String args[]) {
        AsynchronousPriceAPI asynchronousPriceAPI=new AsynchronousPriceAPI();
        long start = System.nanoTime();
        Future<Double> futurePrice = asynchronousPriceAPI.getPriceAsyncSA("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        // Do some more tasks, like querying other shops
        doSomethingElse();
// while the price of the product is being calculated
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
    }
}
