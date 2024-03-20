/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing CellView.java
 * */
package life;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import mvc.*;

/**
 * Name: Agrika
 * Changes: Complete CellView implementation incl. setCell helper
 * Date: 17/3/2024
 */


public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

    // Constructor to initialize CellView with a Cell object
    public CellView(Cell c) {
        myCell = c;
        myCell.subscribe(this); // Subscribe to updates from the associated Cell object
        setSize(25, 25);
        setBorderPainted(true);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setVisible(true);
        if (c != null) {
            c.subscribe(this); // Subscribe to updates from the associated Cell object if it's not null
        }
        this.addActionListener(this); // Register action listener for button click events
        String statusText = String.valueOf(((Agent)myCell).getAmbience()); // Get the ambience status of the Cell
        setText(statusText); // Set the text of the button to display the ambience status
        setBackground(myCell.getColor()); // Set the background color of the button based on the associated Cell's color
        update(); // Update the button view
    }

    // Action performed method called when button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState(); // changes the state of myCell
        setText("" + ((Agent)myCell).getAmbience()); // Update the text of the button with the new ambience status
        setBackground(myCell.getColor()); // Update the background color of the button
        setBorder(BorderFactory.createLineBorder(Color.black)); // Set border color
        myCell.myGrid.notifySubscribers(); // Notify subscribers about the change
    }

    // Update method to refresh the view of the button
    public void update() {
        setText(String.valueOf(((Agent)myCell).getAmbience())); // Update the text of the button
        setBackground(myCell.getColor()); // Update the background color of the button
        repaint(); // Repaint the button
    }

    // Method to set a new Cell object for the CellView
    public void setCell(Cell a) {
        myCell.unsubscribe(this); // Unsubscribe from the previous Cell object
        this.myCell=a; // Set the new Cell object
        myCell.subscribe(this); // Subscribe to updates from the new Cell object
        update(); // Update the button view
    }


}


