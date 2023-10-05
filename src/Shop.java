import java.util.random.RandomGenerator;

public class Shop {
   private  String name;
    public Shop(String bestShop) {
        this.name=bestShop;
    }

    public String getName() {
        return this.name;
    }
    public void  setName(String name) {
        this.name=name;
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
