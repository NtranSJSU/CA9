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

    //default color for the agent
    Color color=Color.RED;

    //default status for the agent
    int status=0;

    //default ambience for the agent
    int ambience=0;

    /**
     * Constructor for creating an agent at a specific position in the grid.
     *
     * @param myGrid The society/grid the agent belongs to.
     * @param a      The x-coordinate of the agent.
     * @param b      The y-coordinate of the agent.
     */
    public Agent(Society myGrid, int a, int b) {
        super(myGrid, a, b);
        notifySubscribers();
    }

    /**
     * Retrieves the color of the agent.
     *
     * @return The color of the agent.
     */
    @Override
    public Color getColor() {
        return color;
    }


    /**
     * Retrieves the status of the agent.
     *
     * @return The status of the agent.
     */
    @Override
    public int getStatus() {
        return status;
    }

    /**
     * Observes the surrounding environment to determine ambience.
     * Notifies subscribers after observing.
     */
    @Override
    public void observe() {
        getAmbience();
        notifySubscribers();
    }

    /**
     * Calculates the ambience of the agent based on its neighbors' statuses.
     *
     * @return The ambience value.
     */
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

    /**
     * Interacts with neighboring cells.
     */
    @Override
    public void interact() {

    }

    /**
     * Updates the status and color of the agent based on its ambience.
     * Notifies subscribers after updating.
     */
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


    /**
     * Moves the agent to its next state.
     * Notifies subscribers after moving to the next state.
     */
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


    /**
     * Resets the agent's status and color, either randomly or to default values.
     *
     * @param randomly If true, reset the agent's status and color randomly.
     *                 If false, reset to default values.
     */
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
