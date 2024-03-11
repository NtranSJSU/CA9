package CellularAutomata;

import CellularAutomata.Cell;
import CellularAutomata.Grid;
import java.awt.*;

public class CellularAutomataCell extends Cell {


    public CellularAutomataCell(Grid myGrid, int a, int b) {
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
        notifySubscribers();
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
