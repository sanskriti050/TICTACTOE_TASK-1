import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JFrame {

    public HomeScreen() {

        setTitle("Tic Tac Toe");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("TIC TAC TOE");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton friend = new JButton("Play With Friend");
        JButton exit = new JButton("Exit");

        JButton[] buttons = {friend, exit};

        for (JButton b : buttons) {

            b.setMaximumSize(new Dimension(250, 50));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFont(new Font("Arial", Font.BOLD, 18));

        }

        panel.add(Box.createVerticalGlue());

        panel.add(title);
        panel.add(Box.createVerticalStrut(50));

        panel.add(friend);
        panel.add(Box.createVerticalStrut(20));

        panel.add(exit);

        panel.add(Box.createVerticalGlue());

        add(panel);

        // Play With Friend
        friend.addActionListener(e -> {

            dispose();
            new TicTacToe();

        });

        // Exit
        exit.addActionListener(e -> System.exit(0));

        setVisible(true);

    }

}