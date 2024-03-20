package mvc;

/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing AppFactory.java
 * */

public interface AppFactory {
    public Model makeModel();
    public View makeView(Model model);
    public String[] getEditCommands();
    public Command makeEditCommand(Model model, String type, Object source);
    public String getTitle();
    public String[] getHelp();
    public String about();
}

