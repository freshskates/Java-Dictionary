package Panel;

import Builder.Data;
import Controllers.Controller;
import Controllers.Definition;
import java.util.List;
import java.util.Scanner;


public class Panel {
    private final int error = 1, help = 2, info = 4, prompt = 8;
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
            System.out.printf("----- Definitions: %d\n", Controller.count);
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
            if(!Controller.lookup_table.contains(user_input[0])) {
                status(error);
                continue;
            }
            this.display = Data.valueOf(user_input[0].toLowerCase()).getDefinitions();
            Controller.sort_list(this.display);
            for (int i = 1; i < user_input.length; i++) option(user_input[i], i);
            Controller.print(this.display);
        }
    }

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

    public String[] getInput(){
        Scanner sc = new Scanner(System.in);
        String searchKey = sc.nextLine();
        return searchKey.split(" ");
    }

}
