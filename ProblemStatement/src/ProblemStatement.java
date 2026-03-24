import java.util.*;

public class ProblemStatement {

    // ---------------- LINEAR SEARCH ----------------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: Not found");
        }

        System.out.println("Comparisons: " + comparisons);
    }

    // ---------------- FLOOR ----------------
    public static int findFloor(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int floor = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] < target) {
                floor = arr[mid]; // possible answer
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return floor;
    }

    // ---------------- CEILING ----------------
    public static int findCeiling(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] > target) {
                ceil = arr[mid]; // possible answer
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ceil;
    }

    // ---------------- INSERTION POINT (LOWER BOUND) ----------------
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low; // position where element should be inserted
    }

    // Counter class
    static class Counter {
        int count = 0;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // Linear search (unsorted case)
        linearSearch(risks, target);

        // Binary search operations
        Counter counter = new Counter();

        int floor = findFloor(risks, target, counter);
        int ceiling = findCeiling(risks, target, counter);
        int insertPos = insertionPoint(risks, target);

        System.out.println("\nBinary Search:");
        System.out.println("Floor(" + target + "): " + floor);
        System.out.println("Ceiling(" + target + "): " + ceiling);
        System.out.println("Insertion Point: index " + insertPos);
        System.out.println("Comparisons: " + counter.count);
    }
}