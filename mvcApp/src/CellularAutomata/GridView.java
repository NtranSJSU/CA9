/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing GridView.java
 * */
package CellularAutomata;

import mvc.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GridView  extends View {
    int row, col;
    private CellView[][] cellViews;
    public GridView(Grid myGrid) {
        super(myGrid);
        row=myGrid.getDim();
        col=myGrid.getDim();
        cellViews=new CellView[row][col];
        this.setLayout(new GridLayout(row,col));
        setSize(500, 500);
        setVisible(true);
        for (int a=0;a<row;a++){
            for (int b=0;b<col;b++){
                cellViews[a][b]=new CellView(myGrid.getCell(a,b));
                this.add(cellViews[a][b]);
            }
        }
        update();
    }

    @Override
    public void update() {
        // call update method of each CellView
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < col; b++) {
                cellViews[a][b].update(); // Assuming CellView has an update method
            }
        }
        super.update();
    }
}
