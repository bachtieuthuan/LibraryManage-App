package App.View;

import App.Users.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainView extends JFrame {
    public MainView() {
        this.setTitle("Main View");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainView.this.logout();
                (new LoginView()).setVisible(true);
            }
        });
        panel.add(btnLogout);
        this.add(panel, "Center");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public void logout() {
        UserDao userDao = new UserDao();
        Integer uid = UserSession.getCurrentUserUid();
        userDao.updateStatus(uid, 0);
        UserSession.clearSession();
        (new LoginView()).setVisible(true);
        this.dispose();
    }
}
