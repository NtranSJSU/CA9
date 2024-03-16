/*
* Date: March 6th 2024
* Name: Nhat Tran
* Version: 0.1
* Changes: Implementing Cell.java
* */
package CellularAutomata;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;

abstract class Cell extends Publisher implements Serializable {
    protected int row, col;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid;
    protected Cell partner = null;

    public Cell(Grid myGrid,int a,int b){
        row=a;
        col=b;
        this.myGrid=myGrid;
    }


    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner==null && neighbors != null) {
            Cell[] array = new Cell[neighbors.size()];
            neighbors.toArray(array);
            while (partner==null) {
                int rand=(new Random()).nextInt(0,neighbors.size());
                this.partner= array[rand];
            }
        }
    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    public abstract Color getColor();

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

