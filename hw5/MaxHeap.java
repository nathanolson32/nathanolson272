
import java.util.*;
public class MaxHeap<E extends Comparable<E>> extends ArrayList<E>   {

    private ArrayList<E> heap;

    // construct an empty Heap using ArrayList
    // with root at index 0.
    // don't use binary tree for implementing the heap.
     public MaxHeap(){
         heap = new ArrayList<E>();
    }

    // returns max value
    public E findMax() {
        E max;
        if (heap.size() <= 0)
            return null;
        else {
            int i;
            max = heap.get(0);
            for (i = 1; i < heap.size(); i++)
                if (heap.get(i) > max)
                    max = heap.get(i);
        }
        return max;
    }

    // adds a new value to the heap at the end of the Heap and
    // adjusts values up to the root to ensure Max heap property is satisfied.
    // parent of node at i is given by the formula (i-1)/2
    // throw appropriate exception
    public void addHeap(E val) {
        heap.add(val);
        int addnew = heap.size()-1;

        while (addnew > 0 && heap.get(addnew).compareTo(heap.get(parent(addnew))) < 0) {
            swap(heap, addnew, parent(addnew));
            addnew = parent(addnew);
        }
     }
     private static <E> void swap(ArrayList<E> a, int i, int j) {
         E t = a.get(i);
         a.set(i, a.get(j));
         a.set(j, t);
     }

    private static int parent(int i) {
         return (i-1)/2;
    }
    private void adjustDown() {
        int k = 0;
        int left = 1;

        while(left < heap.size()) {
            int max = left;
            int right = left + 1;
            if (right < heap.size()) {
                if(heap.get(right).compareTo(heap.get(left)) > 0) {
                    max = right;
                }
            }
            E parent = heap.get(k);
            E child = heap.get(max);
            if(parent.compareTo(child) < 0) {
                heap.set(k, child);
                heap.set(max, parent);
                k = max;
                left = 2*k+1;
            } else {
                break;
            }
        }
    }
    //returns the max value at the root of the heap by swapping the last value
    // and percolating the value down from the root to preserve max heap property
    // children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    // bounds of the Heap index, namely, 0 ... size()-1.
    // throw appropriate exception
    public E removeHeap()  throws NoSuchElementException{
        if (heap.size() ==0) {
            throw new NoSuchElementException();
        } else if (heap.size() ==1) {
            return heap.remove(0);
        }
        E tmp = heap.get(0);
        heap.set(0, heap.remove(heap.size()-1));
        adjustDown();
        return tmp;
    }

    // takes a list of items E and builds the heap and then prints
    // decreasing values of E with calls to removeHeap().
    public void heapSort(List<E> list){
        int n = list.size();
        for (int i = n /2 -1; i >=0 ; i--)
            addHeap((E) list);
        for (int i = n-1; i > 0; i--) {
            int tmp = list[0];
            list.set(0, list.get(i));
            list.set(i, tmp);
            addHeap((E) list);
        }
    }

    // merges the other maxheap with this maxheap to produce a new maxHeap.
    public void heapMerge(MaxHeap<E> other){
        PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
        int total=0;
        for (int i = 0; i < other.size(); i++) {
            queue.add(new ArrayContainer(other[i], 0));
            total =total + other[i].length;
        }
        int m=0;
        int result[] = new int[total];

        while (!queue.isEmpty()) {
            ArrayContainer ac = queue.poll();
            result[m++]=ac.other[ac.index];

            if (ac.index < ac.other.size()-1) {
                queue.add(new ArrayContainer(ac.other, ac.index+1));
            }
        }
    }

    //takes a list of items E and builds the heap by calls to addHeap(..)
    public void buildHeap(List<E> list) {
        int n =list.size();
        for (int i = 0; i < n; ++i)
            System.out.print(list.get(i) + " ");
        System.out.println();
    }
    static void buildHeap(int arr[], int n) {
        // Index of last non-leaf node
        int startIdx = (n / 2) - 1;

        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int i = startIdx; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }
}
