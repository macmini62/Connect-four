import java.util.*;

public class main{
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static char[][] board = new char[ROWS][COLS];
    public static char player = 'O';

    public static void main(String[] args) {
        createBoard();

        Scanner scanner = new Scanner(System.in);
        boolean boardFull = false;
        boolean gameWon = false;

        while(!boardFull && !gameWon){
            displayBoard();
            int playerColumn = getPlayersColumn();
            dropBall(playerColumn);

            boardFull = isFull();
            gameWon = checkWinner();
            switchPlayer();
        }

        displayBoard();

        if(gameWon){
            switchPlayer();
            System.out.println("Player "+player+" Wins!!");
        }
        else{
            System.out.println("No player won that round...Rematch?");
        }

        scanner.close();
    }

    private static void createBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard(){
        for(int i = 0; i < ROWS; i++){
            System.out.print("| ");
            for(int j = 0; j < COLS; j++){
                System.out.print(board[i][j] +" | ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }

    private static int getPlayersColumn(){
        Scanner scanner = new Scanner(System.in);
            int column;
            do{
                System.out.println();
                System.out.print("Player " +player+ " enter the column to drop your ball (1-7): ");
                column = scanner.nextInt() - 1;
            }while(column < 0 || column >= COLS || board[0][column] != ' ');
            
            return column;
    }

    private static void dropBall(int column){
        for(int i = ROWS-1; i >= 0; i--){
            if(board[i][column] == ' '){
                board[i][column] = player;
                break;
            }
        }
    }

    private static void switchPlayer(){
        player = (player == 'O') ? 'X' : 'O';
    }

    private static boolean checkWinner(){
        int count = 0;

        for(int i = ROWS-1; i >= 0; i--){
            for(int j = 0; j < COLS; j++){
                if(board[i][j] == player){
                    count++;
                    if(count == 4) return true;
                }else{
                    count = 0;
                }
            }
        }
        
        return false;
    }

    private static boolean isFull(){
        for(int i = 0; i < COLS; i++){
            if(board[0][i] == ' '){
                return false;
            }
        }

        return true;
    }
}