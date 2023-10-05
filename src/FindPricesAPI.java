import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FindPricesAPI {


    static List<Shop> shops;
    public FindPricesAPI()
    {
        shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("Apple"),
                new Shop("MacBoooks"),
                new Shop("Toys"),
                new Shop("Ebay"),
                new Shop("Online America"));


    }


    public  List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }
    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPricesCF(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        shop.getPrice(product)))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
    public List<String> findPricesCFExec(String product) {
        final Executor executor =
                Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                        new ThreadFactory() {
                            public Thread newThread(Runnable r) {
                                Thread t = new Thread(r);
                                t.setDaemon(true);
                                return t;
                            }
                        });
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        shop.getPrice(product),executor))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }


    public static void main(String[] args)
    {
        FindPricesAPI api=new FindPricesAPI();
        long start = System.nanoTime();
        System.out.println(api.findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Stream Processing Done in " + duration + " msecs");

        long start2 = System.nanoTime();

        System.out.println(api.findPricesParallel("myPhone27S"));
        long durationParallel = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Parallel Streaming Processing Done in " + durationParallel + " msecs");

        long start3 = System.nanoTime();

        System.out.println(api.findPricesCF("myPhone27S"));
        long durationCF = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Completable Future is done in " + durationCF + " msecs");

        long start4 = System.nanoTime();

        System.out.println(api.findPricesCFExec("myPhone27S"));
        long durationCFExec = (System.nanoTime() - start4) / 1_000_000;
        System.out.println("Completable Future is done in " + durationCFExec + " msecs");

    }

}

