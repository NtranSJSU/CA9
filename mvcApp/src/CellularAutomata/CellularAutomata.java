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

public class CellularAutomata extends Grid{
    public int ip;
    public boolean halt;
    public int size;
    public int[] memory;
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

    public void clear() {
        for (int a=0;a<dim;a++) {
            for (int b = 0; b < dim; b++) {
                (cells[a][b]).color= Color.WHITE;
            }
        }
        notifySubscribers();
    }


    public CellularAutomata() {
        super();
        ip = 0;
        halt = false;
        size = 32;
        memory = new int[size];
        commands = new ArrayList<>();
    }

    public CellularAutomata(String file_path) throws Exception {
        ip = 0;
        halt = false;
        size = 32;
        memory = new int[size];
        read(file_path);
        notifySubscribers();
    }

    public void execute() {
        while (!halt) {
            ip++;
            notifySubscribers();
        }
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
