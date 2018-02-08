package com.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    private List<Character> grid;
    private Character turn;

    public TicTacToe() {
        List<Character> expectedGrid = new ArrayList<Character>();

        for (int i = 0; i <= 8; i++) {
            expectedGrid.add(i, '1');
        }
        this.grid = expectedGrid;
        this.turn = 'O';
    }

    public List<Character> getGrid() {
        return grid;
    }

    public Character getTurn() {
        return turn;
    }

    public Character play(int index, Character symbol) {
        symbol = Character.toUpperCase(symbol);
        if (!symbol.equals(turn))
            throw new PlayerTurnException("Now is not Your turn!");
        insert(index, symbol);
        if (checkWinner() == true)
            return symbol;

        this.turn = symbol.equals('O') ? 'X' : 'O';
        return null;
    }

    public void insert(int index, Character symbol) {
        validateGridIndex(index);
        validateGridSymbol(symbol);
        Character currentCharacter = grid.get(index);
        validateIndexFree(currentCharacter);
        grid.set(index, symbol);
    }

    private boolean checkWinner() {
        return checkLineWinner() || checkColumnWinner() || checkDiagonalWinner();

    }

    private boolean checkColumnWinner() {
        boolean winner = false;
        for (int i = 0; i <= 8; i++) {
            if (grid.get(i).equals('1'))
                continue;
            if (grid.get(i).equals(grid.get(i + 3)) && grid.get(i).equals(grid.get(i + 6))) {
                winner = true;
                break;
            }
        }
        return winner;
    }


    private boolean checkLineWinner() {
        boolean winner = false;
        for (int i = 0; i <= 8; i = i + 3) {
            if (grid.get(i).equals('1'))
                continue;
            if (grid.get(i).equals(grid.get(i + 1)) && grid.get(i).equals(grid.get(i + 2))) {
                winner = true;
                break;
            }

        }
        return winner;
    }

    private boolean checkDiagonalWinner() {
        boolean winner = false;
        if (!grid.get(0).equals('1')) {
            if (grid.get(0).equals(grid.get(4)) && grid.get(0).equals(grid.get(8))) {
                return true;
            }
        }
        if (!grid.get(2).equals('1')) {
            if (grid.get(2).equals(grid.get(4)) && grid.get(2).equals(grid.get(6))) {
                return true;
            }
        }
        return false;
    }

    private void validateIndexFree(Character currentCharacter) {
        if (!currentCharacter.equals('1'))
            throw new InsertException("Index is currently occupied!");
    }


    private void validateGridSymbol(Character symbol) {
        if (symbol != 'x' && symbol != 'o' && symbol != 'X' && symbol != 'O')
            throw new InsertException("Symbol is different than x and o");
    }

    private void validateGridIndex(int index) {
        if (index > 8)
            throw new InsertException("Index is above 8!");
        if (index < 0)
            throw new InsertException("Index is below 0!");
    }

    private void printGrid() {
        int counter = 1;
        for (int j = 0; j <= 8; j++) {
            counter++;
            System.out.print(grid.get(j) + " ");
            if (counter == 4 || counter == 7) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();
        int index;

        while (true) {
            Character currentPlayer = ticTacToe.getTurn();
            System.out.println();
            System.out.println("Player: " + currentPlayer + " Now it's your Turn!");
            index = sc.nextInt();
            ticTacToe.play(index, ticTacToe.getTurn());
            System.out.println();
            ticTacToe.printGrid();
            ticTacToe.checkWinner();
        }

    }
}
