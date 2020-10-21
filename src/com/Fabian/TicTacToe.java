package com.Fabian;

public class TicTacToe {

    static final char PLAYER_0 = 'X';
    static final char PLAYER_1 = 'O';

    char[][] gameBoard = {{' ',' ',' '}, {' ',' ',' '}, {' ',' ',' '}};
    int player = (int)(Math.random() * 2);

    public TicTacToe(){

    }

    public int getPlayer(){
        return player;
    }

    public void changePlayer(){
        if (player == 0){
            player++;
        } else {
            player--;
        }
    }

    public boolean hasPlayerWon(){ //check all possible combinations if there is a winning one already

        for (int i = 0; i < 3; i++){
            if ((gameBoard[i][0] == PLAYER_0 && gameBoard[i][1] == PLAYER_0 && gameBoard[i][2] == PLAYER_0) ||
                    (gameBoard[i][0] == PLAYER_1 && gameBoard[i][1] == PLAYER_1 && gameBoard[i][2] == PLAYER_1)){
                return true;
            }
            if ((gameBoard[0][i] == PLAYER_0 && gameBoard[1][i] == PLAYER_0 && gameBoard[2][i] == PLAYER_0) ||
                    (gameBoard[0][i] == PLAYER_1 && gameBoard[1][i] == PLAYER_1 && gameBoard[2][i] == PLAYER_1)){
                return true;
            }
        }

        if ((gameBoard[0][0] == PLAYER_0 && gameBoard[1][1] == PLAYER_0 && gameBoard[2][2] == PLAYER_0) ||
                (gameBoard[0][0] == PLAYER_1 && gameBoard[1][1] == PLAYER_1 && gameBoard[2][2] == PLAYER_1)){
            return true;
        }
        if ((gameBoard[0][2] == PLAYER_0 && gameBoard[1][1] == PLAYER_0 && gameBoard[2][0] == PLAYER_0) ||
                (gameBoard[0][2] == PLAYER_1 && gameBoard[1][1] == PLAYER_1 && gameBoard[2][0] == PLAYER_1)){
            return true;
        }
        return false;
    }

    public boolean checkFreeFields(boolean print){
        int count = 0;
        for (int row = 0; row < 3; row++){
            for (int cell = 0; cell < 3; cell++){
                if(gameBoard[row][cell] == ' '){
                    if (print) {
                        System.out.printf("%d, ", cell + 1 + 3 * row);
                    }
                } else {
                    if (print) {
                        System.out.print("   ");
                    }
                    count++;
                }
            }
            if (print) {
                System.out.println(" ");
            }
        }
        if (count == 9){
            return true;
        }
        return false;
    }

    public boolean makeMove(int field){

        int [] xy = translateFieldToXY(field);

        if (field < 1 || field > 9) {
            System.out.println("This field doesn`t exist you moron!");
            return false;
        }

        return fillBoard(xy[0], xy[1]);
    }

    public int[] translateFieldToXY(int field){
        int[] result = new int[2];

        result[0] = (field-1) % 3; // cell (x)
        result[1] = (field-1) / 3; // row (y)

        return result;
    }

    public boolean fillBoard(int x, int y){

        char[][] tempGameBoard = new char[3][3];
        for (int i = 0; i < 3; i++){
            for(int t = 0; t < 3; t++){
                tempGameBoard[t][i] = gameBoard[t][i];
            }
        }

        if(gameBoard[y][x] == ' ') {
            if(player == 0) {
                tempGameBoard[y][x] = PLAYER_0;
            } else {
                tempGameBoard[y][x] = PLAYER_1;
            }
        }

        if(tempGameBoard[y][x] == gameBoard[y][x]){
            return false;
        }
        updateGameBoard(tempGameBoard);
        return true;
    }

    private void updateGameBoard(char[][] tempGameBoard){
        for (int i = 0; i < 3; i++) {
            for (int t = 0; t < 3; t++) {
                gameBoard[t][i] = tempGameBoard[t][i];
            }
        }
    }

    public void showGameBoard(){

        System.out.println("  " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2]);
        System.out.println("  " + "--+---+--");
        System.out.println("  " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2]);
        System.out.println("  " + "--+---+--");
        System.out.println("  " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2]);

    }
}
