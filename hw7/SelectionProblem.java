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
            Integer arr[] = new Integer[] {12, 3, 5, 7, 19};
            int k = 1_000_000;
            System.out.print("K'th smallest element is " + kthSmallest(arr, k));
        }
    }
    class sixA {
        public static int findKthLargest(List<Integer> nums, int k) {
            PriorityQueue<Integer> input = new PriorityQueue<Integer>(k);
            for (int i: nums) {
                input.offer(i);
                if (input.size()>k)
                    input.poll();
            }
            return input.peek();
        }
        public static void main(String[] args) {
            List<Integer> ints = Arrays.asList(7, 4, 6, 3, 9, 1);
            int k = 1_000_000;
            System.out.println("k'th largest array element is " + findKthLargest(ints, k));
        }
    }
    class sixB {
        public static int findKthLargest(List<Integer> ints, int k) {
            if (ints == null || ints.size() < k)
                System.exit(-1);
            PriorityQueue<Integer> input = new PriorityQueue<>(ints.subList(0, k));
            for (int i = k; i < ints.size(); i++) {
                if (ints.get(i) > input.peek()) {
                    input.poll();
                    input.add(ints.get(i));
                }
            }
            return input.peek();
        }
        public static void main(String[] args) {
            List<Integer> ints = Arrays.asList(2*500000);
            int k = 1_000_000;
            System.out.println("k'th largest array element is " + findKthLargest(ints, k));
        }
    }
}
