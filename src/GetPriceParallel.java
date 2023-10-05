public class GetPriceParallel {

    public static void main(String args[]) {
        ParallelAPI parallelAPI=new ParallelAPI();
        long start = System.nanoTime();
        parallelAPI.getPrice("AppleIphone");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        // Do some more tasks, like querying other shops
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
    }
}
