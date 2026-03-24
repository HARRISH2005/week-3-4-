import java.util.*;

// Asset class
class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class ProblemStatement {

    // ---------------- MERGE SORT (ASC, STABLE) ----------------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // STABLE: <= ensures original order preserved
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ---------------- QUICK SORT (DESC + VOL ASC) ----------------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionMedianOf3(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Median-of-3 pivot selection
    private static int medianOf3(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        double a = arr[low].returnRate;
        double b = arr[mid].returnRate;
        double c = arr[high].returnRate;

        if ((a > b) != (a > c)) return low;
        else if ((b > a) != (b > c)) return mid;
        else return high;
    }

    private static int partitionMedianOf3(Asset[] arr, int low, int high) {
        int pivotIndex = medianOf3(arr, low, high);

        // swap pivot with end
        Asset temp = arr[pivotIndex];
        arr[pivotIndex] = arr[high];
        arr[high] = temp;

        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot)) {
                i++;
                Asset t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        Asset t = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = t;

        return i + 1;
    }

    // Comparison logic: DESC return, ASC volatility
    private static boolean compare(Asset a, Asset b) {
        if (a.returnRate > b.returnRate) return true;
        if (a.returnRate == b.returnRate) {
            return a.volatility < b.volatility;
        }
        return false;
    }

    // ---------------- HYBRID (INSERTION FOR SMALL PARTITIONS) ----------------
    public static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && !compare(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        // Merge Sort (ASC)
        mergeSort(assets, 0, assets.length - 1);
        System.out.println("Merge Sort (ASC): " + Arrays.toString(assets));

        // Quick Sort (DESC + VOL ASC)
        quickSort(assets, 0, assets.length - 1);
        System.out.println("Quick Sort (DESC): " + Arrays.toString(assets));
    }
}