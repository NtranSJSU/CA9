package CellularAutomata;

import CellularAutomata.Cell;
import CellularAutomata.Grid;
import java.awt.*;
import java.util.Random;

/**
 * Name: Agrika Gupta
 * Date: 10/3/2024
 * Changes: Show Status as Text and Colors in Cells. Implement clear() button for
 * CA customization. Changed button implementations for Run50-UpdateLoop(50)
 * and Clear-clear(). Updated CellView and GridView update() methods
 * as subscribers.
 */

public class CellularAutomataCell extends Cell {
    Color color=Color.RED;
    int status=0;
    int ambience=0;
    public CellularAutomataCell(CellularAutomata myGrid, int a, int b) {
        super(myGrid, a, b);
        notifySubscribers();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void observe() {
        ambience= 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getStatus() == 1) {
                ambience++;
            }
        }
    }
    @Override
    public void interact() {

    }
    @Override
    public void update() {
        if (ambience>=0 && ambience<=1){
            reset(false);
        } else if (ambience >=4 && ambience<=8) {
            reset(false);
        } else if (ambience==3) {
            status=1;
            color=Color.GREEN;
        }
    }
    @Override
    public void nextState() {
        if (status==0) {
            color = Color.GREEN;
            status=1;
        }
        else if (status==1) {
            color = Color.RED;
            status=0;
        }
        notifySubscribers();
    }
    @Override
    public void reset(boolean randomly) {
        if (!randomly) {
            this.color=Color.RED;
            this.status=0;
        } else {
            int rand=(new Random()).nextInt(0,2);
            if (rand==1) {
                this.color=Color.GREEN;
                this.status=1;
            } else{
                this.color=Color.RED;
                this.status=0;
            }
        }
        notifySubscribers();
    }
}
