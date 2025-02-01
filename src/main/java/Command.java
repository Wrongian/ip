public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public void exit() {
        this.isExit = true;
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Terminal terminal);

    public void save(SaveData saveData) {
        SaveHandler.save(saveData);
    }

}
