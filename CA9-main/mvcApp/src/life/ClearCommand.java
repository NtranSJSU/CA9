package life;

import mvc.Command;
import mvc.Model;

public class ClearCommand extends Command {
    public ClearCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (! (model instanceof Society))
            throw new Exception("Model must be an instance of Society");
        ((Society)model).clear();
    }
}
