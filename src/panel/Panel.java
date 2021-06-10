package panel;

import builder.Data;
import controllers.Controller;
import controllers.Definition;
import java.util.List;
import java.util.Scanner;


public class Panel {
    /**
     * Init Variables
     *
     * int error:  1
     * int help:   2
     * int info:   4
     * int prompt: 8
     * These are for the bitwise operations used inside Status
     *
     * String[] options: look up table for all the options available
     * List of Definition - display: List currently being handled will be stored in this.display
     * @see Data for data that Panel Class will be handling
     *
     */
    private int count = 0;
    private final int error = 1, help = 2, info = 4, prompt = 8;
    private final int[] used = {0, 0};
    private final String[] options = {"!q", "!help", "distinct", "reverse", "noun", "verb", "adverb", "adjective", "pronoun", "preposition", "conjunction", "interjection"};
    private List<Definition> display;

    /**
     * notFound is used when user input was not recognized
     * Prints out error message to user
     * @param index will be the n_th phrase that is considered as the user's input
     * @param option users input that was not recognized
     */
    public void notFound(int index, String option) {
        final String[] error_status = {"is NOT a part of speech", "is NOT a 'distinct'", "was disregarded" };
        for (String errorStatus : error_status)
            System.out.printf("\t|\t<parameter: %d - %s %s.>\n", index , option, errorStatus);

        System.out.printf("\t<parameter: %d - should be a part of speech or 'distinct' / 'reverse'.>\t|\n", index);
    }

    public void show() {
        System.out.println("|");
        Controller.print(this.display);
        System.out.println("|");
    }

    /**
     * Status is used to print out error, help, info and prompt messages to user
     * @param flag Integer that will be bit masked to see what options were selected
     * @apiNote Error: 1
     * @apiNote Help: 2
     * @apiNote Info: 4
     * @apiNote Prompt: 8
     * Example: To print Help then a prompt, you need to 'or' the values
     * status(2 | 8) or status(help | prompt), both will print help menu then prompt users input
     */
    public void status(int flag) {

        if((flag & error) > 0) {
            System.out.println("|\n\t<Not found> To be considered for the next release. Thank you.\n|\n");
        }

        if((flag & help) > 0) {
        System.out.print("|\n\tPARAMETER HOW-T0, please enter:\n");
        System.out.print("\t1. A search key -then 2. An optional part of speech -then\n");
        System.out.print("\t3. An optional 'distinct' -then 4. An optional 'reverse'\n|\n");
        }

        if((flag & info) > 0) {
            System.out.println("! Loading data...");
            System.out.println("! Loading completed...\n");
            System.out.println("===== DICTIONARY 340 JAVA =====");
            System.out.printf("----- Keywords: %d\n", Data.values().length);
            System.out.printf("----- Definitions: %d\n", Controller.count);
        }

        if((flag & prompt) > 0) {
            System.out.printf("Search [%d]: ", this.count);
        }
    }

    /**
     * Start will start a loop awaiting users input
     * Any: {Part of Speech} / {Distinct} / {Reverse}
     * Order of 'Any' does not matter.
     * @apiNote Users input should consist of {Word} {Any} {Any} {Any}
     * Loop will end when !q is entered by user
     */
    public void start() {
        status(info);
        while(true) {
            ++this.count;
            status(prompt);
            Scanner sc = new Scanner(System.in);
            String searchKey = sc.nextLine();
            String[] user_input = searchKey.split(" ");

            if(user_input[0].length() == 0) {
                status(help);
                break;
            }

            if (user_input.length > 4 || user_input[0].equalsIgnoreCase(options[0])) break;
            if(user_input[0].equalsIgnoreCase(options[1])) {
                status(help);
                continue;
            }
            if(!Controller.lookup_table.contains(user_input[0].toLowerCase())) {
                status(error);
                continue;
            }

            this.display = Data.valueOf(user_input[0].toLowerCase()).getDefinitions();
            Controller.sort_list(this.display);
            for (int i = 1; i < user_input.length; i++) option(user_input[i], i);
            show();

        }
    }

    /**
     * Option will validate the options other than the word
     * valid options consist of {Part of Speech}, {Distinct}, {Reverse}
     *
     * @apiNote If option not found, it will call nofFound method
     */
    public void option(String check, int index) {
        if(check.equals(options[2])) {
            this.display = Controller.remove_duplicates(this.display);
            return;
        }
        else if(check.equals(options[3])) {
            Controller.reverse_list(this.display);
            return;
        }
        for(int i = 2; i < options.length; i++)
            if(check.equals(options[i])) {
                this.display = Controller.filter_list(this.display, options[i]);
                return;
            }
        notFound(index, check);
    }

}
