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
        setOpaque(true);
        setBorderPainted(true);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setVisible(true);
        if (c != null) { c.subscribe(this);}
        this.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState(); // changes the state of myCell
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setText("" + myCell.getStatus());
    }

    // called by notifySubscribers and GridView.update


    @Override
    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw the bordered rectangle
        int borderWidth = 1; // Adjust the border width as needed
        int width = getWidth();
        int height = getHeight();

        // Draw the filled rectangle
        g.setColor(myCell.getColor()); // Change the color as needed
        g.fillRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);

        // Draw the border
        g.setColor(Color.BLACK); // Change the color as needed
        g.drawRect(0, 0, width , height );
    }
    public void setCell(Cell a){myCell=a;
    }
}

/*
Some other files needed:

   GridFactory.java
   GridPanel.java
   ClearCommand.java
   RunCommand.java   // for Run1 and Run50 buttons
   PopulateCommand.java


       public StoplightView(Stoplight light) {
        this.light = light;
        light.subscribe(this);
        setSize(500, 500);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setBackground(Color.LIGHT_GRAY);
    }

    public void update() {
        repaint();
    }

    public void setLight(Stoplight newLight) {
        light.unsubscribe(this);
        light = newLight;
        light.subscribe(this);
        repaint();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        StoplightComponent lightComponent = new StoplightComponent(light);
        lightComponent.paintComponent(gc);
        gc.setColor(oldColor);
    }

*/
