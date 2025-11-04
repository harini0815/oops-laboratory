//Harini M
//2117240070105
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleWizard extends JFrame {
    CardLayout cl = new CardLayout();
    JPanel panel = new JPanel(cl);
    JTextField name = new JTextField(), email = new JTextField(), age = new JTextField();

    public SimpleWizard() {
        setTitle("Multi-Step Wizard");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Step 1
        JPanel p1 = new JPanel(new GridLayout(3, 1));
        p1.add(new JLabel("Step 1: Enter Name"));
        p1.add(name);
        JButton n1 = new JButton("Next");
        p1.add(n1);

        // Step 2
        JPanel p2 = new JPanel(new GridLayout(4, 1));
        p2.add(new JLabel("Step 2: Enter Email"));
        p2.add(email);
        JButton b2 = new JButton("Back"), n2 = new JButton("Next");
        JPanel p2btn = new JPanel(); p2btn.add(b2); p2btn.add(n2);
        p2.add(p2btn);

        // Step 3
        JPanel p3 = new JPanel(new GridLayout(4, 1));
        p3.add(new JLabel("Step 3: Enter Age"));
        p3.add(age);
        JButton b3 = new JButton("Back"), finish = new JButton("Finish");
        JPanel p3btn = new JPanel(); p3btn.add(b3); p3btn.add(finish);
        p3.add(p3btn);

        // Add panels
        panel.add(p1, "1");
        panel.add(p2, "2");
        panel.add(p3, "3");
        add(panel);

        // Button actions
        n1.addActionListener(e -> {
            if (name.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(this, "Name required!");
            else cl.show(panel, "2");
        });
        b2.addActionListener(e -> cl.show(panel, "1"));
        n2.addActionListener(e -> {
            String em = email.getText();
            if (!em.contains("@") || !em.contains("."))
                JOptionPane.showMessageDialog(this, "Invalid email!");
            else cl.show(panel, "3");
        });
        b3.addActionListener(e -> cl.show(panel, "2"));
        finish.addActionListener(e -> {
            try {
                int a = Integer.parseInt(age.getText());
                if (a <= 0) throw new Exception();
                JOptionPane.showMessageDialog(this, "✅ Submitted!\nName: " + name.getText() +
                        "\nEmail: " + email.getText() + "\nAge: " + a);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid age!");
            }
        });
    }

    public static void main(String[] args) {
        new SimpleWizard().setVisible(true);
    }
}
