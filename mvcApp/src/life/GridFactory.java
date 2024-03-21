package life;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;
/*
 * Date: March 20th 2024
 * Name: Nhat Tran
 * Version: 1.0
 * Changes: Adjusting Help Button's Functionality
 * */
public class GridFactory implements AppFactory {
    String title="CELLULAR AUTOMATA: LIFE";
    String about="CellularAutomata v0.1";
    String[] help=new String[]{
            "Run1: Run Cellular Automata Simulation Once",
            "Run50: Run Cellular Automata Simulation 50 times in a set interval",
            "Clear: Reset all Cells back to original state, user can manually turns each cells on",
            "Populate: Randomly toggle cells"
    };
    String[] editCommands=new String[]{"Run1","Run50","Populate","Clear"};
    @Override
    public Model makeModel() {
        return new Society();
    }

    @Override
    public View makeView(Model model) {
        return new SocietyView((Society) model);
    }

    @Override
    public String[] getEditCommands() {
        return editCommands;
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (searchEditCommand(type)){
            switch (type){
                case ("Run1"):
                    RunCommand ra=new RunCommand(model);
                    ra.setCycles(1);
                    return ra;
                case ("Run50"):
                    RunCommand ra2=new RunCommand(model);
                    ra2.setCycles(50);
                    return ra2;
                case ("Populate"):
                    PopulateCommand populateCommand=new PopulateCommand(model);
                    populateCommand.setRandomly(true);
                    return populateCommand;
                case ("Clear"):
                    return new ClearCommand(model);
            }
        }
        return null;
    }

    public boolean searchEditCommand(String type) {
        for (String a:getEditCommands()){
            if (a.equals(type)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String[] getHelp() {
        return help;
    }

    @Override
    public String about() {
        return about;
    }
}
