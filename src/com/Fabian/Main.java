package com.Fabian;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    }

    public static void multiTicTactToe(){
        /*
        9x9 TicTacToe
        Der reihe nach 1 move im 1 tictactoe der zweite move im zweiten tictactoe der dritte move im dritten tictactoe und so weiter.
        Zeige alle TicTacToes an

        update => rechter move rechtes tiktok i wird durch field bestimmt
                    welches tiktactoe schon gewonnen (abspeichern) wenn schon gewonnen freie wahl
         */
    }

    public static void singleTicTacToe(){

        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        boolean autoPlayer = true;
        boolean validMove = false;

        while(!game.hasPlayerWon()) {
            if (game.getPlayer() == 0){
                System.out.println(String.format("Turn of player %c!", TicTacToe.PLAYER_0));
            } else {
                System.out.println(String.format("Turn of player %c!", TicTacToe.PLAYER_1));
            }

            if ((game.getPlayer() == 0 && !autoPlayer) || game.getPlayer() == 1) {
                System.out.println("following Fields are still empty");
                game.checkFreeFields( true); //put here: show which fields are still empty

                System.out.print("choose field: ");
                validMove = game.makeMove(scanner.nextInt());
            } else {
                System.out.println("");
                validMove = game.makeMove((int)(Math.random()*9) + 1);
            }

            if (!validMove){
                if (game.getPlayer() == 0 || !autoPlayer || game.getPlayer() == 1) {
                    System.out.println("This position is already used");
                }
                continue;
            }
            game.showGameBoard();

            if (game.hasPlayerWon()) { //check if the player has won
                System.out.println("Congrats! You have won!");
                break; // while break
            }

            if (game.checkFreeFields(false)){
                System.out.println("You lost booth, you idiots!");
                break;
            }
            game.changePlayer();
        }
        scanner.close();
    }
}