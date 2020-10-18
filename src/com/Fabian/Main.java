package com.Fabian;

import java.util.Scanner;

public class Main {

    static final char PLAYER_0 = 'X';
    static final char PLAYER_1 = 'O';

    public static void main(String[] args) {

        char[][] oldGameBoard = {{' ',' ',' '}, {' ',' ',' '}, {' ',' ',' '}};
        char[][] newGameBoard;
        Scanner scanner = new Scanner(System.in);
        boolean PlayerWon = false;
        boolean autoPlayer = false;
        boolean validMove;
        int player = (int)(Math.random() * 2);
        int field;
        int[] xy;


        while(!PlayerWon) {
            if (player == 0){
                System.out.println(String.format("Turn of player %c!", PLAYER_0));
            } else {
                System.out.println(String.format("Turn of player %c!", PLAYER_1));
            }

            if (player == 0 || !autoPlayer) {
                System.out.println("following Fields are still empty");
                checkFreeFields(oldGameBoard, true); //put here: show which fields are still empty

                System.out.print("choose field: ");
                field = scanner.nextInt();
            } else {
                System.out.println("");
                field = (int)(Math.random()*9) + 1;
            }
            xy = translateFieldToXY(field);

            // check if its correct field
            if (field < 1 || field > 9) {
                System.out.println("This field doesn`t exist you moron!");
                continue;
            }

            newGameBoard = fillBoard(xy[0], xy[1], oldGameBoard, player); //put input into the gameboard
            validMove = validMove(xy[0], xy[1], oldGameBoard, newGameBoard); //check if the boards are the same if yes the move wasn`t valid

            //check if the move was valid
            if (!validMove){
                if (player == 0 || !autoPlayer) {
                    System.out.println("This position is already used");
                }
                continue;
            }

            showGameBoard(newGameBoard);

            //copy the new board into the old one
            for (int i = 0; i < 3; i++) {
                for (int t = 0; t < 3; t++) {
                    oldGameBoard[t][i] = newGameBoard[t][i];
                }
            }

            //check the board if someone got 3 in a row
            PlayerWon = hasPlayerWon(newGameBoard);

            if (PlayerWon) { //check if the player has won
                System.out.println("Congrats! You have won!");
                break; // while break
            }

            if (checkFreeFields(newGameBoard, false)){
                System.out.println("You lost booth, you idiots!");
                break;
            }

            // change player
            if (player == 0){
                player++;
            } else {
                player--;
            }
        }

        scanner.close();
    }

    private static boolean hasPlayerWon(char[][]newGameBoard){ //check all possible combinations if there is a winning one already

        for (int i = 0; i < 3; i++){
            if ((newGameBoard[i][0] == PLAYER_0 && newGameBoard[i][1] == PLAYER_0 && newGameBoard[i][2] == PLAYER_0) ||
                    (newGameBoard[i][0] == PLAYER_1 && newGameBoard[i][1] == PLAYER_1 && newGameBoard[i][2] == PLAYER_1)){
                return true;
            }
            if ((newGameBoard[0][i] == PLAYER_0 && newGameBoard[1][i] == PLAYER_0 && newGameBoard[2][i] == PLAYER_0) ||
                    (newGameBoard[0][i] == PLAYER_1 && newGameBoard[1][i] == PLAYER_1 && newGameBoard[2][i] == PLAYER_1)){
                return true;
            }
        }

        if ((newGameBoard[0][0] == PLAYER_0 && newGameBoard[1][1] == PLAYER_0 && newGameBoard[2][2] == PLAYER_0) ||
                (newGameBoard[0][0] == PLAYER_1 && newGameBoard[1][1] == PLAYER_1 && newGameBoard[2][2] == PLAYER_1)){
            return true;
        }
        if ((newGameBoard[0][2] == PLAYER_0 && newGameBoard[1][1] == PLAYER_0 && newGameBoard[2][0] == PLAYER_0) ||
                (newGameBoard[0][2] == PLAYER_1 && newGameBoard[1][1] == PLAYER_1 && newGameBoard[2][0] == PLAYER_1)){
            return true;
        }
        return false;
    }

    private static boolean checkFreeFields(char[][] gameBoard, boolean print){
        int count = 0;
        for (int row = 0; row < 3; row++){
            for (int cell = 0; cell < 3; cell++){
                if(gameBoard[row][cell] == ' '){
                    if (print) {
                        System.out.print(String.format("%d, ", cell + 1 + 3 * row));
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

    private static int[] translateFieldToXY(int field){
        int[] result = new int[2];

        result[0] = (field-1) % 3; // cell (x)
        result[1] = (field-1) / 3; // row (y)

        return result;
    }

    private static boolean validMove(int x, int y, char[][] oldGameBoard, char[][] newGameBoard){
        if(oldGameBoard[y][x] == newGameBoard[y][x]){
            return false;
        }
        return true;
    }

    private static char[][] fillBoard(int x, int y,char[][] oldGameBoard,int player){

        char[][] tempGameBoard = new char[3][3];
        for (int i = 0; i < 3; i++){
            for(int t = 0; t < 3; t++){
                tempGameBoard[t][i] = oldGameBoard[t][i];
            }
        }

        if(oldGameBoard[y][x] == ' ') {
            if(player == 0) {
                tempGameBoard[y][x] = PLAYER_0;
            } else {
                tempGameBoard[y][x] = PLAYER_1;
            }

        }
        return tempGameBoard;
    }

    private static void showGameBoard(char[][] gameBoard){

        System.out.println("  " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2]);
        System.out.println("  " + "--+---+--");
        System.out.println("  " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2]);
        System.out.println("  " + "--+---+--");
        System.out.println("  " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2]);

    }
}