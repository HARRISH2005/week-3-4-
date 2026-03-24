import java.util.*;

public class ProblemStatement {

    // ---------------- LINEAR SEARCH ----------------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        if (first != -1) {
            System.out.println("First occurrence: " + first);
            System.out.println("Last occurrence: " + last);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons: " + comparisons);
    }

    // ---------------- BINARY SEARCH (FIRST) ----------------
    public static int binaryFirst(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // go left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ---------------- BINARY SEARCH (LAST) ----------------
    public static int binaryLast(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // go right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // Counter class for tracking comparisons
    static class Counter {
        int count = 0;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        String[] logs = {"accA", "accB", "accB", "accC"};

        String target = "accB";

        // Linear Search
        linearSearch(logs, target);

        // Binary Search (requires sorted input)
        Arrays.sort(logs);

        Counter counter = new Counter();

        int first = binaryFirst(logs, target, counter);
        int last = binaryLast(logs, target, counter);

        System.out.println("\nBinary Search:");
        if (first != -1) {
            System.out.println("First occurrence: " + first);
            System.out.println("Last occurrence: " + last);
            System.out.println("Count: " + (last - first + 1));
        } else {
            System.out.println("Not found");
        }

        System.out.println("Comparisons: " + counter.count);
    }
}