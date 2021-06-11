import panel.Panel;

public class Main {
    /**
     * @author Robert Cacho Ruiz
     *
     * Main class only uses the Panel Class,
     * Panel Class has access to Data and Handles it accordingly
     * @see builder.Data for all Data used
     * @see Panel : acts as the Secondary Entry Point
     */
    public static void main(String[] args) {
        Panel panel = new Panel();
        panel.start();
    }

}
