package App.Components;


import App.Constants;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AppJTextFieldComponent extends JTextField {
    private String placeholder;
    private Color placeholderColor;
    private Color borderColor;
    private Color backgroundColor;
    private Color textColor;
    private boolean isPlaceholderVisible;

    public AppJTextFieldComponent(final String placeholder, Color backgroundColor, Color borderColor) {
        this.placeholderColor = Constants.PLACEHOLDER_COLOR;
        this.borderColor = Constants.BORDER_COLOR;
        this.textColor = Constants.TEXT_COLOR;
        this.isPlaceholderVisible = true;
        this.placeholder = placeholder;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(5, 5, 5, 30));
        this.setForeground(Constants.PLACEHOLDER_COLOR);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
        if (this.getText().isEmpty()) {
            this.setText(placeholder);
            this.setForeground(this.placeholderColor);
        }

        this.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                String t = AppJTextFieldComponent.this.getText();
                if (AppJTextFieldComponent.this.isPlaceholderVisible) {
                    AppJTextFieldComponent.this.setText("");
                    AppJTextFieldComponent.this.setForeground(AppJTextFieldComponent.this.textColor);
                    AppJTextFieldComponent.this.isPlaceholderVisible = false;
                }

            }

            public void focusLost(FocusEvent e) {
                if (AppJTextFieldComponent.this.getText().isEmpty()) {
                    AppJTextFieldComponent.this.setText(placeholder);
                    AppJTextFieldComponent.this.setForeground(AppJTextFieldComponent.this.placeholderColor);
                    AppJTextFieldComponent.this.isPlaceholderVisible = true;
                }

            }
        });
    }

    public String getText() {
        String text = super.getText();
        return this.isPlaceholderVisible && text.equals(this.placeholder) ? "" : text;
    }
}

