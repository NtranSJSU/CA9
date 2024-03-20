/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing AppPanel.java
 * */
package life;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Name: Agrika Gupta
 * Date: 10/3/2024
 * Changes: Display GridView of Grid with CellViews of individual Cell composition
 */

/**
 * Name: Agrika
 * Changes: Complete Cellular Automaton implementation/customization with view showing cells and their ambiences, and ability to
 * observe and update according to their neighbors' status per second/cycle, as well as completing New, Open, and Save implementations,
 * making sure to set each cell view to new grid's respective cell.
 * Date: 17/3/2024
 */

public class AppPanel extends JPanel implements Subscriber, ActionListener {
    protected Model ca;
    protected View view;
    ControlPanel controls;
    protected AppFactory appFactory;

    public AppPanel(AppFactory appFactory1) {
        appFactory=appFactory1;
        this.ca = appFactory.makeModel();
        this.view = appFactory.makeView(ca);
        this.setLayout(new GridLayout(1, 2));
        controls = new ControlPanel();
        this.add(controls);
        this.add(view);
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setResizable(true);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(appFactory.getTitle());
        frame.setSize(740, 500);
        frame.setVisible(true);
        World.PANEL_HEIGHT -= frame.getJMenuBar().getHeight();
        this.ca.subscribe(this);
    }
    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", appFactory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void setModel(Model newModel) {
        this.ca.unsubscribe(this);
        this.ca = newModel;
        this.ca.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.ca);
        update();
        ca.changed();
    }

    public void actionPerformed(ActionEvent ae) {
        String act = ae.getActionCommand();
        try {
            switch (act) {
                case "Save" : {
                    if (ca.getFileName() == null) {
                        ca.setFileName(Utilities.getFileName(null,false));
                    }
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ca.getFileName()));
                    os.writeObject(this.ca);
                    os.close();
                    break;
                }
                case "Open" : {
                    if (Utilities.confirm("Are you sure?")) {
                        String fName = Utilities.getFileName(null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        ca =(Model)is.readObject();
                        this.setModel(ca);
                        ca.setFileName(fName);
                        is.close();
                    }
                    break;
                }
                case "New" : {
                    if (Utilities.confirm("Are you sure?")) {
                        ca = appFactory.makeModel();
                        this.setModel(ca);
                        ca.setUnsavedChanges(false);
                    }
                    break;
                }
                case "Quit" : {
                    if (Utilities.confirm("Are you sure?"))
                        System.exit(0);
                    break;
                }
                case "About" : {
                    Utilities.inform(appFactory.about());
                    break;
                }
                case "Help" : {
                    String[] actDesc = appFactory.getHelp();
                    Utilities.inform(actDesc);
                    break;
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex); // all error handling done here!
        }
    }


    @Override
    public void update() {
        repaint();
    }

    static class ControlPanel extends JPanel {
        public ControlPanel() {
            setLayout(new GridLayout(2,2,50,50));
            setBorder( BorderFactory.createEmptyBorder(50, 50, 50, 50));
            setBackground(Color.MAGENTA);
        }
    }

}

