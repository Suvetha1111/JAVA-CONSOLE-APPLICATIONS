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

import java.util.Scanner;

public class MineSweeperGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 5;
        int mineCount = 5;
        
        MineField field = new MineField(size, mineCount);
        Board board = new Board(size);
        Game game = new Game(field, board);

        boolean isPlaying = true;
        board.displayBoard();

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
