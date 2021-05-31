package Factory;

import Handler.Definition;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Words {

    public static int count;
    public static HashSet<String> lookup_table = new HashSet<>();

    public static void print(List<Definition> definitions) {
        for(Definition x : definitions) System.out.printf("[%s] %s\n", x.pos(), x.meaning());
    }

    public enum Data {

        book(Arrays.asList(
                new Definition("noun", "A set of pages."),
                new Definition("noun", "A set of pages."),
                new Definition("verb", "To arrange for someone to have a seat on a plane."),
                new Definition("verb", "To arrange something on a particular date.")
        )),

        arrow(Arrays.asList(new Definition("noun", "Here is one arrow: <IMG> -=>> </IMG>"))),

        distinct(Arrays.asList(
                new Definition("adjective", "Familiar. Worked in Java."),
                new Definition("adjective", "Unique. No duplicated. Clearly different or of a different kind."),
                new Definition("adverb", "Uniquely. Written \"distinctly\"."),
                new Definition("noun", "A keyword in this assignment."),
                new Definition("noun", "A keyword in this assignment."),
                new Definition("noun", "A keyword in this assignment."),
                new Definition("noun", "An advanced search option."),
                new Definition("noun", "Distinct is a parameter in this assignment.")
        )),

        placeholder(Arrays.asList(
                new Definition("adjective", "To be updated..."),
                new Definition("adjective", "To be updated..."),
                new Definition("adverb", "To be updated..."),
                new Definition("conjunction", "To be updated..."),
                new Definition("interjection", "To be updated..."),
                new Definition("noun", "To be updated..."),
                new Definition("noun", "To be updated..."),
                new Definition("noun", "To be updated..."),
                new Definition("preposition", "To be updated..."),
                new Definition("pronoun", "To be updated..."),
                new Definition("verb", "To be updated...")
        )),

        reverse(Arrays.asList(
                new Definition("adjective", "On back side."),
                new Definition("adjective", "Opposite to usual or previous arrangement."),
                new Definition("noun", "A dictionary program's parameter."),
                new Definition("noun", "Change to opposite direction."),
                new Definition("noun", "The opposite."),
                new Definition("noun", "To be updated..."),
                new Definition("noun", "To be updated..."),
                new Definition("noun", "To be updated..."),
                new Definition("noun", "To be updated..."),
                new Definition("verb", "Change something to opposite."),
                new Definition("verb", "Go back"),
                new Definition("verb", "Revoke ruling."),
                new Definition("verb", "To be updated..."),
                new Definition("verb", "To be updated..."),
                new Definition("verb", "Turn something inside out.")
        ));

        private final List<Definition> definitions;

        Data(List<Definition> definitions) {
            lookup_table.add(this.name());
            count += definitions.size();
            this.definitions = definitions;
        }

        public List<Definition> getDefinitions() {
            return this.definitions;
        }

    }
}
