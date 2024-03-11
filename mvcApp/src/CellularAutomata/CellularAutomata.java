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
    List<String> commands;
    int row=0;
    int col=0;



    @Override
    public Cell makeCell(boolean uniform) {
        if (0<col && col<20)
            col++;
        else if (0<row && row<20)
            row++;
        return new CellularAutomataCell(this,row,col);
    }




    public CellularAutomata() {
        super();
    }

    @Override
    public void clear() {
        row=0; col=0;
        super.clear();
    }

    public void read(String file_path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file_path);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // Read each line from the file
        String line;
        commands = new ArrayList<>();
        while ((line=bufferedReader.readLine()) != null) {
            commands.add(line);
        }

        // Close the resources
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        notifySubscribers();
    }

}
