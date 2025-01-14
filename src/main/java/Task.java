import java.lang.StringBuilder;
public class Task {

    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        if (this.getIsDone()) {
            msg.append("[X] ");
        } else {
            msg.append("[ ] ");
        }
        msg.append(this.getName());
        return msg.toString();
    }

}
