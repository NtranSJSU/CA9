/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing CellularAutomata.java
 * */
package CellularAutomata;

import mvc.Publisher;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Agrika Gupta
 * Changes: Extended mvc framework to modify and implement Grid, Cell, GridView, CellView, and its customizations
 * Date: 10/3/2024
 */

public class CellularAutomata extends Grid{
    @Override
    public Cell makeCell(Grid cellularAutomata,int row, int col) {
        return new CellularAutomataCell(this,row,col);
    }
    public CellularAutomata() {
        super();
    }

}
