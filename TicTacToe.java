import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    // ===== VARIABLES =====
    private JButton[][] buttons = new JButton[3][3];

    private JLabel statusLabel;
    private JLabel scoreLabel;

    private JButton restartButton;
    private JButton newGameButton;
    private JButton exitButton;

    private boolean xTurn = true;

    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;

    // ===== CONSTRUCTOR =====
    public TicTacToe() {

        setTitle("Tic Tac Toe");
        setSize(550,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel(new GridLayout(2,1));
        topPanel.setBackground(new Color(40,40,40));

        statusLabel = new JLabel("Current Turn : X",
                SwingConstants.CENTER);

        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial",Font.BOLD,22));

        scoreLabel = new JLabel(
                "X Wins : 0     O Wins : 0     Draw : 0",
                SwingConstants.CENTER);

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial",Font.PLAIN,18));

        topPanel.add(statusLabel);
        topPanel.add(scoreLabel);

        add(topPanel,BorderLayout.NORTH);

        // ---------- BOARD ----------
        JPanel board = new JPanel(new GridLayout(3,3,5,5));

        board.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10));

        board.setBackground(Color.BLACK);

        Font font = new Font("Arial",Font.BOLD,60);

        for(int i=0;i<3;i++){

            for(int j=0;j<3;j++){

                buttons[i][j]=new JButton();

                buttons[i][j].setFont(font);

                buttons[i][j].setFocusPainted(false);

                buttons[i][j].setBackground(Color.WHITE);

                buttons[i][j].addActionListener(e->{

                    JButton button=(JButton)e.getSource();

                    if(!button.getText().equals(""))
                        return;

                    if(xTurn){

                        button.setText("X");

                    }else{

                        button.setText("O");

                    }

                    // Part 2 se continue...

                                        // Winner Check
                    if(checkWinner()){
                        return;
                    }

                    // Draw Check
                    if(checkDraw()){

                        draws++;
                        updateScore();

                        JOptionPane.showMessageDialog(
                                this,
                                "Match Draw!"
                        );

                        disableBoard();

                        return;
                    }

                    // Change Turn
                    xTurn = !xTurn;

                    statusLabel.setText(
                            "Current Turn : " + (xTurn ? "X" : "O")
                    );

                });

                board.add(buttons[i][j]);

            }

        }

        add(board, BorderLayout.CENTER);

        // ---------- BOTTOM PANEL ----------

        JPanel bottomPanel = new JPanel();

        restartButton = new JButton("Restart");

        newGameButton = new JButton("New Game");

        exitButton = new JButton("Exit");

        bottomPanel.add(restartButton);
        bottomPanel.add(newGameButton);
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Restart Button
        restartButton.addActionListener(e -> {

            resetBoard();

        });

        // New Game Button
        newGameButton.addActionListener(e -> {

            xWins = 0;
            oWins = 0;
            draws = 0;

            updateScore();

            resetBoard();

        });

        // Exit Button
        exitButton.addActionListener(e -> {

            dispose();

            new HomeScreen();

        });

        setVisible(true);

    }

    // ==========================
    // RESET BOARD
    // ==========================

    private void resetBoard(){

        xTurn = true;

        statusLabel.setText("Current Turn : X");

        for(int i=0;i<3;i++){

            for(int j=0;j<3;j++){

                buttons[i][j].setText("");

                buttons[i][j].setEnabled(true);

                buttons[i][j].setBackground(Color.WHITE);

            }

        }

    }

        // ==========================
    // CHECK WINNER
    // ==========================

    private boolean checkWinner(){

        // -------- ROWS --------
        for(int i=0;i<3;i++){

            if(!buttons[i][0].getText().equals("") &&
               buttons[i][0].getText().equals(buttons[i][1].getText()) &&
               buttons[i][1].getText().equals(buttons[i][2].getText())){

                String winner = buttons[i][0].getText();

                if(winner.equals("X"))
                    xWins++;
                else
                    oWins++;

                updateScore();

                statusLabel.setText("Winner : " + winner);

                highlightWinner(
                        buttons[i][0],
                        buttons[i][1],
                        buttons[i][2]);

                JOptionPane.showMessageDialog(
                        this,
                        winner + " Wins!"
                );

                disableBoard();

                return true;

            }

        }

        // -------- COLUMNS --------
        for(int j=0;j<3;j++){

            if(!buttons[0][j].getText().equals("") &&
               buttons[0][j].getText().equals(buttons[1][j].getText()) &&
               buttons[1][j].getText().equals(buttons[2][j].getText())){

                String winner = buttons[0][j].getText();

                if(winner.equals("X"))
                    xWins++;
                else
                    oWins++;

                updateScore();

                statusLabel.setText("Winner : " + winner);

                highlightWinner(
                        buttons[0][j],
                        buttons[1][j],
                        buttons[2][j]);

                JOptionPane.showMessageDialog(
                        this,
                        winner + " Wins!"
                );

                disableBoard();

                return true;

            }

        }

        // Main Diagonal Part 4 me continue...

                // -------- MAIN DIAGONAL --------
        if(!buttons[0][0].getText().equals("") &&
           buttons[0][0].getText().equals(buttons[1][1].getText()) &&
           buttons[1][1].getText().equals(buttons[2][2].getText())){

            String winner = buttons[0][0].getText();

            if(winner.equals("X"))
                xWins++;
            else
                oWins++;

            updateScore();

            statusLabel.setText("Winner : " + winner);

            highlightWinner(buttons[0][0], buttons[1][1], buttons[2][2]);

            JOptionPane.showMessageDialog(this, winner + " Wins!");

            disableBoard();

            return true;
        }

        // -------- SECOND DIAGONAL --------
        if(!buttons[0][2].getText().equals("") &&
           buttons[0][2].getText().equals(buttons[1][1].getText()) &&
           buttons[1][1].getText().equals(buttons[2][0].getText())){

            String winner = buttons[0][2].getText();

            if(winner.equals("X"))
                xWins++;
            else
                oWins++;

            updateScore();

            statusLabel.setText("Winner : " + winner);

            highlightWinner(buttons[0][2], buttons[1][1], buttons[2][0]);

            JOptionPane.showMessageDialog(this, winner + " Wins!");

            disableBoard();

            return true;
        }

        return false;
    }

    // ==========================
    // HIGHLIGHT WINNER
    // ==========================

    private void highlightWinner(JButton a, JButton b, JButton c){

        a.setBackground(Color.GREEN);
        b.setBackground(Color.GREEN);
        c.setBackground(Color.GREEN);

    }

    // ==========================
    // DISABLE BOARD
    // ==========================

    private void disableBoard(){

        for(int i=0;i<3;i++){

            for(int j=0;j<3;j++){

                buttons[i][j].setEnabled(false);

            }

        }

    }

    // ==========================
    // CHECK DRAW
    // ==========================

    private boolean checkDraw(){

        for(int i=0;i<3;i++){

            for(int j=0;j<3;j++){

                if(buttons[i][j].getText().equals(""))
                    return false;

            }

        }

        return true;

    }

    // ==========================
    // UPDATE SCORE
    // ==========================

    private void updateScore(){

        scoreLabel.setText(
                "X Wins : " + xWins +
                "     O Wins : " + oWins +
                "     Draw : " + draws
        );

    }

}