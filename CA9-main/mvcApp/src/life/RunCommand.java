package life;

import mvc.Command;
import mvc.Model;

public class RunCommand extends Command {
    int cycles;
    public RunCommand(Model model) {
        super(model);
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    @Override
    public void execute() throws Exception {
        if (! (model instanceof Society))
            throw new Exception("Model must be an instance of Society");
        ((Society)model).updateLoop(cycles);

    }
}
