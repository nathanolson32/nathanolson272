import java.util.*;
import java.security.*;
import java.io.*;

public class MyHashSet {


    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<T>(set);
    }

    // https://www.cpp.edu/~ftang/courses/CS240/lectures/hashing.htm


    public int hashCode(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\Users\\Nate\\IdeaProjects\\COMP272\\src\\EnglishWordList.txt"));
        Set<String> ENGLAND = new HashSet<String>();
        while (input.hasNextLine())
            ENGLAND.add(input.next().trim());
        input.close();
        for (String str : ENGLAND)
            System.out.println(str + " ");
    }
}
