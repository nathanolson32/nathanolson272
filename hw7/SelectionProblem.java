import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class SelectionProblem <E extends Comparable<E>> {
    int k;
    ArrayList<E> input;

    class oneB {
        public static int kthSmallest(Integer[] input, int k) {
            Arrays.sort(input);
            return input[k - 1];
        }
        public static void main(String[] args) {
            Integer arr[] = new Integer[]{12, 3, 5, 7, 19};
            int k = 2;
            System.out.print("K'th smallest element is " + kthSmallest(arr, k));
        }
    }
    // A Java program to find k'th smallest element using max heap
    static class smallestHeap {
        class MaxHeap {
            int[] harr;
            int capacity;
            int heap_size;
            int parent(int i) {
                return (i - 1) / 2;
            }
            int left(int i) {
                return (2 * i + 1);
            }
            int right(int i) {
                return (2 * i + 2);
            }
            int getMax() {
                return harr[0];
            }
            void replaceMax(int x) {
                this.harr[0] = x;
                maxHeapify(0);
            }
            MaxHeap(int a[], int size) {
                heap_size = size;
                harr = a;
                int i = (heap_size - 1) / 2;
                while (i >= 0) {
                    maxHeapify(i);
                    i--;
                }
            }
            int extractMax() {
                if (heap_size == 0)
                    return Integer.MAX_VALUE;
                int root = harr[0];
                if (heap_size > 1) {
                    harr[0] = harr[heap_size - 1];
                    maxHeapify(0);
                }
                heap_size--;
                return root;
            }
            void maxHeapify(int i) {
                int l = left(i);
                int r = right(i);
                int largest = i;
                if (l < heap_size && harr[l] > harr[i])
                    largest = l;
                if (r < heap_size && harr[r] > harr[largest])
                    largest = r;
                if (largest != i) {
                    int t = harr[i];
                    harr[i] = harr[largest];
                    harr[largest] = t;
                    maxHeapify(largest);
                }
            }
        }
        int kthSmallest(int arr[], int n, int k) {
            MaxHeap mh = new MaxHeap(arr, k);
            for (int i = k; i < n; i++)
                if (arr[i] < mh.getMax())
                    mh.replaceMax(arr[i]);
            return mh.getMax();
        }
        public static void main(String[] args) {
            int arr[] = {12, 3, 5, 7, 19};
            int n = arr.length, k = 4;
            smallestHeap input = new smallestHeap();
            System.out.print("K'th smallest element is " + input.kthSmallest(arr, n, k));
        }
    }


    class kLARGE {
        public static void kLargest(Integer[] arr, int k) {
            Arrays.sort(arr, Collections.reverseOrder());
            for (int i = 0; i < k; i++)
                System.out.print(arr[i] + " ");
        }
        public static ArrayList<Integer> kLargest(int[] arr, int k) {
            Integer[] obj_array = Arrays.stream(arr).boxed().toArray(Integer[]::new);
            Arrays.sort(obj_array, Collections.reverseOrder());
            ArrayList<Integer> list = new ArrayList<>(k);
            for (int i = 0; i < k; i++)
                list.add(obj_array[i]);
            return list;
        }
        public static void main(String[] args) {
            Integer arr[] = new Integer[]{1, 23, 12, 9, 30, 2, 50};
            int k = 3;
            kLargest(arr, k);
            int[] prim_array = {1, 23, 12, 9, 30, 2, 50};
            System.out.print(kLargest(prim_array, k));
        }
    }
    
    class Main {
        // Function to find the k'th largest element in an array using max-heap
        public static int findKthLargest(List<Integer> ints, int k) {
            if (ints == null || ints.size() < k)
                System.exit(-1);
            PriorityQueue<Integer> input = new PriorityQueue<>((a, b) -> b - a);
            input.addAll(ints);
            while (--k > 0) {
                input.poll();
            }
            return input.peek();
        }
        public static void main(String[] args) {
            List<Integer> ints = Arrays.asList(7, 4, 6, 3, 9, 1);
            int k = 2;
            System.out.println("k'th largest array element is " + findKthLargest(ints, k));
        }
    }

}
