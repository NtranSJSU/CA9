/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing CellularAutomata.java
 * */
package CellularAutomata;

import mvc.Publisher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CellularAutomata extends Publisher implements Serializable {
    public int ip;
    public boolean halt;
    public int size;
    public int[] memory;
    List<String> commands;

    public CellularAutomata() {
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

    public void clear() {
        for (int i=0; i<size; i++) {
            memory[i] = 0;
        }
        notifySubscribers();
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
