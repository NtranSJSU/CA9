package life;

import mvc.Command;
import mvc.Model;

public class ClearCommand extends Command {
    public ClearCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (! (model instanceof Society)) // Checking if the model is an instance of Society
            throw new Exception("Model must be an instance of Society"); // Throwing exception if model is not an instance of Society
        ((Society)model).clear(); // Clearing the Society model
    }
}
