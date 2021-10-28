import java.util.*;
import java.security.*;
import java.io.*;

public class MyHashSet {
    public hashCode(String s) {
        int tableSize = 262127;
        hashCode(s) = Math.abs(s.hashCode()) % tableSize;
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
}
