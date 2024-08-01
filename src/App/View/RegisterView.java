package App.View;

import App.Constants;
import App.Users.*;
import App.Components.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegisterView extends JFrame {
    private AppJTextFieldComponent usernameField;
    private AppJPasswordFieldComponent passwordField;
    private AppJPasswordFieldComponent confirmPasswordField;
    private JComboBox<String> selectBox;
    private JButton regButton;
    private JButton backButton;
    private JLabel noti;
    private AppJPanelComponent mainPanel;

    public RegisterView() {
        this.setTitle("Register");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setLayout((LayoutManager)null);
        this.mainPanel = new AppJPanelComponent(Constants.LOGIN_BACKGROUND, Constants.LOGIN_SHADOW, 30, 1.0F);
        this.mainPanel.setLayout((LayoutManager)null);
        int mPanelW = 300;
        int mPanelH = 280;
        this.mainPanel.setBounds(200, 100, mPanelW, mPanelH);
        this.noti = new JLabel("", 0);
        this.noti.setBounds(50, 20, 200, 15);
        this.noti.setFont(new Font("Arial", 0, 11));
        this.usernameField = new AppJTextFieldComponent("Username", Constants.LOGIN_BACKGROUND, Constants.BORDER_COLOR);
        this.usernameField.setBounds(50, 30, 200, 30);
        this.passwordField = new AppJPasswordFieldComponent("Password", Constants.LOGIN_BACKGROUND, Constants.BORDER_COLOR);
        this.passwordField.setBounds(50, 60, 200, 30);
        this.confirmPasswordField = new AppJPasswordFieldComponent("Password Confirm", Constants.LOGIN_BACKGROUND, Constants.BORDER_COLOR);
        this.confirmPasswordField.setBounds(50, 90, 200, 30);
        JPanel boxloaitk = new JPanel();
        boxloaitk.setLayout(new GridLayout(1, 2));
        JLabel pl = new JLabel("Loại TK: ");
        String[] phanloai = new String[]{"Admin", "Sinh Viên", "Giảng Viên", "Ngoại Lai"};
        this.selectBox = new JComboBox(phanloai);
        this.selectBox.setBorder(new EmptyBorder(5, 5, 5, 5));
        boxloaitk.add(pl);
        boxloaitk.add(this.selectBox);
        boxloaitk.setBounds(50, 140, 200, 30);
        boxloaitk.setBackground(Color.WHITE);
        this.regButton = new JButton("Register");
        this.regButton.setBounds(50, 180, 200, 30);
        this.regButton.setFocusPainted(false);
        this.regButton.setBorderPainted(false);
        this.regButton.setFont(new Font("Arial", 0, 13));
        this.regButton.setBackground(Color.CYAN);
        this.regButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterView.this.register();
            }
        });
        this.backButton = new JButton("Back");
        this.backButton.setBounds(50, 220, 200, 30);
        this.backButton.setFocusPainted(false);
        this.backButton.setBorderPainted(false);
        this.backButton.setFont(new Font("Arial", 0, 13));
        this.backButton.setBackground(Color.CYAN);
        this.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                (new LoginView()).setVisible(true);
                RegisterView.this.dispose();
            }
        });
        this.mainPanel.add(this.noti);
        this.mainPanel.add(this.usernameField);
        this.mainPanel.add(this.passwordField);
        this.mainPanel.add(this.confirmPasswordField);
        this.mainPanel.add(boxloaitk);
        this.mainPanel.add(this.regButton);
        this.mainPanel.add(this.backButton);
        this.add(this.mainPanel);
    }

    private void register() {
        String username = this.usernameField.getText();
        String password = this.passwordField.getPassword();
        String confirmPassword = this.confirmPasswordField.getPassword();
        String loaitk = (String)this.selectBox.getSelectedItem();
        UserDao userDao = new UserDao();
        if (!username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && !loaitk.isEmpty()) {
            Integer check = userDao.register(username, password, loaitk);
            if (password.equals(confirmPassword)) {
                if (check == 1) {
                    this.noti.setText("Đăng ký thành công");
                    this.noti.setForeground(Constants.NOTI_SUCCESS);
                    (new LoginView()).setVisible(true);
                    this.dispose();
                } else if (check == 0) {
                    this.noti.setText("Đăng ký thất bại");
                    this.noti.setForeground(Constants.NOTI_FAILED);
                } else if (check == -1) {
                    this.noti.setText("Username đã có người sử dụng");
                    this.noti.setForeground(Constants.NOTI_EMPTY);
                }
            } else {
                this.noti.setText("Password confirm incorrect");
                this.noti.setForeground(Constants.NOTI_FAILED);
            }
        } else {
            this.noti.setText("Không được để trống");
            this.noti.setForeground(Constants.NOTI_EMPTY);
        }

    }
}
