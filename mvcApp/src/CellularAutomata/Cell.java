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
    protected Color color=Color.WHITE;
    protected int row, col;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid;
    protected Cell partner = null;
    int status=0;
    public Cell(Grid myGrid,int a,int b){
        row=a;
        col=b;
        this.myGrid=myGrid;
    }


    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
			/*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/
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

    public Color getColor(){

        return color;
    }

    public int getStatus() {

        return status;
    }

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

