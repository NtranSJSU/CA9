package life;

import mvc.AppFactory;
import mvc.Utilities;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;

import static life.Society.death;
import static life.Society.rebirth;

public class GridPanel extends AppPanel{

    public GridPanel(GridFactory appFactory1) {
        super(appFactory1);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        // Combine the existing line border with the empty border
        Border compoundBorder = BorderFactory.createCompoundBorder(
                blackline,
                emptyBorder
        );
        JButton run1 = new JButton("Run1");
        run1.addActionListener(this);
        run1.setBorder( compoundBorder);
        run1.setPreferredSize(new Dimension(15, 15));
        JButton run50 = new JButton("Run50");
        run50.addActionListener(this);
        run50.setBorder(  compoundBorder);
        run50.setPreferredSize(new Dimension(15,15));
        JButton populate = new JButton("Populate");
        populate.addActionListener(this);
        populate.setBorder(  compoundBorder);
        populate.setPreferredSize(new Dimension(15, 15));
        JButton clear = new JButton("Clear");
        clear.addActionListener(this);
        clear.setBorder( compoundBorder);
        clear.setPreferredSize(new Dimension(15, 15));
        controls.add(run1);
        controls.add(run50);
        controls.add(populate);
        controls.add(clear);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        super.actionPerformed(ae);
        String act = ae.getActionCommand();
        try {
            switch (act) {
                case "Run1": {
                    appFactory.makeEditCommand(ca,"Run1",this).execute();
                    break;
                }
                case "Run50": {
                    appFactory.makeEditCommand(ca,"Run50",this).execute();
                    break;
                }
                case "Clear": {
                    if (Utilities.confirm("Are you sure")) {
                        appFactory.makeEditCommand(ca,"Clear",this).execute();
                    }
                    break;
                }
                case "Populate": {
                    appFactory.makeEditCommand(ca,"Populate",this).execute();
                    break;
                }
            }
        }  catch (Exception ex) {
            Utilities.error(ex); // all error handling done here!
        }
    }


    static {
        rebirth.add(3);
        death.add(0);
        death.add(1);
        death.add(4);
        death.add(5);
        death.add(6);
        death.add(7);
        death.add(8);
    }


    public static void main(String[] args) {
        GridPanel app = new GridPanel(new GridFactory());
    }
}
