package globalquake.playground;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class DateTimeInputDialog extends JDialog {
    private final JSpinner yearSpinner;
    private final JSpinner monthSpinner;
    private final JSpinner daySpinner;
    private final JSpinner hourSpinner;
    private final JSpinner minuteSpinner;
    private final JSpinner secondSpinner;
    private LocalDateTime result;

    public DateTimeInputDialog(JFrame parent, String title, LocalDateTime initialDateTime) {
        super(parent, title, true);
        this.result = initialDateTime;

        setLayout(new GridLayout(6, 2, 10, 10));
        ((javax.swing.JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        yearSpinner = createSpinner(initialDateTime.getYear(), 1900, 2100);
        monthSpinner = createSpinner(initialDateTime.getMonthValue(), 1, 12);
        daySpinner = createSpinner(initialDateTime.getDayOfMonth(), 1, 31);
        hourSpinner = createSpinner(initialDateTime.getHour(), 0, 23);
        minuteSpinner = createSpinner(initialDateTime.getMinute(), 0, 59);
        secondSpinner = createSpinner(initialDateTime.getSecond(), 0, 59);

        add(new JLabel("Year:"));
        add(yearSpinner);
        add(new JLabel("Month:"));
        add(monthSpinner);
        add(new JLabel("Day:"));
        add(daySpinner);
        add(new JLabel("Hour:"));
        add(hourSpinner);
        add(new JLabel("Minute:"));
        add(minuteSpinner);
        add(new JLabel("Second:"));
        add(secondSpinner);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            try {
                result = LocalDateTime.of(
                        (Integer) yearSpinner.getValue(),
                        (Integer) monthSpinner.getValue(),
                        (Integer) daySpinner.getValue(),
                        (Integer) hourSpinner.getValue(),
                        (Integer) minuteSpinner.getValue(),
                        (Integer) secondSpinner.getValue()
                );
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date/time: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);

        add(buttonPanel);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private JSpinner createSpinner(int initialValue, int min, int max) {
        SpinnerNumberModel model = new SpinnerNumberModel(initialValue, min, max, 1);
        JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(100, 25));
        return spinner;
    }

    public LocalDateTime getResult() {
        return result;
    }
}