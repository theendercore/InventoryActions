package highfox.inventoryactions.api.function;

import highfox.inventoryactions.api.action.IActionContext;

import java.util.Queue;

public interface IActionFunction {

    /**
     * Executes the function after all relevant checks have passed. Only run on the server
     * <p>
     * Any functions that would change the state of the game should be added to the deferred work queue,
     * so as to not interfere with other functions
     *
     * @param workQueue queue of runnables to run later
     * @param context   the context this function is being run from
     */
    void run(Queue<Runnable> workQueue, IActionContext context);

    /**
     * Gets the deserializer type.
     *
     * @return an {@link ActionFunctionType}
     */
    ActionFunctionType getType();

}
