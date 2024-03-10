/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing View.java
 *
 * Date: March 9th 2024
 * Name: Agrika Gupta
 * Version: 0.1
 * Changes: Adding a pointer to the model */
package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {
    protected Model model;
    public View(Model a) {
        model=a;
        model.subscribe(this);
    }

    public View()
    {
        model=null;

    }
    public void update() {
        repaint();
    }

    public void setModel(Model newLight) {
        model.unsubscribe(this);
        model = newLight;
        model.subscribe(this);
        repaint();
    }
}
