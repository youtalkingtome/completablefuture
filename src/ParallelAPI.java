import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

import static java.util.stream.Collectors.toList;

public class ParallelAPI {

    static List<Shop> shops;
    static {
        shops= Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));
    }
    public List<String> getPrice(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), calculatePrice(product)))
                .collect(toList());
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
