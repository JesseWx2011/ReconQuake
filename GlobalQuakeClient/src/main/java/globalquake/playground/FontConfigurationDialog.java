package globalquake.playground;

import javax.swing.*;
import java.awt.*;

public class FontConfigurationDialog extends JDialog {
    private final JComboBox<String> fontNameCombo;
    private final JSpinner fontSizeSpinner;
    private final JComboBox<String> fontStyleCombo;
    private Font result;

    public FontConfigurationDialog(JFrame parent, String title, Font initialFont) {
        super(parent, title, true);
        this.result = initialFont;

        setLayout(new GridLayout(3, 2, 10, 10));
        ((javax.swing.JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontNameCombo = new JComboBox<>(fontNames);
        fontNameCombo.setSelectedItem(initialFont.getFontName());
        fontNameCombo.setPreferredSize(new Dimension(200, 25));

        fontSizeSpinner = new JSpinner(new SpinnerNumberModel(initialFont.getSize(), 8, 72, 1));
        fontSizeSpinner.setPreferredSize(new Dimension(100, 25));

        String[] styleNames = {"Plain", "Bold", "Italic", "Bold Italic"};
        fontStyleCombo = new JComboBox<>(styleNames);
        int styleIndex = 0;
        if ((initialFont.getStyle() & Font.BOLD) != 0 && (initialFont.getStyle() & Font.ITALIC) != 0) {
            styleIndex = 3;
        } else if ((initialFont.getStyle() & Font.BOLD) != 0) {
            styleIndex = 1;
        } else if ((initialFont.getStyle() & Font.ITALIC) != 0) {
            styleIndex = 2;
        }
        fontStyleCombo.setSelectedIndex(styleIndex);
        fontStyleCombo.setPreferredSize(new Dimension(150, 25));

        add(new JLabel("Font Name:"));
        add(fontNameCombo);
        add(new JLabel("Size:"));
        add(fontSizeSpinner);
        add(new JLabel("Style:"));
        add(fontStyleCombo);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            String fontName = (String) fontNameCombo.getSelectedItem();
            int fontSize = (Integer) fontSizeSpinner.getValue();
            int styleIndex2 = fontStyleCombo.getSelectedIndex();
            int style = switch (styleIndex2) {
                case 1 -> Font.BOLD;
                case 2 -> Font.ITALIC;
                case 3 -> Font.BOLD | Font.ITALIC;
                default -> Font.PLAIN;
            };
            result = new Font(fontName, style, fontSize);
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);

        add(buttonPanel);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public Font getResult() {
        return result;
    }
}
