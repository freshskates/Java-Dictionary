package panel;

import builder.Data;
import controllers.Controller;
import controllers.Definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Panel {
    /**
     * Init Variables
     *
     * int notFound: 1 0x00000001
     * int help:     2 0x00000010
     * int info:     4 0x00000100
     * int prompt:   8 0x00001000
     * int exit:    16 0x00010000
     * These are for the bitwise operations used inside Status
     *
     * String[] options: look up table for all the options available
     * List of Definition - display: List currently being handled will be stored in this.display
     * @see Data for data that Panel Class will be handling
     *
     */
    private int count = 0;
    private final int notFound = 1,
            help = 2,
            info = 4,
            prompt = 8,
            exit = 16;
    private final String[] partOfSpeeches = {"noun", "verb", "adverb", "adjective", "pronoun", "preposition", "conjunction", "interjection"};
    private final String[] options = {"!q", "!help", "distinct", "reverse"};
    private List<Definition> display;

    /**
     * Start will start a loop awaiting users input
     * Part of speech only 2nd parameter
     * Distinct up to 3rd parameter
     * Reverse up to 4th parameter
     * @apiNote Users input should consist of {Word} {{Part of Speech}} {Distinct} {Reverse}
     * !q for graceful exit
     * !help for help
     */
    public void start() {
        status(info);
        Scanner sc = new Scanner(System.in);

        while(true) {
            ++this.count;
            status(prompt);
            String searchKey = sc.nextLine().toLowerCase();
            String[] user_input = searchKey.split(" ");

            if(user_input[0].length() == 0 || user_input.length > 4 || user_input[0].equalsIgnoreCase(options[1])) {
                status(help);
                continue;
            }

            if (user_input[0].equalsIgnoreCase(options[0])) break;

            if(!Controller.lookup_table.contains(user_input[0])) {
                status(notFound);
                continue;
            }
            this.display = new ArrayList<>(Data.valueOf(user_input[0]).getDefinitions());
            for (int i = 1; i < user_input.length; i++) option(user_input[i], i);
            show(Data.valueOf(user_input[0]).name());
        }
        status(exit);
    }

    /**
     * Option will validate the options other than the word
     * valid options consist of {Part of Speech}, {Distinct}, {Reverse}
     *
     * @apiNote If option not found, it will call nofFound method
     */
    private void option(String check, int index) {
        for(int i = 0; i < partOfSpeeches.length && index == 1; i++)
            if(check.equals(partOfSpeeches[i])) {
                this.display = Controller.filterList(this.display, partOfSpeeches[i]);
                return;
            }

        if(check.equals(options[2]) && index < 3) {
            this.display = Controller.removeDuplicates(this.display);
            return;
        }

        if(check.equals(options[3])) {
            Controller.reverseList(this.display);
            return;
        }

        error(index, check);
    }

    /**
     * Status is used to print out error, help, info and prompt messages to user
     * @param flag Integer that will be bit masked to see what options were selected
     * @apiNote notFound:  1     0x00000001
     * @apiNote Help:      2     0x00000010
     * @apiNote Info:      4     0x00000100
     * @apiNote Prompt:    8     0x00001000
     * @apiNote exit:     16     0x00010000
     * Bitmask: Popular approach in many big libraries to increase flexibility
     * Example: To print Help then a prompt, you need to 'or' the values
     * status(2 | 8) or status(help | prompt), both will print help menu then prompt users input
     */
    private void status(int flag) {

        if((flag & notFound) > 0) {
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

        if((flag & exit) > 0) {
            System.out.println("-----THANK YOU-----");
        }
    }

    /**
     * notFound is used when user input was not recognized
     * Prints out error message to user
     * @param index will be the n_th phrase that is considered as the user's input
     * @param option users input that was not recognized
     */
    private void error(int index, String option) {
        final String[] opts = {"a part of speech", "'distinct'", "'reverse'"};
        final String[] suffix = {"nd", "rd", "th"};
        final String param = (index + 1) + suffix[index - 1];
        System.out.println("\t|");

        for (int i = index - 1; i < opts.length; i++)
            System.out.printf("\t<The entered %s parameter '%s' is NOT %s.>\n", param , option, opts[i]);
        System.out.printf("\t<The entered %s parameter '%s' was disregarded.>\n", param , option);
        System.out.printf("\t<The %s parameter should be %s.>\n\t|\n", param, String.join(" or ", Arrays.copyOfRange(opts, index - 1, opts.length)));
    }

    private void show(String word) {
        System.out.println("\t|");
        Controller.print(this.display, word);
        System.out.println("\t|");
    }

}
