package com.Fabian;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        //singleTicTacToe();
        multiTicTacToe();
    }

    public static void multiTicTacToe() {
        Scanner scanner = new Scanner(System.in);
        TicTacToe[] game = new TicTacToe[9];
        TicTacToe masterGame = new TicTacToe();

        boolean autoPlayer = false;
        boolean validMove = false;

        int inputField = 0;

        int field = 0;

        for (int i = 0; i < 9; i++) {
            game[i] = new TicTacToe();
        }

        System.out.print("choose in which field you want to start: ");
        field = (scanner.nextInt() - 1);
        System.out.println(" ");

        if (field < 0 || field > 9) {
            System.out.println("This field doesn`t exist!");
            multiTicTacToe();
        }

        while (!masterGame.hasPlayerWon()) {

            if (game[field].hasPlayerWon()){
                System.out.println("This field is already won!");
                System.out.print("please choose a field: ");
                field = (scanner.nextInt() - 1);
                System.out.println(" ");
                continue;
            }

            System.out.println("You are currently in Field: " + (field + 1));
            if (game[field].getPlayer() == 0) {
                System.out.println(String.format("Turn of player %c!", TicTacToe.PLAYER_0));
            } else {
                System.out.println(String.format("Turn of player %c!", TicTacToe.PLAYER_1));
            }

            game[field].showGameBoard();

            System.out.println("following Fields are still empty");
            game[field].checkFreeFields(true);

            System.out.print("choose field: ");
            inputField = scanner.nextInt();
            validMove = game[field].makeMove(inputField);

            if (!validMove) {
                System.out.println("This position is already used");
                continue;
            }

            if (game[field].hasPlayerWon()) { //check if the player has won
                System.out.println("Congrats! You have won a field!");
                masterGame.setPlayer(game[field].getPlayer());
                masterGame.makeMove(inputField);
            }

            if (game[field].checkFreeFields(false)) {
                System.out.println("You lost booth in this field, you idiots!");
            }

            if (masterGame.hasPlayerWon()) { //check if the player has won
                System.out.println("Congrats! You have won the Game!");
                break;
            }

            if (masterGame.checkFreeFields(false)) {
                System.out.println("You booth lost the whole game!");
                break;
            }

            for (int i = 0; i < 9; i++){
                game[i].changePlayer();
            }

            field = inputField - 1;
/*
            System.out.print("Do you want to see the master board?: ");
            if (scanner.next() == "Yes"){
                masterGame.showGameBoard();
            } */
        }
    }

    public static void singleTicTacToe() {

        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        boolean autoPlayer = true;
        boolean validMove = false;

                while (!game.hasPlayerWon()) {
                    if (game.getPlayer() == 0) {
                        System.out.println(String.format("Turn of player %c!", TicTacToe.PLAYER_0));
                    } else {
                        System.out.println(String.format("Turn of player %c!", TicTacToe.PLAYER_1));
                    }

                    if ((game.getPlayer() == 0 && !autoPlayer) || game.getPlayer() == 1) {
                        System.out.println("following Fields are still empty");
                        game.checkFreeFields(true);

                        System.out.print("choose field: ");
                        validMove = game.makeMove(scanner.nextInt());
                    } else {
                        System.out.println("");
                        validMove = game.makeMove((int) (Math.random() * 9) + 1);
                    }

                    if (!validMove) {
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

                    if (game.checkFreeFields(false)) {
                        System.out.println("You lost booth, you idiots!");
                        break;
                    }
                    game.changePlayer();
                }
                scanner.close();
            }
}