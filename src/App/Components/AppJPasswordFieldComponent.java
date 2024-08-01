package App.Components;

import App.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class AppJPasswordFieldComponent extends JPanel {
    private JPasswordField passwordField;
    private String placeholder;
    private Color placeholderColor;
    private Color borderColor;
    private Color backgroundColor;
    private Color textColor;
    private boolean isPlaceholderVisible;

    public AppJPasswordFieldComponent(final String placeholder, Color backgroundColor, Color borderColor) {
        this.placeholderColor = Constants.PLACEHOLDER_COLOR;
        this.borderColor = Constants.BORDER_COLOR;
        this.textColor = Constants.TEXT_COLOR;
        this.isPlaceholderVisible = true;
        this.placeholder = placeholder;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.passwordField = new JPasswordField();
        this.passwordField.setOpaque(false);
        this.passwordField.setBorder(new EmptyBorder(5, 5, 5, 30));
        this.passwordField.setForeground(Constants.PLACEHOLDER_COLOR);
        this.passwordField.setEchoChar('*');
        this.passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
        this.passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (AppJPasswordFieldComponent.this.isPlaceholderVisible) {
                    AppJPasswordFieldComponent.this.setText("");
                    AppJPasswordFieldComponent.this.passwordField.setForeground(Constants.TEXT_COLOR);
                    AppJPasswordFieldComponent.this.isPlaceholderVisible = false;
                }

            }

            public void focusLost(FocusEvent e) {
                if (AppJPasswordFieldComponent.this.getPassword().isEmpty()) {
                    AppJPasswordFieldComponent.this.setText(placeholder);
                    AppJPasswordFieldComponent.this.setForeground(AppJPasswordFieldComponent.this.placeholderColor);
                    AppJPasswordFieldComponent.this.isPlaceholderVisible = true;
                }

            }
        });
        this.add(this.passwordField);
    }

    public void setText(String t) {
        this.passwordField.setText(t);
        if (t.length() > 0) {
            this.isPlaceholderVisible = false;
        }

    }

    public String getPassword() {
        return new String(this.passwordField.getPassword());
    }
}

