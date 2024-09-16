/*
Current Board:
   0 1 2 3 4 
0 | ? ? ? ? ? 
1 | ? ? ? ? ? 
2 | ? ? ? ? ? 
3 | ? ? ? ? ? 
4 | ? ? ? ? ? 
Flags remaining: 5

Enter your move (row col action):
Action: 0 = Open cell, 1 = Flag cell
Row: 3
Column: 3
Action (0=Open, 1=Flag): 0

Current Board:
   0 1 2 3 4
0 | ? ? ? ? ?
1 | ? ? ? ? ?
2 | ? ? ? ? ?
3 | ? ? ? 1 ?
4 | ? ? ? ? ? 
Flags remaining: 5

Enter your move (row col action):
Action: 0 = Open cell, 1 = Flag cell
Row: 4
Column: 4
Action (0=Open, 1=Flag): 0

Current Board:
   0 1 2 3 4
0 | ? ? ? ? ?
1 | ? ? ? ? ?
2 | ? ? ? ? ?
3 | ? ? ? 1 ?
4 | ? ? ? ? 1
Flags remaining: 5

Enter your move (row col action):
Action: 0 = Open cell, 1 = Flag cell
Row: 2
Column: 2
Action (0=Open, 1=Flag): 0

Current Board:
   0 1 2 3 4
0 | ? ? ? ? ?
1 | ? 2 1 1 ?
2 | ? 1   1 ?
3 | ? 2   1 ? 
4 | ? 1   1 1
Flags remaining: 5

Enter your move (row col action):
Action: 0 = Open cell, 1 = Flag cell
Row: 2
Column: 0
Action (0=Open, 1=Flag): 0

Minefield Layout:
   0 1 2 3 4
0 | M 1 1 M 1
1 | 2 2 1 1 1
2 | M 1   1 1
3 | 2 2   1 M
4 | M 1   1 1
Game Over! You hit a mine.




*/

import java.util.Random;
import java.util.Scanner;

class MineSweeper {

    private final int Size = 5;
    private final char[][] field = new char[Size][Size];
    private final char[][] board = new char[Size][Size];
    private final int mineCount = 5;
    private int flagCount = 0;

    public MineSweeper() {
        fillArray(field, ' ');
        fillArray(board, '?');
        setUpField();
    }

    private void fillArray(char[][] matrix, char fill) {
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                matrix[i][j] = fill;
            }
        }
    }

    private void setUpField() {
        int count = 0;
        Random rand = new Random();
        while (count < mineCount) {
            int row = rand.nextInt(Size);
            int col = rand.nextInt(Size);
            if (field[row][col] == ' ') {
                field[row][col] = 'M';
                fillAdjacencyMatrix(row, col);
                count++;
            }
        }
    }

    private void fillAdjacencyMatrix(int row, int col) {
        for (int i = Math.max(0, row - 1); i <= Math.min(Size - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(Size - 1, col + 1); j++) {
                if (field[i][j] != 'M') {
                    field[i][j] = field[i][j] == ' ' ? '1' : (char) (field[i][j] + 1);
                }
            }
        }
    }

    public boolean play(int row, int col, boolean flag) {
        if (isValid(row, col)) {
            if (flag) {
                toggleFlag(row, col);
            } else {
                if (!openCell(row, col)) {
                    displayField();  // Display full field when game is over
                    System.out.println("Game Over! You hit a mine.");
                    return false;
                }
            }
            displayBoard();
            if (isWin()) {
                System.out.println("Congratulations! You won the game.");
                return false;
            }
        } else {
            System.out.println("Invalid move! Please enter valid row and column.");
        }
        return true;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < Size && col >= 0 && col < Size;
    }

    private void toggleFlag(int row, int col) {
        if (board[row][col] == 'F') {
            board[row][col] = '?';
            flagCount--;
        } else if (board[row][col] == '?') {
            board[row][col] = 'F';
            flagCount++;
        }
    }

    private boolean openCell(int row, int col) {
        if (board[row][col] != '?') {
            return true;  // Already opened or flagged
        }
        if (field[row][col] == 'M') {
            return false;  // Hit a mine
        } else if (field[row][col] == ' ') {
            revealEmptyCells(row, col);
        } else {
            board[row][col] = field[row][col];  // Open numbered cell
        }
        return true;
    }

    private void revealEmptyCells(int row, int col) {
        if (!isValid(row, col) || board[row][col] != '?' || field[row][col] == 'M') {
            return;
        }

        board[row][col] = field[row][col];
        if (field[row][col] == ' ') {
            for (int i = Math.max(0, row - 1); i <= Math.min(Size - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(Size - 1, col + 1); j++) {
                    revealEmptyCells(i, j);
                }
            }
        }
    }

    private boolean isWin() {
        int revealedCells = 0;
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                if (board[i][j] != '?' && board[i][j] != 'F') {
                    revealedCells++;
                }
            }
        }
        return revealedCells == (Size * Size - mineCount);  // All non-mined cells revealed
    }

    public void displayBoard() {
        System.out.println("\nCurrent Board:");
        System.out.print("   ");
        for (int i = 0; i < Size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < Size; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < Size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Flags remaining: " + (mineCount - flagCount));
    }

    public void displayField() {
        System.out.println("\nMinefield Layout:");
        System.out.print("   ");
        for (int i = 0; i < Size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < Size; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < Size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class MineSweeperGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MineSweeper game = new MineSweeper();
        boolean isPlaying = true;

        game.displayBoard();

        while (isPlaying) {
            System.out.println("\nEnter your move (row col action):");
            System.out.println("Action: 0 = Open cell, 1 = Flag cell");
            int row = -1, col = -1, action = -1;

            try {
                System.out.print("Row: ");
                row = scanner.nextInt();
                System.out.print("Column: ");
                col = scanner.nextInt();
                System.out.print("Action (0=Open, 1=Flag): ");
                action = scanner.nextInt();

                boolean flag = (action == 1);
                isPlaying = game.play(row, col, flag);
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numbers for row, column, and action.");
                scanner.next();  // Clear invalid input
            }
        }

      
    }
}
