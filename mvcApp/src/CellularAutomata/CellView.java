/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing CellView.java
 * */
package CellularAutomata;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import mvc.*;


public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

    public CellView(Cell c) {
        myCell = c;
        setSize(25, 25);
        setBorderPainted(true);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setVisible(true);
        if (c != null) {
            c.subscribe(this);
        }
        this.addActionListener(this);
        String statusText = String.valueOf(((CellularAutomataCell)myCell).getStatus());
        setText(statusText);
        setBackground(myCell.getColor());
        update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState(); // changes the state of myCell
        setText("" + myCell.getStatus());
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void update() {
        setText(String.valueOf(myCell.getStatus()));
        setBackground(myCell.getColor());
        repaint();
    }


}

/*
Some other files needed:
   GridFactory.java
   GridPanel.java
   ClearCommand.java
   RunCommand.java   // for Run1 and Run50 buttons
   PopulateCommand.java
*/
