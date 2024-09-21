public class Game {
    private final MineField field;
    private final Board board;

    public Game(MineField field, Board board) {
        this.field = field;
        this.board = board;
    }

    public boolean play(int row, int col, boolean flag) {
        if (board.isValid(row, col)) {
            if (flag) {
                board.toggleFlag(row, col);
            } else {
                char cellContent = field.getCell(row, col);
                if (!board.openCell(row, col, cellContent)) {
                    field.displayField();
                    System.out.println("Game Over! You hit a mine.");
                    return false;
                }
                if (cellContent == ' ') {
                    board.revealEmptyCells(row, col, field);
                }
            }
            board.displayBoard();
            if (isWin()) {
                System.out.println("Congratulations! You won the game.");
                return false;
            }
        } else {
            System.out.println("Invalid move! Please enter valid row and column.");
        }
        return true;
    }

    private boolean isWin() {
        int revealedCells = 0;
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                if (board.getBoardCell(i, j) != '?' && board.getBoardCell(i, j) != 'F') {
                    revealedCells++;
                }
            }
        }
        return revealedCells == (field.getSize() * field.getSize() - field.getMineCount());
    }
}
