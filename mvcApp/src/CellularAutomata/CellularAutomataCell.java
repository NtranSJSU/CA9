package CellularAutomata;

import CellularAutomata.Cell;
import CellularAutomata.Grid;
import java.awt.*;

/**
 * Name: Agrika Gupta
 * Date: 10/3/2024
 * Changes: Show Status as Text and Colors in Cells. Implement clear() button for
 * CA customization. Changed button implementations for Run50-UpdateLoop(50)
 * and Clear-clear(). Updated CellView and GridView update() methods
 * as subscribers.
 */

public class CellularAutomataCell extends Cell {
    public CellularAutomataCell(CellularAutomata myGrid, int a, int b) {
        super(myGrid, a, b);
        notifySubscribers();
    }
    @Override
    public void observe() {

    }
    @Override
    public void interact() {

    }
    @Override
    public void update() {
    }
    @Override
    public void nextState() {
        if (color==Color.WHITE)
            color=Color.GREEN;
        else if (color==color.GREEN)
            color=Color.RED;
        else
            color=color.WHITE;
    }
    @Override
    public void reset(boolean randomly) {

    }
}
