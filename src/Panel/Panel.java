package Panel;

import Factory.Words.Data;
import Factory.Words;
import Handler.Definition;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Panel {
    int error = 1, help = 2, info = 4, prompt = 8;
    private final String[] options = {"!q", "!help", "distinct", "reverse", "noun", "verb", "adverb", "adjective", "pronoun", "preposition", "conjunction", "interjection"};
    private List<Definition> display;

    public void notFound(int index, String option) {
        final String[] error_status = {"is NOT a part of speech", "is NOT a 'distinct'", "was disregarded" };
        for (String errorStatus : error_status)
            System.out.printf("\t|\t<parameter: %d - %s %s.>\n", index , option, errorStatus);

        System.out.printf("\t<parameter: %d - should be a part of speech or 'distinct' / 'reverse'.>\t|\n", index);
    }

    public void status(int flag) {

        if((flag & error) > 0) {
            System.out.println("\t<Not found> To be considered for the next release. Thank you.");
        }

        if((flag & help) > 0) {
        System.out.println("\tPARAMETER HOW-T0, please enter:");
        System.out.println("\t1. A search key -then 2. An optional part of speech -then");
        System.out.println("\t3. An optional 'distinct' -then 4. An optional 'reverse'");
        }

        if((flag & info) > 0) {
            System.out.println("! Loading data...");
            System.out.println("! Loading completed...\n");
            System.out.println("===== DICTIONARY 340 JAVA =====");
            System.out.printf("----- Keywords: %d\n", Data.values().length);
            System.out.printf("----- Definitions: %d\n", Words.count);
        }

        if((flag & prompt) > 0) {
            System.out.println("Enter a word to search: ");
        }
    }

    public void start() {
        status(info);
        while(true){
            status(prompt);
            String[] user_input = getInput();
            if (user_input.length <= 0  || user_input.length > 4 || user_input[0].equalsIgnoreCase(options[0])) break;
            if(user_input[0].equalsIgnoreCase(options[1])) status(help);
            if(!Words.lookup_table.contains(user_input[0])) {
                status(error);
                continue;
            }
            this.display = Data.valueOf(user_input[0].toLowerCase()).getDefinitions();
            this.sort_list();
            for (int i = 1; i < user_input.length; i++) option(user_input[i], i);
            Words.print(this.display);
        }
    }

    public void option(String check, int index) {
        if(check.equals(options[2])) {
            remove_duplicates();
            return;
        }
        else if(check.equals(options[3])) {
            reverse_list();
            return;
        }
        for(int i = 2; i < options.length; i++)
            if(check.equals(options[i])) {
                filter_list(options[i]);
                return;
            }
        notFound(index, check);
    }

    public void remove_duplicates() {
        this.display = this.display.stream().distinct().collect(Collectors.toList());
    }

    public void filter_list(String input_pos) {
        this.display = this.display.stream().filter(c -> c.pos().equalsIgnoreCase(input_pos)).collect(Collectors.toList());
    }

    public void reverse_list() {
        Collections.reverse(this.display);
    }

    public void sort_list() {
        this.display.sort(Comparator.comparing(Definition::meaning));
    }

    public String[] getInput(){
        Scanner sc = new Scanner(System.in);
        String searchKey = sc.nextLine();
        return searchKey.split(" ");
    }

}
