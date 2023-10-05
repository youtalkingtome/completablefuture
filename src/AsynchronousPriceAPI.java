import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.random.RandomGenerator;

public class AsynchronousPriceAPI {

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsyncSA(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    public double getPrice(String product) {
        return calculatePrice(product);
    }
    private static double calculatePrice(String product) {
        delay();
        return RandomGenerator.getDefault().nextDouble() * product.charAt(0) + product.charAt(1);
    }
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
