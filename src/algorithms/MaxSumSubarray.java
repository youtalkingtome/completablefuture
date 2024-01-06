package algorithms;
public class MaxSumSubarray {

    public static int findMaxSumSubarray(int[] arr, int k) {
        int windowSum = 0, maxSum = 0;

        // Calculate the sum of the first window of size k
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        // Initialize maxSum as the sum of the first window
        maxSum = windowSum;

        // Slide the window; subtract the element going out and add the element coming in
        for (int end = k; end < arr.length; end++) {
            windowSum += arr[end] - arr[end - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] exampleArray = {1, 2, 3, 4, 5};
        int k = 2;
        System.out.println("Maximum sum of a subarray of size " + k + ": " + findMaxSumSubarray(exampleArray, k));
    }
}
