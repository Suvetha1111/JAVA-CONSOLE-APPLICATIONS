public class Board {
    private final char[][] board;
    private final int size;
    private int flagCount = 0;

    public Board(int size) {
        this.size = size;
        this.board = new char[size][size];
        fillArray('?');
    }

    private void fillArray(char fill) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = fill;
            }
        }
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public void toggleFlag(int row, int col) {
        if (board[row][col] == 'F') {
            board[row][col] = '?';
            flagCount--;
        } else if (board[row][col] == '?') {
            board[row][col] = 'F';
            flagCount++;
        }
    }

    public boolean openCell(int row, int col, char cellContent) {
        if (board[row][col] != '?') {
            return true;  // Already opened or flagged
        }
        board[row][col] = cellContent;
        return cellContent != 'M';
    }

    public void revealEmptyCells(int row, int col, MineField field) {
        if (!isValid(row, col) || board[row][col] != '?' || field.getCell(row, col) == 'M') {
            return;
        }

        board[row][col] = field.getCell(row, col);
        if (field.getCell(row, col) == ' ') {
            for (int i = Math.max(0, row - 1); i <= Math.min(size - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(size - 1, col + 1); j++) {
                    revealEmptyCells(i, j, field);
                }
            }
        }
    }

    public void displayBoard() {
        System.out.println("\nCurrent Board:");
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getFlagCount() {
        return flagCount;
    }

    public char getBoardCell(int row, int col) {
        return board[row][col];
    }
}
