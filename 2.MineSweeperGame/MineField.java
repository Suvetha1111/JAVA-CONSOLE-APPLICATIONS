import java.util.Random;

public class MineField {
    private final int size;
    private final char[][] field;
    private final int mineCount;

    public MineField(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.field = new char[size][size];
        fillArray(' ');
        setUpField();
    }

    private void fillArray(char fill) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = fill;
            }
        }
    }

    private void setUpField() {
        int count = 0;
        Random rand = new Random();
        while (count < mineCount) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if (field[row][col] == ' ') {
                field[row][col] = 'M';
                fillAdjacencyMatrix(row, col);
                count++;
            }
        }
    }

    private void fillAdjacencyMatrix(int row, int col) {
        for (int i = Math.max(0, row - 1); i <= Math.min(size - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(size - 1, col + 1); j++) {
                if (field[i][j] != 'M') {
                    field[i][j] = field[i][j] == ' ' ? '1' : (char) (field[i][j] + 1);
                }
            }
        }
    }

    public char getCell(int row, int col) {
        return field[row][col];
    }

    public int getSize() {
        return size;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void displayField() {
        System.out.println("\nMinefield Layout:");
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}
