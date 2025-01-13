public class Terminal {

    public void greet() {
        printHorizontal();
        System.out.println("Hello I am Demacia, a chatbot");
        System.out.println("Type what you desire");
    }

    public void exit() {
        printHorizontal();
        System.out.println("Buybye, see ya later...");
        printHorizontal();
    }

    private void printHorizontal() {
        System.out.println("------------------------------");
    }

}
