package org.tairobea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private JFrame jFrame;
    private JPanel panel;
    private JPanel textPanel;
    private JButton[][] grid;
    private boolean playerX = true;
    private JLabel jLabel;

    private int turns = 0;

    public TicTacToe() {
        jFrame = new JFrame("TicTacToe");

        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(400, 400);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        jLabel = new JLabel();
        jLabel.setBackground(Color.BLUE);
        jLabel.setForeground(Color.white);
        jLabel.setFont(new Font("Arial", Font.BOLD, 30));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setText("Tic-Tac-Toe");
        jLabel.setHorizontalAlignment(JLabel.CENTER);

        jLabel.setOpaque(true);
        textPanel = new JPanel();
        textPanel.setBackground(Color.blue);
        textPanel.add(jLabel, BorderLayout.CENTER);
        jFrame.add(textPanel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBackground(Color.darkGray);
        jFrame.add(panel, BorderLayout.CENTER);
        setGrid();

        JButton resetButton = new JButton("reset");
        resetButton.addActionListener((e) -> {
            reset();
        });
        JPanel resetPanel = new JPanel();
        resetPanel.add(resetButton);
        resetPanel.setBackground(Color.BLUE);
        jFrame.add(resetPanel, BorderLayout.SOUTH);
        jFrame.setVisible(true);

    }

    private void setGrid() {
        int rows = 3;
        int cols = 3;
        grid = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton button = new JButton();

                grid[i][j] = button;
                panel.add(button);
                button.setForeground(Color.blue);
                button.setBackground(Color.BLACK);

                button.setFont(new Font("Arial", Font.BOLD, 35));
                button.setText("");
                button.setVisible(true);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        performGameLogic(e);
                    }
                });
            }
        }

    }

    private void performGameLogic(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (!button.getText().isBlank()) {
            return;
        }

        String player = playerX ? "X" : "O";

        button.setText(player);
        turns++;
        if (checkWinner()){

            jLabel.setText("Player " + player + "won");
            disableInput();
            return;
        }

        if (turns == 9){
            jLabel.setText("It's a draw");
            disableInput();
            return;
        }

        playerX = !playerX;
        player = playerX ? "X" : "O";
        jLabel.setText("Player " + player + "'s turn");
    }

    private void reset() {
        turns = 0;
        playerX = true;
        int rows = 3;
        int cols = 3;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].setText("");
                grid[i][j].setEnabled(true);
            }
        }
        jLabel.setText("Tic-Tac-Toe");
    }

    private boolean checkWinner() {
        int rows = 3;
        int cols = 3;
        for (int i = 0; i < rows; i++) {
            if (!grid[i][0].getText().isBlank() && grid[i][0].getText().equals(grid[i][1].getText()) && grid[i][1].getText().equals(grid[i][2].getText())) {
                return true;
            }
        }

        for (int j = 0; j < cols; j++) {
            if (!grid[0][j].getText().isBlank() && grid[0][j].getText().equals(grid[1][j].getText()) && grid[1][j].getText().equals(grid[2][j].getText())) {
                return true;
            }
        }

        if (!grid[0][0].getText().isBlank() && grid[0][0].getText().equals(grid[1][1].getText()) && grid[1][1].getText().equals(grid[2][2].getText())){
            return true;
        }

        if (!grid[2][0].getText().isBlank() && grid[2][0].getText().equals(grid[1][1].getText()) && grid[1][1].getText().equals(grid[0][2].getText())){
            return true;
        }

        return false;
    }

    private void disableInput(){
        int rows = 3;
        int cols = 3;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                grid[i][j].setEnabled(false);
            }
        }
    }

}