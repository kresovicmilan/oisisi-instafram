package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class ClockPanel extends JPanel {

  private JLabel clock = new JLabel();

  public ClockPanel() {
    setLayout(new BorderLayout());
    tickTock();
    add(clock);
    Timer timer = new Timer(500, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tickTock();
      }
    });
    timer.setRepeats(true);
    timer.setCoalesce(true);
    timer.setInitialDelay(0);
    timer.start();
  }

  public void tickTock() {
    clock.setText("Vreme: " + DateFormat.getDateTimeInstance().format(new Date()) + "   ");
    clock.setFont(new Font("Ariel", Font.PLAIN, 14));
    clock.setMaximumSize(getMaximumSize());
  }
}