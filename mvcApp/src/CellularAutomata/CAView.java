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

public class CAView extends View {
    CellularAutomata ca;
    JPanel view;

    public CAView(CellularAutomata ca) {
        this.ca = ca;
        CAView.this.ca.subscribe(this);

        this.setSize(400, 460);
        this.setLayout(new GridLayout(1, 1));

        view = new JPanel();

        this.add(view, BorderLayout.CENTER);
        view.setBackground(Color.PINK);

        view.setVisible(true);

        update();
    }

    public void set(CellularAutomata ca) {
        this.ca = ca;
        update();
    }
    public void update() {
        view = new JPanel();
        this.removeAll();
        this.add(view);
        view.setBackground(Color.pink);
        this.revalidate();
    }
}
