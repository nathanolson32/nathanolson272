import java.util.*;
import java.security.*;
import java.io.*;

public class MyHashSet {
    public hashCode(String s) {
        int tableSize = 262127;
        return hashCode(s) = Math.abs(s.hashCode()) % tableSize;
        ArrayList<LinkedList>
    }

    static int hashCode(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = (h << 5) | (h >>> 27); // 5-bit cyclic shift of the running sum
            h += (int) s.charAt(i); // add in next character
        }
        return h;
    }

    public int hashCode1(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    public int hashCode2(String s) {
        byte[] sb=s.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] key=md.digest(sb);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BitSet bs = BitSet.valueOf(key);
        // using the BitSet bs, you will extract 18 bits based on the
        // get() method of BitSet. The 18 bits are extracted at the first
        // 18 prime numbers 2, 7,17,29,41,53,67,79,97,107,127,139,157,
        // 173,191, 199,227,239. Put the bits together in that order to
        // form an integer and return it. That will be the hash value of
        // the key that you can use in the table of size 2^18.
        return 0;
    }




















// Java program to demonstrate implementation of our
// own hash table with chaining for collision detection
    // A node of chains
    class HashNode<K, V> {
        K key;
        V value;
        final int hashCode;

        // Reference to next node
        HashNode<K, V> next;

        // Constructor
        public HashNode(K key, V value, int hashCode)
        {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    // Class to represent entire hash table
    class Map<K, V> {
        // bucketArray is used to store array of chains
        private ArrayList<HashNode<K, V> > bucketArray;

        // Current capacity of array list
        private int numBuckets;

        // Current size of array list
        private int size;

        // Constructor (Initializes capacity, size and
        // empty chains.
        public Map()
        {
            bucketArray = new ArrayList<>();
            numBuckets = 10;
            size = 0;

            // Create empty chains
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
        }

        public int size() { return size; }
        public boolean isEmpty() { return size() == 0; }

        private final int hashCode (K key) {
            return Objects.hashCode(key);
        }

        // This implements hash function to find index
        // for a key
        private int getBucketIndex(K key)
        {
            int hashCode = hashCode(key);
            int index = hashCode % numBuckets;
            // key.hashCode() coule be negative.
            index = index < 0 ? index * -1 : index;
            return index;
        }

        // Method to remove a given key
        public V remove(K key)
        {
            // Apply hash function to find index for given key
            int bucketIndex = getBucketIndex(key);
            int hashCode = hashCode(key);
            // Get head of chain
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            // Search for key in its chain
            HashNode<K, V> prev = null;
            while (head != null) {
                // If Key found
                if (head.key.equals(key) && hashCode == head.hashCode)
                    break;

                // Else keep moving in chain
                prev = head;
                head = head.next;
            }

            // If key was not there
            if (head == null)
                return null;

            // Reduce size
            size--;

            // Remove key
            if (prev != null)
                prev.next = head.next;
            else
                bucketArray.set(bucketIndex, head.next);

            return head.value;
        }

        // Returns value for a key
        public V get(K key)
        {
            // Find head of chain for given key
            int bucketIndex = getBucketIndex(key);
            int hashCode = hashCode(key);

            HashNode<K, V> head = bucketArray.get(bucketIndex);

            // Search key in chain
            while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode)
                    return head.value;
                head = head.next;
            }

            // If key not found
            return null;
        }

        // Adds a key value pair to hash
        public void add(K key, V value)
        {
            // Find head of chain for given key
            int bucketIndex = getBucketIndex(key);
            int hashCode = hashCode(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            // Check if key is already present
            while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            // Insert key in chain
            size++;
            head = bucketArray.get(bucketIndex);
            HashNode<K, V> newNode
                    = new HashNode<K, V>(key, value, hashCode);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);

            // If load factor goes beyond threshold, then
            // double hash table size
            if ((1.0 * size) / numBuckets >= 0.7) {
                ArrayList<HashNode<K, V> > temp = bucketArray;
                bucketArray = new ArrayList<>();
                numBuckets = 2 * numBuckets;
                size = 0;
                for (int i = 0; i < numBuckets; i++)
                    bucketArray.add(null);

                for (HashNode<K, V> headNode : temp) {
                    while (headNode != null) {
                        add(headNode.key, headNode.value);
                        headNode = headNode.next;
                    }
                }
            }
        }

        // Driver method to test Map class
        public static void main(String[] args)
        {
            Map<String, Integer> map = new Map<>();
            map.add("this", 1);
            map.add("coder", 2);
            map.add("this", 4);
            map.add("hi", 5);
            System.out.println(map.size());
            System.out.println(map.remove("this"));
            System.out.println(map.remove("this"));
            System.out.println(map.size());
            System.out.println(map.isEmpty());
        }
    }


}
