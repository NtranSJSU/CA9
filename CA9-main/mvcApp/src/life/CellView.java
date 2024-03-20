package life;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import mvc.*;

/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing CellView.java
 * */

/**
 * Name: Agrika
 * Changes: Complete CellView implementation incl. setCell helper
 * Date: 17/3/2024
 */


public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

    public CellView(Cell c) {
        myCell = c;
        myCell.subscribe(this);
        setSize(25, 25);
        setBorderPainted(true);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setVisible(true);
        if (c != null) {
            c.subscribe(this);
        }
        this.addActionListener(this);
        String statusText = String.valueOf(((Agent)myCell).getAmbience());
        setText(statusText);
        setBackground(myCell.getColor());
        update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState(); // changes the state of myCell
        setText("" + ((Agent)myCell).getAmbience());
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
        myCell.myGrid.notifySubscribers();
    }

    public void update() {
        setText(String.valueOf(((Agent)myCell).getAmbience()));
        setBackground(myCell.getColor());
        repaint();
    }

    public void setCell(Cell a) {
        myCell.unsubscribe(this);
        this.myCell=a;
        myCell.subscribe(this);
        update();
    }


}

