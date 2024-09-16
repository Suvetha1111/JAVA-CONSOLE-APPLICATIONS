
import java.util.Scanner;

public class Tic_tac_toe
{
    static char[] board={'0','1','2','3','4','5','6','7','8','9'};
    static void designBoard()
    {
        System.out.println("\n--->TIC TAC TOE GAME<<--\n");
        System.out.println("\t____|____|____|");
        System.out.println("\t    |    |    ");
        System.out.printf("\t  %c |  %c |  %c  \n",board[1],board[2],board[3]);
        System.out.println("\t____|____|____|");
        System.out.println("\t    |    |    ");
        System.out.printf( "\t  %c |  %c |  %c  \n",board[4],board[5],board[6]);
        System.out.println("\t____|____|____|");

        System.out.println("\t    |    |    ");
        System.out.printf("\t  %c |  %c |  %c  \n",board[7],board[8],board[9]);
        System.out.println("\t____|____|____|");
    
    } 
    static int checkWin()
    {
        if(board[1]==board[2]&&board[2]==board[3])
        {
            return 1;
        }
        else if(board[4]==board[5]&&board[5]==board[6])
        {
            return 1;
        }
        else if(board[7]==board[8]&&board[8]==board[9])
        {
            return 1;
        }
        else if(board[1]==board[4]&&board[4]==board[7])
        {
            return 1;
        }
        else if(board[2]==board[5]&&board[5]==board[8])
        {
            return 1;
        }
        else if(board[3]==board[6]&&board[6]==board[9])
        {
            return 1;
        }
        else if(board[1]==board[5]&&board[5]==board[9])
        {
            return 1;
        }
        else if(board[3]==board[5]&&board[5]==board[7])
        {
            return 1;
        }
        for(int i=1;i<=9;i++)
        {
            if(board[i]!='X'&&board[i]!='O')
            {
                return -1;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println("\n TIC TAC TOE");
    
        int input;
        int player=1;
        Scanner sc=new Scanner(System.in);
        while (true) { 
            player=(player%2==0)?2:1;
            designBoard();
            System.out.printf("\nEnter no. for player %d:",player);
        
        if(!sc.hasNext())
{
    System.out.println("Invalid input.Ennter a number.");
    continue;
}
        input=sc.nextInt();
        if(input<1||input>9||(board[input]=='X'||board[input]=='O'))
        {
            System.out.println("Invalid input.Ennter a valid position");
           continue;
        }
        char mark=(player==1)?'X':'O';
        board[input]=mark;
        int result=checkWin();
        if(result==1)
        {
            designBoard();
            System.out.printf("\t\n Player %d Wins \n",player);
            break;
        }
        else if(result==0){
            designBoard();
            System.out.println("Match draw !!!");
            break;
        }
        player++;
    }  
 
}
}
------------------------------------------------------------------------------------------------
    for any number of square matrix
    import java.util.Scanner;

public class TicTacToe {
    static char[][] board;
    static int boardSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the board (n x n): ");
        boardSize = sc.nextInt();
        board = new char[boardSize][boardSize];
        initializeBoard();

        int player = 1;
        while (true) {
            player = (player % 2 == 0) ? 2 : 1;
            designBoard();
            System.out.printf("\nEnter row and column (1 to %d) for player %d (row col): ", boardSize, player);

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter numbers.");
                sc.next(); // Clear invalid input
                continue;
            }
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            if (row < 0 || row >= boardSize || col < 0 || col >= boardSize || (board[row][col] == 'X' || board[row][col] == 'O')) {
                System.out.println("Invalid input. Enter a valid position.");
                continue;
            }

            char mark = (player == 1) ? 'X' : 'O';
            board[row][col] = mark;

            int result = checkWin();
            if (result == 1) {
                designBoard();
                System.out.printf("\nPlayer %d Wins!\n", player);
                break;
            } else if (result == 0) {
                designBoard();
                System.out.println("Match draw!");
                break;
            }
            player++;
        }
        sc.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = (char) ('1' + i * boardSize + j);
            }
        }
    }

    static void designBoard() {
        System.out.println("\n--->TIC TAC TOE GAME<<--\n");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print("\t" + board[i][j] + " ");
                if (j < boardSize - 1) System.out.print("|");
            }
            System.out.println();
            if (i < boardSize - 1) {
                System.out.println("\t" + "-".repeat(boardSize * 2 - 1));
            }
        }
        System.out.println();
    }

    static int checkWin() {
        // Check rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return 1;
            }
        }

        // Check diagonals
        if (checkMainDiagonal() || checkAntiDiagonal()) {
            return 1;
        }

        // Check for draw
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return -1; // Game is still ongoing
                }
            }
        }
        return 0; // Draw
    }

    static boolean checkRow(int row) {
        char first = board[row][0];
        if (first == 'X' || first == 'O') {
            for (int col = 1; col < boardSize; col++) {
                if (board[row][col] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static boolean checkColumn(int col) {
        char first = board[0][col];
        if (first == 'X' || first == 'O') {
            for (int row = 1; row < boardSize; row++) {
                if (board[row][col] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static boolean checkMainDiagonal() {
        char first = board[0][0];
        if (first == 'X' || first == 'O') {
            for (int i = 1; i < boardSize; i++) {
                if (board[i][i] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static boolean checkAntiDiagonal() {
        char first = board[0][boardSize - 1];
        if (first == 'X' || first == 'O') {
            for (int i = 1; i < boardSize; i++) {
                if (board[i][boardSize - 1 - i] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

