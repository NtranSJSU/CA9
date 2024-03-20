package life;

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

/**
 * Name: Agrika
 * Changes: Complete Cellular Automaton Cell implementation/customization incl. reset(randomly), nextState(), update(), observe(), getAmbience() helper, and initialization
 * Date: 17/3/2024
 */

public class Agent extends Cell {
    Color color=Color.RED;
    int status=0;
    int ambience=0;
    public Agent(Society myGrid, int a, int b) {
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
        getAmbience();
        notifySubscribers();
    }

    public int getAmbience() {
        ambience= 0;
        neighbors=myGrid.getNeighbors(this,1);
        for (Cell neighbor : neighbors) {
            if (myGrid.getStatus(neighbor) == 1) {
                ambience++;
            }
        }
        return ambience;
    }

    @Override
    public void interact() {

    }
    @Override
    public void update() {
        if (Society.death.contains(ambience)){
            status=0;
            color=Color.RED;
        } else if (Society.rebirth.contains(ambience)) {
            status=1;
            color=Color.GREEN;
        }
        notifySubscribers();
    }


    @Override
    public void nextState() {
        if (status==0) {
            color = Color.GREEN;
            status=1;
        }
        else if (status==1) {
            reset(false);
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
