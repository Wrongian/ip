public class Demacia {

    private final Terminal terminal;

    public Demacia() {
        this.terminal = new Terminal();
    }
    public void greet() {
        this.terminal.printHorizontal();
        this.terminal.output("Hello I am Demacia, a chatbot");
        this.terminal.output("Type what you desire");
        this.terminal.printHorizontal();
    }

    public void start() {
        // get messages from user
        while (true) {
            String msg = this.terminal.input();
            switch (msg) {
                case "bye":
                    this.exit();
                default:
                    // echo by default for now
                    this.terminal.printHorizontal();
                    this.terminal.output(msg);
                    this.terminal.printHorizontal();
            }
        }

    }

    public void exit() {
        this.terminal.printHorizontal();
        this.terminal.output("Buybye, see ya later...");
        this.terminal.printHorizontal();
        System.exit(0);
    }

    public static void main(String[] args) {
        Demacia bot = new Demacia();
        bot.greet();
        bot.start();
    }
}
