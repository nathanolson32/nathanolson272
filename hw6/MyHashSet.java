import java.util.*;
import java.security.*;
import java.io.*;

public class MyHashSet {

    static class HashNode<K, V> {
        K key;
        V value;
        final int hashCode;
        HashNode<K, V> next;

        public HashNode(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    static class Map<K, V> {
        private ArrayList<HashNode<K, V>> bucketArray;
        private int tableSize;
        private int size;

        public Map() {
            bucketArray = new ArrayList<>();
            tableSize = 262127;
            size = 0;
            for (int i = 0; i < tableSize; i++)
                bucketArray.add(null);
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        private final int hashCode(K key) {
            return Objects.hashCode(key);
        }


        private int getBucketIndex(K key) {
            int hashCode = hashCode(key);
            int index = hashCode % tableSize;
            index = index < 0 ? index * -1 : index;
            return index;
        }

        public V get(K key) {
            int bucketIndex = getBucketIndex(key);
            int hashCode = hashCode(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode)
                    return head.value;
                head = head.next;
            }
            return null;
        }

        public void add(K key, V value) {
            int bucketIndex = getBucketIndex(key);
            int hashCode = hashCode(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }
            size++;
            head = bucketArray.get(bucketIndex);
            HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);
            if ((1.0 * size) / tableSize >= 0.7) {
                ArrayList<HashNode<K, V>> temp = bucketArray;
                bucketArray = new ArrayList<>();
                tableSize = 2 * tableSize;
                size = 0;
                for (int i = 0; i < tableSize; i++)
                    bucketArray.add(null);
                for (HashNode<K, V> headNode : temp) {
                    while (headNode != null) {
                        add(headNode.key, headNode.value);
                        headNode = headNode.next;
                    }
                }
            }
        }

        public static void main(String[] args) throws FileNotFoundException {
            // Scanner input = new Scanner(new File("C:\\Users\\Nate\\IdeaProjects\\COMP272\\src\\EnglishWordList.txt"));
            Map<String, Integer> map = new Map<>();
            // while (input.hasNextLine())
            //   map.add(input.nextLine());
            // input.close();
            // for (String str : map)
            // System.out.println(str + " ");
            map.add("this", 1);
            map.add("coder", 2);
            map.add("this", 4);
            map.add("hi", 5); // this is how to add the word list kind of
            System.out.println(map.size());
            System.out.println();
        }
        // This prints a list with no duplicates, but I am not sure how to make it print out a file.
        // The comments are scanner to input file.
    }

    public int hashCode(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public void hashCode(String s) {
        byte[] sb = s.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] key = md.digest(sb);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BitSet bs = BitSet.valueOf(key);
    }
}
