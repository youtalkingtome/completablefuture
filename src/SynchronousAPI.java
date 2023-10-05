import java.util.random.RandomGenerator;

public class SynchronousAPI {
        public double getPrice(String product) {
            return calculatePrice(product);
        }
        private  double calculatePrice(String product) {
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
