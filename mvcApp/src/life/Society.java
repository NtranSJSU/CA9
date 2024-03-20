/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing CellularAutomata.java
 * */
package life;

import java.util.HashSet;
import java.util.Set;

/**
 * Name: Agrika Gupta
 * Changes: Extended mvc framework to modify and implement Grid, Cell, GridView, CellView, and its customizations
 * Date: 10/3/2024
 */

public class Society extends Grid {
    public static Set<Integer> rebirth = new HashSet<>();
    public static Set<Integer> death = new HashSet<>();
    public static int percentAlive = 50;

    @Override
    public Cell makeCell(Grid cellularAutomata, int row, int col) {
        return new Agent(this, row, col);
    }

    public Society() {
        super();
    }

    //helper method
    public double getPercentAlive() {
        int total = dim * dim;
        int alive = 0;
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                if (getCell(row, col).getStatus() == 1)
                    alive++;
            }
        }
        int percentalive = (alive*100) / total;
        return percentalive;
    }

    @Override
    public void repopulate(boolean randomly) {
        // randomly set the status of each cell
        clear();
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                if (getPercentAlive() >= percentAlive)
                    break;
                cells[row][col].reset(randomly);
            }
        }
        notifySubscribers();
    }
}




