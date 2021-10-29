import java.util.*;
import java.security.*;
import java.io.*;

public class MyHashSet {

    public static class HTObject {
        public String key;
        public Integer value;
    }

    public static final int tableSize = 262127;
    private LinkedList<HTObject>[] table = new LinkedList[tableSize];

    public void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\Users\\Nate\\IdeaProjects\\COMP272\\src\\EnglishWordList.txt"));
        Set<String> H = new HashSet<String>();
        while (input.hasNextLine())
            H.add(input.nextLine().trim());
        input.close();
        for (String str : H)
            System.out.println(str + " ");
    }

    public void HashTable() {
        for (int i = 0; i < tableSize; i++) {
            table[i] = null;
        }
    }

    private HTObject getObj(String key) {
        if (key == null)
            return null;
        int index = key.hashCode() % tableSize;
        LinkedList<HTObject> items = table[index];
        if (items == null)
            return null;
        for (HTObject item : items) {
            if (item.key.equals(key))
                return item;
        }
        return null;
    }
    
    // https://www.cpp.edu/~ftang/courses/CS240/lectures/hashing.htm

    public int hashCode(Object key) {
        H(s) = hash(key) % tableSize;
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




