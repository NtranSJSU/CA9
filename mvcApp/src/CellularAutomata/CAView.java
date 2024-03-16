/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing CAView.java
 * */
package CellularAutomata;

import mvc.View;

import javax.swing.*;
import java.awt.*;

public class CAView extends GridView {

    public CAView(CellularAutomata ca) {
        super(ca);
    }

    public void set(CellularAutomata ca) {
        setModel(ca);
        update();
    }
}