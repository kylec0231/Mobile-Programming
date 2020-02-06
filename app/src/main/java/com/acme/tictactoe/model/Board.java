package com.acme.tictactoe.model;

import android.util.Log;

import static com.acme.tictactoe.model.Player.O;
import static com.acme.tictactoe.model.Player.X;

public class Board {

    private Cell[][] cells = new Cell[6][7];

    private Player winner;
    private GameState state;
    private Player currentTurn;

    private enum GameState { IN_PROGRESS, FINISHED };

    public Board() {
        restart();
    }

    /**
     *  Restart or start a new game, will clear the board and win status
     */
    public void restart() {
        clearCells();
        winner = null;
        currentTurn = Player.X;
        state = GameState.IN_PROGRESS;
    }

    /**
     * Mark the current row for the player who's current turn it is.
     * Will perform no-op if the arguments are out of range or if that position is already played.
     * Will also perform a no-op if the game is already over.
     *
     * @param row 0..2
     * @param col 0..2
     * @return the player that moved or null if we did not move anything.
     *
     */
    public Player mark( int row, int col ) {

        Player playerThatMoved = null;

        if(isValid(row, col)) {

            cells[row][col].setValue(currentTurn);
            playerThatMoved = currentTurn;

            if(isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED;
                winner = currentTurn;

            } else {
                // flip the current turn and continue
                flipCurrentTurn();
            }
        }

        return playerThatMoved;
    }

    public Player getWinner() {
        return winner;
    }

    private void clearCells() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private boolean isValid(int row, int col ) {
        if( state == GameState.FINISHED ) {
            return false;
        } else if( isOutOfBounds(row,col)) {
            return false;
        } else if( isCellValueAlreadySet(row, col) ) {
            return false;
        }
          else if (row < 5 && !isCellValueAlreadySet(row+1,col)) {
            Log.i("TAG","Invalid Cell");
              return false;
        } else{
            Log.i("TAG","Valid Cell");
            return true;
        }
    }

    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row > 5 || col < 0 || col > 6;
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }


    /**
     * Algorithm adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
     * @param player
     * @param currentRow
     * @param currentCol
     * @return true if <code>player</code> who just played the move at the <code>currentRow</code>, <code>currentCol</code>
     *              has a tic tac toe.
     */
    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {
        //Check Vertical
        int numInARow = 0;
        for(int i = 0; i< 6; i++){
            if(cells[i][currentCol].getValue() == player){
                if(++numInARow == 4) return true;
            } else
                numInARow = 0;
        }
        //Check Horizontal
        numInARow = 0;
        for(int i = 0; i< 7; i++) {
            if (cells[currentRow][i].getValue() == player) {
                if (++numInARow == 4) return true;
            } else{
                numInARow = 0;
            }
        }
        //Check Diagonal 1
        numInARow = 0;
        int col = 0;
        int row = 0;
        switch (currentRow+currentCol){
            case 3:
                col = 0;
                row = 3;
                break;
            case 4:
                col = 0;
                row = 4;
                break;
            case 5:
                col = 0;
                row = 5;
                break;
            case 6:
                col = 1;
                row = 5;
                break;
            case 7:
                col = 2;
                row = 5;
                break;
            case 8:
                col = 3;
                row = 5;
                break;
            default:
                break;

        }
        while(row >= 0 && col < 7){
            if (cells[row--][col++].getValue() == player) {
                Log.i("numInARow","num"+numInARow + col + row);
                if (++numInARow == 4)
                    return true;
            } else{
                numInARow = 0;
            }
        }

        //Check Diagonal 2
        numInARow = 0;
        col = currentCol;
        row = currentRow;
        while(col > 0 && row > 0){
            col--;
            row--;
        }
        while(col < 7 && row < 6){
            if (cells[row++][col++].getValue() == player) {
                if (++numInARow == 4)
                    return true;
            } else{
                numInARow = 0;
            }
        }
        return false;
    }

    private void flipCurrentTurn() {
        currentTurn = currentTurn == X ? O : X;
    }

}
