
import java.util.*;
public class MaxHeap<E extends Comparable<E>> extends ArrayList<E>   {

    private ArrayList<E> heap;

    // construct an empty Heap using ArrayList
    // with root at index 0.
    // don't use binary tree for implementing the heap.
     public MaxHeap(){
         heap = new ArrayList<E>();
    } // MaxHeap

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
    }// findMax

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
     }// addHeap

    //returns the max value at the root of the heap by swapping the last value
    // and percolating the value down from the root to preserve max heap property
    // children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    // bounds of the Heap index, namely, 0 ... size()-1.
    // throw appropriate exception
    public E removeHeap()  throws NoSuchElementException{
        if (heap.size() ==0)
            throw new NoSuchElementException();

        else if (heap.size() ==1)
            return heap.remove(0);

        E tmp = heap.get(0);
        heap.set(0, heap.remove(heap.size()-1));
        adjustDown();
        return tmp;
    } //removeHeap

    // takes a list of items E and builds the heap and then prints
    // decreasing values of E with calls to removeHeap().
    public void heapSort(List<E> list){
        int n = list.size();
        for (int i = n /2 -1; i >=0 ; i--)
            addHeap((E) list);
        for (int i = n-1; i > 0; i--) {
            int tmp = (int) list.get(0);
            list.set(0, list.get(i));
            list.set(i, tmp);
            addHeap((E) list);
        }
    } //heapSort

    // merges the other maxheap with this maxheap to produce a new maxHeap.
    public void heapMerge(MaxHeap<E> other){
        int resultHeapSize = heap.size() + other.size();
        int[] resultMaxHeap = new int[resultHeapSize];

        for (int i = 0; i < heap.size(); i++)
            resultMaxHeap[i] = (int) heap.get(i);

        for (int i = 0; i < other.size(); i++)
            resultMaxHeap[heap.size() + i] = (int) other.get(i);

        // Builds a max heap of given arr[0..n-1]
        for (int i = resultHeapSize / 2 - 1; i >= 0; i--)
            maxHeapify(resultMaxHeap, resultHeapSize, i);
    } //heapMerge

    //takes a list of items E and builds the heap by calls to addHeap
    public void buildHeap(List<E> list) {
        int n =list.size();
        for (int i = 0; i < n; ++i)
            System.out.print(list.get(i) + " ");
        System.out.println();
    } //buildHeap







    private static void maxHeapify(int[] arr, int n, int i) {
        if (i >= n)
            return;
        int left=i*2+1;
        int right=i*2+2;
        int max;
        if (left<n && arr[left]>arr[i])
            max=left;
        else
            max=i;
        if (right<n && arr[right]>arr[max])
            max = right;
     } // maxheapify

    private static <E> void swap(ArrayList<E> a, int i, int j) {
        E t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    } //swap

    private static int parent(int i) {
        return (i-1)/2;
    } // parent

    private void adjustDown() {
        int k = 0;
        int left = 1;
        while (left < heap.size()) {
            int max = left;
            int right = left + 1;
            if (right < heap.size()) {
                if (heap.get(right).compareTo(heap.get(left)) > 0)
                    max = right;
            }
            E parent = heap.get(k);
            E child = heap.get(max);
            if (parent.compareTo(child) < 0) {
                heap.set(k, child);
                heap.set(max, parent);
                k = max;
                left = 2*k+1;
            } else
                break;
        }
    } // adjustDown
} // class MaxHeap
