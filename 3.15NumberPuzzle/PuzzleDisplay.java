public class PuzzleDisplay {
    public void displayPuzzle(Puzzle puzzle) {
        int size = puzzle.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzle.getTile(i, j) == 0) {
                    System.out.print("   "); // Print empty space
                } else {
                    System.out.printf("%3d", puzzle.getTile(i, j));
                }
            }
            System.out.println();
        }
    }
}
