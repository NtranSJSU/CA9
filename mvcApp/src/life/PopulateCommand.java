package life;

import mvc.Command;
import mvc.Model;

public class PopulateCommand extends Command {
    private boolean randomly;
    public PopulateCommand(Model model) {
        super(model);
    }

    public void setRandomly(boolean randomly) {
        this.randomly = randomly;
    }

    @Override
    public void execute() throws Exception {
        if (! (model instanceof Society))
            throw new Exception("Model must be an instance of Society");
        ((Society)model).repopulate(randomly);

    }
}
