package life;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;
/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing Cell.java
 * */

// Abstract class representing a cell in the grid
abstract class Cell extends Publisher implements Serializable {
    protected int row, col; // Position of the cell in the grid
    protected Set<Cell> neighbors = new HashSet<Cell>(); // Set to store neighboring cells
    protected Grid myGrid; // Reference to the grid this cell belongs to
    protected Cell partner = null; // Reference to a neighboring cell as a partner

    // Constructor for initializing cell's position and its grid
    public Cell(Grid myGrid,int a,int b){
        row=a;
        col=b;
        this.myGrid=myGrid;
        // Obtain neighboring cells and notify subscribers about the cell's existence
        neighbors=myGrid.getNeighbors(this,1);
        notifySubscribers();
    }


    // choose a random neighbor as a partner
    public void choosePartner() {
        neighbors=myGrid.getNeighbors(this,1); // Retrieve neighbors
        if (partner==null && neighbors != null) // Check if there are neighbors available
        {
            Cell[] array = new Cell[neighbors.size()]; // Create an array to hold neighbors
            neighbors.toArray(array); // Convert neighbors set to an array
            while (partner==null) {
                int rand=(new Random()).nextInt(0,neighbors.size()); // Generate a random index
                this.partner= array[rand]; // Assign a random neighbor as a partner
            }
        }
        notifySubscribers(); // Notify subscribers about the change
    }

    // Unlink the current cell from its partner
    public void unpartner()
    {
        if (partner != null) // Check if the cell has a partner
        {
            if (partner.partner != null) // Check if the partner has another partner
            {
                partner.partner = null; // Unlink partner from its partner
            }
            partner = null; // Unlink current cell from its partner
        }
        notifySubscribers(); // Notify subscribers about the change
    }

    // Abstract method to get the color of the cell
    public abstract Color getColor();

    // Abstract method to get the status of the cell
    public abstract int getStatus();

    // observer neighbors' states
    public abstract void observe();
    // interact with a random neighbor
    public abstract void interact();
    // update my state
    public abstract void update();
    // set status to status + 1 mod whatever
    public abstract void nextState();
    // set status to a random or initial value
    public abstract void reset(boolean randomly);

}

