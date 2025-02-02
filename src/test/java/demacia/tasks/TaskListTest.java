package demacia.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.StringBuilder;

public class TaskListTest {

    @Test
    public void save_normal1_correct() {

        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            TaskStub task = new TaskStub();
            taskList.addToList(task);
        }
        String actual = taskList.save();

        StringBuilder expectedBuilder = new StringBuilder();
        TaskStub task = new TaskStub();
        for (int i = 0; i < 10; i++) {
            expectedBuilder.append(task.save());
            expectedBuilder.append("\n");
        }
        expectedBuilder.deleteCharAt(expectedBuilder.length() - 1);
        String expected = expectedBuilder.toString();

        assert(expected.equals(actual));
    }

    @Test
    public void save_normal2_correct() {

        TaskList taskList = new TaskList();
        TaskStub task = new TaskStub();
        taskList.addToList(task);
        String actual = taskList.save();

        String expected = task.save();

        assert(expected.equals(actual));
    }

    @Test
    public void save_emptyList_correct() {

        TaskList taskList = new TaskList();

        String actual = taskList.save();

        String expected = "";

        assert(expected.equals(actual));
    }

    @Test
    public void deleteTask_normal1_correct() {

        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            TaskStub task = new TaskStub();
            taskList.addToList(task);
        }

        for (int i = 0; i < 9; i++) {
            taskList.deleteTask(0);
        }

        assert(taskList.listTasks().equals("1.[D][X] do homework (by: 2025-05-20 12:23)"));
    }

    @Test
    public void deleteTask_normal2_correct() {

        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            TaskStub task = new TaskStub();
            taskList.addToList(task);
        }

        for (int i = 9; i >= 0; i--) {
            taskList.deleteTask(i);
        }

        assert(taskList.listTasks().equals("No tasks found"));
    }

    @Test
    public void deleteTask_emptyList_exception() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));

        try {
            taskList.deleteTask(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            assert(e.getMessage().equals("Task does not exist in the list"));
        }
    }

    @Test
    public void deleteTask_deleteTooManyFromList_exception() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            TaskStub task = new TaskStub();
            taskList.addToList(task);
        }

        for (int i = 9; i >= 0; i--) {
            taskList.deleteTask(i);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));

        try {
            taskList.deleteTask(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            assert(e.getMessage().equals("Task does not exist in the list"));
        }
    }

    @Test
    public void deleteTask_deleteOutOfBounds1_exception() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));

        try {
            taskList.deleteTask(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            assert(e.getMessage().equals("Task does not exist in the list"));
        }
    }

    @Test
    public void deleteTask_deleteOutOfBounds2_exception() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
        try {
            taskList.deleteTask(101);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            assert(e.getMessage().equals("Task does not exist in the list"));
        }
    }

    @Test
    public void deleteTask_deleteOutOfBounds3_exception() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
        try {
            taskList.deleteTask(3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            assert(e.getMessage().equals("Task does not exist in the list"));
        }
    }
}
