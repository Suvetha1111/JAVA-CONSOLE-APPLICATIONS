/*
Enter puzzle size (e.g., 4 for 4x4 puzzle): 
3
  5  2  3
  1  6  4
  7  8   
Move (u=up, d=down, l=left, r=right): 
l
  5  2  3
  1  6  4
  7     8
Move (u=up, d=down, l=left, r=right): 
u
  5  2  3
  1     4
  7  6  8
Move (u=up, d=down, l=left, r=right): 
l
  5  2  3
     1  4
  7  6  8
Move (u=up, d=down, l=left, r=right):
u
     2  3
  5  1  4
  7  6  8
Move (u=up, d=down, l=left, r=right):
d
  5  2  3
     1  4
  7  6  8
Move (u=up, d=down, l=left, r=right):
d
  5  2  3
  7  1  4
     6  8
Move (u=up, d=down, l=left, r=right):
u
  5  2  3
     1  4
  7  6  8
Move (u=up, d=down, l=left, r=right):
u
     2  3
  5  1  4
  7  6  8
Move (u=up, d=down, l=left, r=right):
l
Invalid move!
     2  3
  5  1  4
  7  6  8
Move (u=up, d=down, l=left, r=right):
r
It continues until it is solved.
*/
import java.util.Scanner;

public class PuzzleGame {
    private Puzzle puzzle;
    private PuzzleDisplay display;

    public PuzzleGame(int size) {
        puzzle = new Puzzle(size);
        display = new PuzzleDisplay();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!puzzle.isSolved()) {
            display.displayPuzzle(puzzle);
            System.out.println("Move (u=up, d=down, l=left, r=right): ");
            char move = scanner.next().charAt(0);
            int newRow = puzzle.getEmptyRow();
            int newCol = puzzle.getEmptyCol();

            switch (move) {
                case 'u':
                    newRow--;
                    break; // move up
                case 'd':
                    newRow++;
                    break; // move down
                case 'l':
                    newCol--;
                    break; // move left
                case 'r':
                    newCol++;
                    break; // move right
                default:
                    System.out.println("Invalid move! Use u, d, l, r.");
                    continue;
            }

            // Check boundaries
            if (newRow >= 0 && newRow < puzzle.getSize() && newCol >= 0 && newCol < puzzle.getSize()) {
                puzzle.moveTile(newRow, newCol);
            } else {
                System.out.println("Invalid move!");
            }
        }
        display.displayPuzzle(puzzle);
        System.out.println("Congratulations! You solved the puzzle.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter puzzle size (e.g., 4 for 4x4 puzzle): ");
        int size = scanner.nextInt();

        PuzzleGame game = new PuzzleGame(size);
        game.play();
    }
}
