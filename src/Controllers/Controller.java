package Controllers;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    public static int count;
    public static HashSet<String> lookup_table = new HashSet<>();

    public static void print(List<Definition> definitions) {
        for(Definition x : definitions) System.out.printf("[%s] %s\n", x.pos(), x.meaning());
    }

    public static List<Definition> remove_duplicates(List<Definition> definitions) {
        return definitions.stream().distinct().collect(Collectors.toList());
    }

    public static List<Definition> filter_list(List<Definition> definitions, String input_pos) {
        return definitions.stream().filter(c -> c.pos().equalsIgnoreCase(input_pos)).collect(Collectors.toList());
    }

    public static void reverse_list(List<Definition> definitions) {
        Collections.reverse(definitions);
    }

    public static void sort_list(List<Definition> definitions) {
        definitions.sort(Comparator.comparing(Definition::meaning));
    }

}
