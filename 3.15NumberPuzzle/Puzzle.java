import java.util.Random;

public class Puzzle {
    private int size;
    private int[][] grid;
    private int emptyRow, emptyCol;

    public Puzzle(int size) {
        this.size = size;
        this.grid = new int[size][size];
        initializePuzzle();
    }

    private void initializePuzzle() {
        int[] numbers = new int[size * size];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        Random rand = new Random();
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = numbers[index++];
                if (grid[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    public int getTile(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }

    public void moveTile(int newRow, int newCol) {
        grid[emptyRow][emptyCol] = grid[newRow][newCol];
        grid[newRow][newCol] = 0;
        emptyRow = newRow;
        emptyCol = newCol;
    }

    public boolean isSolved() {
        int correct = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) {
                    return grid[i][j] == 0;
                }
                if (grid[i][j] != correct++) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getEmptyRow() {
        return emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }
}
