package life;

import mvc.*;

import java.awt.*;
/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing GridView.java
 * */

/**
 * Name: Agrika
 * Changes: Complete CellView implementation incl. setModel override
 * Date: 17/3/2024
 */
public class GridView  extends View {
    int row, col;
    CellView[][] cellViews;
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
                cellViews[a][b].update();
            }
        }
        repaint();
    }

    @Override
    public void setModel(Model a) {
        super.setModel(a);
        for (int x=0;x<row;x++){
            for (int b=0;b<col;b++) {
                cellViews[x][b].setCell(((Society)model).getCell(x,b));
            }
        }
    }
}
