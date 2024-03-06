/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing GridView.java
 * */
package CellularAutomata;

import mvc.*;

public class GridView  extends View {

    private CellView cellViews[][];

    public GridView(Model model) {
        /*
        Cell cell = new CellView(((Grid)model).getCell(row, col)
        cellViews[row][col] = cell
        set cell.row and cell.col here
        */
    }

    public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
    }

}
