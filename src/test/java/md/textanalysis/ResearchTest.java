package md.textanalysis;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ResearchTest {
    @Test
    void getDelta() throws Exception {
        List<String> list1 = Files.readAllLines(Paths.get(
                "/home/dima/WORK/Personal/Work/java/mydict/research/ends/mydict_ly_11118.txt"), StandardCharsets.UTF_8);
        List<String> list2 = Files.readAllLines(Paths.get(
                "/home/dima/WORK/Personal/Work/java/mydict/research/ends/mydict_11367.txt"), StandardCharsets.UTF_8);
        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);

        set1.removeAll(list2);
        set2.removeAll(list1);

        System.out.println("set1-set2");
        System.out.println("---------");
        for (String s : new TreeSet<>(set1)) System.out.println(s);
        System.out.println(" ");

        System.out.println("set2-set1");
        System.out.println("---------");
        for (String s : new TreeSet<>(set2)) System.out.println(s);
    }
}
