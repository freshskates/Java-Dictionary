package builder;

import controllers.Definition;

import java.util.Arrays;
import java.util.List;
import static controllers.Controller.lookup_table;
import static controllers.Controller.count;
public enum Data {
    /**
     * This Enum is used as the initial data storage, not optimal for client usage
     * because to add more words, you would need access to source code
     *
     * Enum acts like an Unordered-Hashmap, therefore no need for hashmaps to be created in future
     * Word Retrieval: O(1) Time Complexity
     *
     * @see controllers.Controller for Enum Data Handler
     * @see panel.Panel for Data usage
     */

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
    )),

    robert(Arrays.asList(
            new Definition("noun", "my name is robert."),
            new Definition("noun", "To be updated...")
    )),

    iphone(Arrays.asList(
            new Definition("noun", "iPhone is an apple product."),
            new Definition("noun", "To be updated...")
    )),

    discord(Arrays.asList(
            new Definition("noun", "To be updated..."),
            new Definition("noun", "To be updated...")
    )),

    skype(Arrays.asList(
            new Definition("noun", "To be updated..."),
            new Definition("noun", "To be updated...")
    )),
    run(Arrays.asList(
            new Definition("noun", "To be updated...")
    )),
    make(Arrays.asList(
            new Definition("noun", "To be updated...")
    )),
    set(Arrays.asList(
            new Definition("noun", "To be updated...")
    )),
    apple(Arrays.asList(
            new Definition("noun", "To be updated...")
    )),
    pear(Arrays.asList(
            new Definition("noun", "To be updated...")
    )),
    brown(Arrays.asList(
            new Definition("noun", "To be updated...")
    )),
    crayons(Arrays.asList(
            new Definition("noun", "To be updated..."),
            new Definition("noun", "To be updated...")
    )),
    tacos(Arrays.asList(
            new Definition("noun", "To be updated..."),
            new Definition("noun", "To be updated...")
    )),
    covid(Arrays.asList(
            new Definition("noun", "To be updated..."),
            new Definition("noun", "To be updated...")
    )),
    fish(Arrays.asList(
            new Definition("noun", "To be updated..."),
            new Definition("noun", "To be updated...")
    ));

    private final List<Definition> definitions;

    /**
     * Constructor inside Enum that sets an array of Definition's
     * @see this.definitions
     * @param definitions List of Definitions, storing a Part of Speech and a Meaning
     */
    Data(List<Definition> definitions) {
        lookup_table.add(this.name());
        count += definitions.size();
        this.definitions = definitions;
    }

    /**
     * Getter for this.definitions
     * @return Mutable List of Definitions
     */
    public List<Definition> getDefinitions() {
        return this.definitions;
    }

}
