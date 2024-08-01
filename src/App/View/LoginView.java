package App.View;

import App.Constants;
import App.Users.*;
import App.Components.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginView extends JFrame {
    private AppJTextFieldComponent usernameField;
    private AppJPasswordFieldComponent passwordField;
    private JButton loginButton;
    private JLabel noti;
    private JButton createAccount;
    private AppJPanelComponent mainPanel;

    public LoginView() {
        this.setTitle("Login");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setLayout((LayoutManager)null);
        this.mainPanel = new AppJPanelComponent(Constants.LOGIN_BACKGROUND, Constants.LOGIN_SHADOW, 30, 1.0F);
        this.mainPanel.setLayout((LayoutManager)null);
        int mPanelW = 300;
        int mPanelH = 240;
        this.mainPanel.setBounds(200, 100, mPanelW, mPanelH);
        this.noti = new JLabel("", 0);
        this.noti.setBounds(50, 30, 200, 15);
        this.noti.setFont(new Font("Arial", 0, 11));
        this.usernameField = new AppJTextFieldComponent("Username", Constants.LOGIN_BACKGROUND, Constants.BORDER_COLOR);
        this.usernameField.setBounds(50, 50, 200, 30);
        this.passwordField = new AppJPasswordFieldComponent("Password", Constants.LOGIN_BACKGROUND, Constants.BORDER_COLOR);
        this.passwordField.setBounds(50, 90, 200, 30);
        this.loginButton = new JButton("Login");
        this.loginButton.setBounds(50, 130, 200, 30);
        this.loginButton.setFocusPainted(false);
        this.loginButton.setBorderPainted(false);
        this.loginButton.setBackground(Color.CYAN);
        this.loginButton.setFont(new Font("Arial", 0, 13));
        this.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = LoginView.this.usernameField.getText();
                String pass = LoginView.this.passwordField.getPassword();
                if (!user.isEmpty() && !pass.isEmpty()) {
                    LoginView.this.authenticate();
                } else {
                    LoginView.this.noti.setText("Username and Password are Empty");
                    LoginView.this.noti.setForeground(Constants.NOTI_EMPTY);
                }

            }
        });
        this.createAccount = new JButton();
        this.createAccount.setText("Create Account");
        this.createAccount.setFont(new Font("Arial", 0, 13));
        this.createAccount.setFocusPainted(false);
        this.createAccount.setBorderPainted(false);
        this.createAccount.setBounds(50, 170, 200, 30);
        this.createAccount.setBackground(Color.CYAN);
        this.createAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                (new RegisterView()).setVisible(true);
                LoginView.this.dispose();
            }
        });
        this.mainPanel.add(this.noti);
        this.mainPanel.add(this.usernameField);
        this.mainPanel.add(this.passwordField);
        this.mainPanel.add(this.loginButton);
        this.mainPanel.add(this.createAccount);
        this.add(this.mainPanel);
    }

    private void authenticate() {
        String username = this.usernameField.getText();
        String password = new String(this.passwordField.getPassword());
        UserDao userDao = new UserDao();
        User user = userDao.login(username, password);
        if (user != null) {
            UserSession.setCurrentUserUid(user.getUid());
            userDao.updateStatus(user.getUid(), 1);
            (new MainView()).setVisible(true);
            this.dispose();
        } else {
            this.noti.setText("Username or Password incorrect");
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new LoginView()).setVisible(true);
            }
        });
    }
}
