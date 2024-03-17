/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing Grid.java
 * */
package CellularAutomata;

import java.awt.*;
import java.util.*;

import mvc.*;

public abstract class Grid extends Model {
    static private int time ;
    protected int dim ;
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell(Grid myGrid, int row, int col);
    public Grid(int dim) {
        this.dim = dim;
        cells=new Cell[dim][dim];
        populate();
        notifySubscribers();
    }
    public Grid() { this(20);
    }
    public void clear() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                // Reset the state of each cell to its initial state
                cells[row][col].reset(false);
                cells[row][col].notifySubscribers();
            }
        }
        notifySubscribers();
    }

    protected void populate() {
        for (int row=0;row<dim;row++){
            for (int col=0;col<dim;col++) {
                cells[row][col]=makeCell(this,row,col);
                cells[row][col].notifySubscribers();
                }
            }
        }
    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
            // randomly set the status of each cell
            for (int row=0;row<dim;row++) {
                for (int col = 0; col < dim; col++) {
                    cells[row][col].reset(randomly);
                    cells[row][col].notifySubscribers();
                }
            }
            notifySubscribers();
        }
    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        Set<Cell> neighbors = new HashSet<>();
        int row = asker.row;
        int col = asker.col;

        // Iterate over cells within the specified radius
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                // Skip the center cell itself
                if (i == 0 && j == 0) {
                    continue;
                }
                int newRow = (row + i + dim) % dim;
                int newCol = (col + j + dim) % dim;
                neighbors.add(this.getCell(newRow, newCol));
            }
        }

        return neighbors;

    }
    // overide these
    public int getStatus(Cell asker) { return asker.getStatus(); }
    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int row=0;row<dim;row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].observe();
                cells[row][col].notifySubscribers();
            }
        }
        notifySubscribers();
    }
    public void interact() {
        // ???
    }
    public void update() {
        for (int row=0;row<dim;row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].update();
                cells[row][col].notifySubscribers();
            }
        }
        notifySubscribers();
    }
    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
    // 1. use makeCell to fill in cells
        // 2. use getNeighbors to set the neighbors field of each cell
    }


// cell phases:


