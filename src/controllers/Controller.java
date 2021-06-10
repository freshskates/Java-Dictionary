package controllers;

import panel.Panel;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    /**
     * Init Variables
     *
     * count: Amount of Definitions, this is set by the Data Enum
     * @see builder.Data constructor for count increment usage
     *
     * lookup_table: HashSet of all the words that have definitions
     * this was made to enhance performance when checking if word exists in enum.
     * @see builder.Data constructor for Hashset word addition
     *
     * Another O(1) Time Complexity approach considered was to use .valueOf('Enum Value'),
     * however it would throw an Exception if value does not exist inside. Surrounding this with
     * a try catch would be considered bad practice for something that can be controlled.
     *
     */
    public static int count;
    public static HashSet<String> lookup_table = new HashSet<>();

    /**
     * With List print out the Part of Speech and Meaning
     * with a '[Part of Speech] Meaning' - format
     * @param definitions Requires a List of Definition's to use
     * @see Panel for usage
     */
    public static void print(List<Definition> definitions) {
        for(Definition x : definitions) System.out.printf("[%s] %s\n", x.pos(), x.meaning());
    }

    /**
     * Remove Duplicates from List of Definitions by comparing elements
     * Used stream().distinct() method to gather unique values
     * Collectors.toList() was used to return a Mutable List
     * @param definitions Requires a List of Definition's to use
     * @return List with only Unique Definitions
     * @see Panel for usage
     */
    public static List<Definition> remove_duplicates(List<Definition> definitions) {
        return definitions.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Filter List of Definitions by comparing Part of Speech
     * Using Lambda Functions introduced in Java 8 to filter
     * Collectors.toList() was used to return a Mutable List
     * @param definitions Requires a List of Definition's to use
     * @param input_pos Part of Speech as a String
     * @return Mutable List filtered with only the Part of Speech passed in
     * @see Panel for usage
     */
    public static List<Definition> filter_list(List<Definition> definitions, String input_pos) {
        return definitions.stream().filter(c -> c.pos().equalsIgnoreCase(input_pos)).collect(Collectors.toList());
    }

    /**
     * Reverse List of Definitions
     * Using Lambda Functions introduced in Java 8 to reverse
     * Mutates List inside function, therefore no return value
     * @param definitions Requires a List of Definition's to use
     * @see Panel for usage
     */
    public static void reverse_list(List<Definition> definitions) {
        Collections.reverse(definitions);
    }

    /**
     * Sort List of Definitions
     * Using Lambda Functions introduced in Java 8 to sort
     * Mutates List inside function, therefore no return value
     * @param definitions Requires a List of Definition's to use
     * @see Panel for usage
     */
    public static void sort_list(List<Definition> definitions) {
        definitions.sort(Comparator.comparing(Definition::meaning));
    }

}
