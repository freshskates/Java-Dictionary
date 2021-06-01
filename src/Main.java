import Panel.Panel;

public class Main {
    /**
     * Main class only uses the Panel Class,
     * Panel Class has access to Data and Handles it accordingly
     * @see Panel acts as the Secondary Entry Point
     */
    public static void main(String[] args) {
        Panel panel = new Panel();
        panel.start();
    }

}
