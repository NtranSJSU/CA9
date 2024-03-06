/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing Command.java
 * */
package mvc;

public abstract class Command {
    protected Model model;

    public Command(Model model) {
        super();
        this.model = model;
    }

    public abstract void execute() throws Exception ;

}
