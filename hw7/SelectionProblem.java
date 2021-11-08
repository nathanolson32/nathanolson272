import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class SelectionProblem <E extends Comparable<E>> {
    int k;
    ArrayList<E> input;

        public static int kthSmallest(Integer[] input, int k) {
            Arrays.sort(input);
            return input[k - 1];
        }
        public static int findKthLargestA(List<Integer> nums, int k) {
            PriorityQueue<Integer> input = new PriorityQueue<Integer>(k);
            for (int i: nums) {
                input.offer(i);
                if (input.size()>k)
                    input.poll();
            }
            return input.peek();
        }
        public static int findKthLargestB(List<Integer> ints, int k) {
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
            Integer arr[] = new Integer[] {12, 3, 4, 7, 19};
            int k = 2;
            System.out.println("k'th smallest element is " + kthSmallest(arr, k));

            List<Integer> intsA = Arrays.asList(2, 4, 5, 3, 9, 1);
            System.out.println("k'th largest  element is " + findKthLargestA(intsA, k));

            List<Integer> intsB = Arrays.asList(1,3,6,4,7,2);
            System.out.println("k'th largest element is " + findKthLargestB(intsB, k));

        }
}
