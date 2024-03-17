/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing AppPanel.java
 * */
package CellularAutomata;

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

public class AppPanel extends JPanel implements Subscriber, ActionListener {

    private String recentFName = null;
    private Model ca;
    private CAView view;
    public AppPanel() {
        this.ca = new CellularAutomata();
        this.view = new CAView((CellularAutomata) ca);
        this.setLayout(new GridLayout(1, 2));
        ControlPanel controls = new ControlPanel();
        this.add(controls);
        this.add(view);
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setResizable(true);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("CellularAutomata");
        frame.setSize(702, 502);
        frame.setVisible(true);
        World.PANEL_HEIGHT -= frame.getJMenuBar().getHeight();
        this.ca.subscribe(this);
    }
    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Run1", "Run50", "Populate", "Clear"}, this);
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
                    if (recentFName == null) {
                        recentFName = Utilities.getFileName(null, false);
                    }
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(recentFName));
                    os.writeObject(this.ca);
                    os.close();
                    break;
                }
                case "Open" : {
                    if (Utilities.confirm("Are you sure?")) {
                        String fName = Utilities.getFileName(null, true);
                        recentFName = fName;
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        ca =(CellularAutomata)is.readObject();
                        is.close();
                    }
                    break;
                }
                case "New" : {
                    if (Utilities.confirm("Are you sure?")) {
                        recentFName = null;
                        ca = new CellularAutomata();
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
                case "Run1" : {
                    ((CellularAutomata)ca).updateLoop(1);
                    break;
                }
                case "Run50" : {
                    ((CellularAutomata)ca).updateLoop(50);
                    break;
                }
                case "Clear" : {
                    if (Utilities.confirm("Are you sure")) {
                        recentFName = null;
                        ((CellularAutomata)ca).clear();
                    }
                    break;
                }
                case "Populate" : {
                    ((CellularAutomata)ca).repopulate(true);
                    break;
                }
                case "About" : {
                    Utilities.inform("CellularAutomata v0.1");
                    break;
                }
                case "Help" : {
                    String[] actDesc = new String[]{
                            "Run1: Read the inputted file and post it in the bottom right",
                            "Run50: Run50 the file shown and display the result on the top right",
                            "Clear: Clear the file from the program",
                            "Populate: Randomly toggle cells"
                    };
                    Utilities.inform(actDesc);
                    break;
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex); // all error handling done here!
        }
    }


    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }

    @Override
    public void update() {
        repaint();
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(2, 2));
            Dimension d = new Dimension();
            d.setSize(400, 400);
            p.setPreferredSize(new Dimension(d));
            JPanel rn1 = new JPanel();
            JPanel rn50 = new JPanel();
            JPanel ppl = new JPanel();
            JPanel clr = new JPanel();
            JButton run1 = new JButton("Run1");
            run1.addActionListener(AppPanel.this);
            rn1.add(run1);
            JButton run50 = new JButton("Run50");
            run50.addActionListener(AppPanel.this);
            rn50.add(run50);
            JButton populate = new JButton("Populate");
            populate.addActionListener(AppPanel.this);
            ppl.add(populate);
            JButton clear = new JButton("Clear");
            clear.addActionListener(AppPanel.this);
            clr.add(clear);
            p.add(rn1);
            p.add(rn50);
            p.add(ppl);
            p.add(clr);
            this.add(p);
        }
    }

}

