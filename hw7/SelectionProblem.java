import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class SelectionProblem <E extends Comparable<E>> {
    int k;
    ArrayList<E> input ;

   // We have discussed a similar problem to print k largest elements.
    //Method 1 (Simple Solution)
    //A simple solution is to sort the given array using a O(N log N) sorting algorithm
    //like Merge Sort, Heap Sort, etc, and return the element at index k-1 in the
    //sorted array.
    //Time Complexity of this solution is O(N Log N)

// Java code for kth smallest element
// in an array
    class kth {
        // Function to return k'th smallest
        // element in a given array
        public static int kthSmallest(Integer[] arr, int k) {
            // Sort the given array
            Arrays.sort(arr);
            // Return k'th element in
            // the sorted array
            return arr[k - 1];
        }
        // driver program
        public static void main(String[] args) {
            Integer arr[] = new Integer[] { 12, 3, 5, 7, 19 };
            int k = 2;
            System.out.print("K'th smallest element is " + kthSmallest(arr, k));
        }
    }

   // Output
   // K'th smallest element is 5

   // we can find the kth smallest element in time complexity better than O(N log N).
    //we know the Set in C++ STL is implemented using Binary Search Tree and we also know that
    //the time complexity of all cases(searching , inserting, deleting )
   // in BST is log (n) in average case and O(n) in worst case .   we are using set because it
   // is mentioned in the question that all the elements in array re distinct.

// A Java program to find k'th smallest element using min heap
static class GFG {
        // A class for Max Heap
        class MinHeap {
            int[] harr; // pointer to array of elements in heap
            int capacity; // maximum possible size of min heap
            int heap_size; // Current number of elements in min heap
            int parent(int i) { return (i - 1) / 2; }
            int left(int i) { return ((2 * i )+ 1); }
            int right(int i) { return ((2 * i) + 2); }
            int getMin() { return harr[0]; } // Returns minimum
            // to replace root with new node x and heapify() new root
            void replaceMax(int x) {
                this.harr[0] = x;
                minHeapify(0);
            }
            MinHeap(int a[], int size) {
                heap_size = size;
                harr = a; // store address of array
                int i = (heap_size - 1) / 2;
                while (i >= 0) {
                    minHeapify(i);
                    i--;
                }
            }
            //method to remove maximum element (or root) from min heap
            int extractMin() {
                if (heap_size == 0)
                    return Integer.MAX_VALUE;
                // Store the maximum vakue.
                int root = harr[0];
                // If there are more than 1 items, move the last item to root
                // and call heapify.
                if (heap_size > 1) {
                    harr[0] = harr[heap_size - 1];
                    minHeapify(0);
                }
                heap_size--;
                return root;
            }
            // A recursive method to heapify a subtree with root at given index
            // This method assumes that the subtrees are already heapified
            void minHeapify(int i) {
                int l = left(i);
                int r = right(i);
                int smallest = i;
                if (l < heap_size && harr[l] < harr[i])
                    smallest = l;
                if (r < heap_size && harr[r] < harr[smallest])
                    smallest = r;
                if (smallest != i) {
                    int t = harr[i];
                    harr[i] = harr[smallest];
                    harr[smallest] = t;
                    minHeapify(smallest);
                }
            }
        }

        // Function to return k'th largest element in a given array
        int kthSmallest(int arr[], int n, int k) {
            // Build a heap of first k elements: O(k) time
            MinHeap mh = new MinHeap(arr, n);
            // Process remaining n-k elements. If current element is
            // smaller than root, replace root with current element
            for (int i = 0; i < k - 1; i++)
                mh.extractMin();
            // Return root
            return mh.getMin();
        }
        // Driver program to test above methods
        public static void main(String[] args) {
            int arr[] = { 12, 3, 5, 7, 19 };
            int n = arr.length, k = 2;
            GFG gfg = new GFG();
            System.out.print("K'th smallest element is " + gfg.kthSmallest(arr, n, k));
        }
    }
   // Output
   // K'th smallest element is 5
   // Time complexity of this solution is O(n + kLogn).


    //Method 4 (Using Max-Heap)
     //We can also use Max Heap for finding the k’th smallest element. Following is an algorithm.
       //     1) Build a Max-Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)
        //  2) For each element, after the k’th element (arr[k] to arr[n-1]), compare it with root of MH.
         //   ……a) If the element is less than the root then make it root and call heapify for MH
//……b) Else ignore it.
// The step 2 is O((n-k)*logk)
//3) Finally, the root of the MH is the kth smallest element.
   //  Time complexity of this solution is O(k + (n-k)*Logk)

// A Java program to find k'th smallest element using max heap
static class GFG2 {
        // A class for Max Heap
        class MaxHeap {
            int[] harr; // pointer to array of elements in heap
            int capacity; // maximum possible size of max heap
            int heap_size; // Current number of elements in max heap

            int parent(int i) { return (i - 1) / 2; }
            int left(int i) { return (2 * i + 1); }
            int right(int i) { return (2 * i + 2); }
            int getMax() { return harr[0]; } // Returns maximum
            // to replace root with new node x and heapify() new root
            void replaceMax(int x) {
                this.harr[0] = x;
                maxHeapify(0);
            }
            MaxHeap(int a[], int size) {
                heap_size = size;
                harr = a; // store address of array
                int i = (heap_size - 1) / 2;
                while (i >= 0) {
                    maxHeapify(i);
                    i--;
                }
            }

            // Method to remove maximum element (or root) from max heap
            int extractMax() {
                if (heap_size == 0)
                    return Integer.MAX_VALUE;
                // Store the maximum vakue.
                int root = harr[0];
                // If there are more than 1 items, move the last item to root
                // and call heapify.
                if (heap_size > 1) {
                    harr[0] = harr[heap_size - 1];
                    maxHeapify(0);
                }
                heap_size--;
                return root;
            }
            // A recursive method to heapify a subtree with root at given index
            // This method assumes that the subtrees are already heapified
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
        // Function to return k'th largest element in a given array
        int kthSmallest(int arr[], int n, int k) {
            // Build a heap of first k elements: O(k) time
            MaxHeap mh = new MaxHeap(arr, k);
            // Process remaining n-k elements.  If current element is
            // smaller than root, replace root with current element
            for (int i = k; i < n; i++)
                if (arr[i] < mh.getMax())
                    mh.replaceMax(arr[i]);
            // Return root
            return mh.getMax();
        }

        // Driver program to test above methods
        public static void main(String[] args) {
            int arr[] = { 12, 3, 5, 7, 19 };
            int n = arr.length, k = 4;
            GFG2 gfg = new GFG2();
            System.out.print("K'th smallest element is " +
                    gfg.kthSmallest(arr, n, k));
        }
    }

// This code is contributed by Rajput-Ji
 //   Output
   // K'th smallest element is 12
   // Method 5 (QuickSelect)
   // This is an optimization over method 1 if QuickSort is used as a sorting algorithm in first step. In QuickSort, we pick a pivot element, then move the pivot element to its correct position and partition the surrounding array. The idea is, not to do complete quicksort, but stop at the point where pivot itself is k’th smallest element. Also, not to recur for both left and right sides of pivot, but recur for one of them according to the position of pivot. The worst case time complexity of this method is O(n2), but it works in O(n) on average.


// Java code for kth smallest element in an array

    class GFG3 {
        // Standard partition process of QuickSort.
        // It considers the last element as pivot
        // and moves all smaller element to left of
        // it and greater elements to right
        public static int partition(Integer[] arr, int l, int r) {
            int x = arr[r], i = l;
            for (int j = l; j <= r - 1; j++) {
                if (arr[j] <= x) {
                    // Swapping arr[i] and arr[j]
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                }
            }
            // Swapping arr[i] and arr[r]
            int temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
            return i;
        }
        // This function returns k'th smallest element
        // in arr[l..r] using QuickSort based method.
        // ASSUMPTION: ALL ELEMENTS IN ARR[] ARE DISTINCT
        public static int kthSmallest(Integer[] arr, int l, int r, int k) {
            // If k is smaller than number of elements
            // in array
            if (k > 0 && k <= r - l + 1) {
                // Partition the array around last
                // element and get position of pivot
                // element in sorted array
                int pos = partition(arr, l, r);
                // If position is same as k
                if (pos - l == k - 1)
                    return arr[pos];
                // If position is more, recur for
                // left subarray
                if (pos - l > k - 1)
                    return kthSmallest(arr, l, pos - 1, k);
                // Else recur for right subarray
                return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
            }
            // If k is more than number of elements
            // in array
            return Integer.MAX_VALUE;
        }
        // Driver program to test above methods
        public static void main(String[] args) {
            Integer arr[] = new Integer[] { 12, 3, 5, 7, 4, 19, 26 };
            int k = 3;
            System.out.print("K'th smallest element is " + kthSmallest(arr, 0, arr.length - 1, k));
        }
    }

    //Output

    //K'th smallest element is 5
    //Method 6 (Map STL)

    //A map based STL approach is although very much similar to the
    // quickselect and counting sort algorithm but much easier to implement.
    // We can use an ordered map and map each element with it’s frequency.
    // And as we know that an ordered map would store the data in an sorted manner,
    // we keep on adding the frequency of each element till it does not become greater
    // than or equal to k so that we reach the k’th element from the start i.e. the k’th smallest element.
      //      Eg –
    //Array={7,0,25,6,16,17,0}
    //k=3
   // Now in order to get the k’th largest element, we need to add the frequencies till it becomes
    // greater than or equal to 3. It is clear from the above that the frequency of 0 + frequency
    // of 6 will become equal to 3 so the third smallest number in the array will be 6.
    //We can achieve the above using an iterator to traverse the map.


// Java program for the above approach
    class GFG4 {
        static int Kth_smallest(TreeMap<Integer, Integer> m, int k) {
            int freq = 0;
            for (Map.Entry it : m.entrySet()) {
                // adding the frequencies of each element
                freq += (int)it.getValue();
                // if at any point frequency becomes
                // greater than or equal to k then
                // return that element
                if (freq >= k) {
                    return (int)it.getKey();
                }
            }
            return -1; // returning -1 if k>size of the array
            // which is an impossible scenario
        }
        // Driver code
        public static void main(String[] args) {
            int n = 5;
            int k = 2;
            int[] arr = { 12, 3, 5, 7, 19 };
            TreeMap<Integer, Integer> m = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                // mapping every element with
                // it's
                // frequency
                m.put(arr[i], m.getOrDefault(arr[i], 0) + 1);
            }
            int ans = Kth_smallest(m, k);
            System.out.println(
                    "The " + k + "rd smallest element is " + ans);
        }
    }

    //Output
    //The 2rd smallest element is 5
    //There are two more solutions which are better than above discussed ones: One solution is to do randomized version of quickSelect() and other solution is the worst case linear time algorithm (see the following posts).
    //K’th Smallest/Largest Element in Unsorted Array | Set 2 (Expected Linear Time)
    //K’th Smallest/Largest Element in Unsorted Array | Set 3 (Worst Case Linear Time)
}
