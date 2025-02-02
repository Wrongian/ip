package demacia.tasks;

import org.junit.jupiter.api.Test;

/**
 * Class to test Todo class.
 */
public class TodoTest {

    @Test
    public void toString_unmarkedInput_correct() {
        String name = "watch anime";

        Todo todo = new Todo(name);
        String actual = todo.toString();
        String expected = "[T][ ] watch anime";

        assert(actual.equals(expected));
    }

    @Test
    public void toString_markedInput_correct() {
        String name = "watch anime";

        Todo todo = new Todo(name);
        todo.markDone();

        String actual = todo.toString();
        String expected = "[T][X] watch anime";

        assert(actual.equals(expected));

        todo = new Todo(name, false);
        todo.markDone();

        actual = todo.toString();
        expected = "[T][X] watch anime";

        assert(actual.equals(expected));
    }

    @Test
    public void save_normalInputs_correct() {
        String name = "watch anime";

        Todo todo = new Todo(name);
        todo.markDone();

        // unmarked
        String actual = todo.save();
        String expected = "name:watch anime,isMarked:true,type:T";

        assert(actual.equals(expected));

        todo.unmarkDone();

        actual = todo.save();
        expected = "name:watch anime,isMarked:false,type:T";

        assert(actual.equals(expected));
    }
}
