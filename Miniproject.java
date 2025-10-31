package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Movieclient extends JFrame {

    CardLayout cardLayout;
    JPanel mainPanel;

    public Movieclient() {
        setTitle("🎬 Movie Library System");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add all screens
        mainPanel.add(new WelcomePanel(), "Welcome");
        mainPanel.add(new LoginPanel(), "Login");
        mainPanel.add(new RegisterPanel(), "Register");
        mainPanel.add(new MenuPanel(), "Menu");

        add(mainPanel);
        setVisible(true);
    }

    // ---------- Welcome Screen ----------
    class WelcomePanel extends JPanel {
        public WelcomePanel() {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);

            JPanel header = new JPanel();
            header.setBackground(new Color(255, 87, 34));
            JLabel title = new JLabel("🎬 Welcome to Movie Library");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setForeground(Color.WHITE);
            header.add(title);

            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(2, 1, 15, 15));
            buttons.setBackground(Color.WHITE);
            buttons.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

            JButton loginBtn = new JButton("Login");
            loginBtn.setBackground(new Color(56, 142, 60));
            loginBtn.setForeground(Color.WHITE);
            loginBtn.setFont(new Font("Arial", Font.BOLD, 16));

            JButton registerBtn = new JButton("Register");
            registerBtn.setBackground(new Color(25, 118, 210));
            registerBtn.setForeground(Color.WHITE);
            registerBtn.setFont(new Font("Arial", Font.BOLD, 16));

            buttons.add(loginBtn);
            buttons.add(registerBtn);

            add(header, BorderLayout.NORTH);
            add(buttons, BorderLayout.CENTER);

            loginBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
            registerBtn.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        }
    }

    // ---------- Login Screen ----------
    class LoginPanel extends JPanel {
        public LoginPanel() {
            setLayout(null);
            setBackground(Color.WHITE);

            JLabel title = new JLabel("Login to Movie Library");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setBounds(110, 20, 250, 30);
            add(title);

            JLabel userLabel = new JLabel("Username:");
            userLabel.setBounds(60, 80, 100, 25);
            add(userLabel);
            JTextField userField = new JTextField();
            userField.setBounds(160, 80, 180, 25);
            add(userField);

            JLabel passLabel = new JLabel("Password:");
            passLabel.setBounds(60, 120, 100, 25);
            add(passLabel);
            JPasswordField passField = new JPasswordField();
            passField.setBounds(160, 120, 180, 25);
            add(passField);

            JButton loginBtn = new JButton("Login");
            loginBtn.setBounds(100, 180, 100, 30);
            loginBtn.setBackground(new Color(56, 142, 60));
            loginBtn.setForeground(Color.WHITE);
            add(loginBtn);

            JButton backBtn = new JButton("Back");
            backBtn.setBounds(220, 180, 100, 30);
            add(backBtn);

            loginBtn.addActionListener(e -> {
                String user = userField.getText().trim();
                String pass = new String(passField.getPassword());
                if (!user.isEmpty() && !pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "✅ Login Successful (Demo Mode)");
                    cardLayout.show(mainPanel, "Menu");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            backBtn.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        }
    }

    // ---------- Registration Screen ----------
    class RegisterPanel extends JPanel {
        public RegisterPanel() {
            setLayout(null);
            setBackground(Color.WHITE);

            JLabel title = new JLabel("Register New User");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setBounds(120, 20, 250, 30);
            add(title);

            JLabel userLabel = new JLabel("New Username:");
            userLabel.setBounds(60, 80, 120, 25);
            add(userLabel);
            JTextField userField = new JTextField();
            userField.setBounds(180, 80, 180, 25);
            add(userField);

            JLabel passLabel = new JLabel("Password:");
            passLabel.setBounds(60, 120, 120, 25);
            add(passLabel);
            JPasswordField passField = new JPasswordField();
            passField.setBounds(180, 120, 180, 25);
            add(passField);

            JButton registerBtn = new JButton("Register");
            registerBtn.setBounds(100, 180, 100, 30);
            registerBtn.setBackground(new Color(25, 118, 210));
            registerBtn.setForeground(Color.WHITE);
            add(registerBtn);

            JButton backBtn = new JButton("Back");
            backBtn.setBounds(220, 180, 100, 30);
            add(backBtn);

            registerBtn.addActionListener(e -> {
                String user = userField.getText().trim();
                String pass = new String(passField.getPassword());
                if (!user.isEmpty() && !pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "✅ Registration Successful (Demo Mode)");
                    cardLayout.show(mainPanel, "Login");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            backBtn.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        }
    }

    // ---------- Movie Menu Screen ----------
    class MenuPanel extends JPanel {
        public MenuPanel() {
            setLayout(null);
            setBackground(Color.WHITE);

            JPanel header = new JPanel();
            header.setBackground(new Color(255, 87, 34));
            header.setBounds(0, 0, 450, 50);

            JLabel title = new JLabel("🎬 MOVIE MENU");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setForeground(Color.WHITE);
            header.add(title);

            JTextArea movieArea = new JTextArea(
                "Available Movies:\n\n" +
                "1. Inception        ₹199\n" +
                "2. Interstellar     ₹249\n" +
                "3. The Dark Knight  ₹179\n" +
                "4. Tenet            ₹199\n" +
                "5. Dunkirk          ₹149\n"
            );
            movieArea.setEditable(false);
            movieArea.setBounds(80, 70, 300, 150);
            movieArea.setFont(new Font("Arial", Font.PLAIN, 14));

            JLabel idLabel = new JLabel("Movie ID:");
            idLabel.setBounds(80, 230, 100, 25);
            JTextField idField = new JTextField();
            idField.setBounds(150, 230, 60, 25);

            JLabel qtyLabel = new JLabel("Quantity:");
            qtyLabel.setBounds(220, 230, 80, 25);
            JTextField qtyField = new JTextField();
            qtyField.setBounds(290, 230, 60, 25);

            JButton orderBtn = new JButton("Place Order");
            orderBtn.setBounds(100, 280, 120, 35);
            orderBtn.setBackground(new Color(255, 193, 7));

            JButton logoutBtn = new JButton("Logout");
            logoutBtn.setBounds(240, 280, 120, 35);
            logoutBtn.setBackground(new Color(229, 57, 53));
            logoutBtn.setForeground(Color.WHITE);

            add(header);
            add(movieArea);
            add(idLabel); add(idField);
            add(qtyLabel); add(qtyField);
            add(orderBtn);
            add(logoutBtn);

            orderBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "✅ Order placed successfully!"));
            logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Movieclient::new);
    }
}
