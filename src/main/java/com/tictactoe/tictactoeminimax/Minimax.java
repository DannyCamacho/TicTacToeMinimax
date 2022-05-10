package com.tictactoe.tictactoeminimax;

import com.tictactoe.message.*;

public class Minimax {
    private Boolean isMovesLeft(char[] boardState) {
        for (int i = 0; i < 9; ++i)
            if (boardState[i] == 0) return true;
        return false;
    }

    private int evaluate(char[] boardState) {
        for (int i = 0; i < 3; ++i) {
            if (boardState[3 * i] == boardState[3 * i + 1] && boardState[3 * i] == boardState[3 * i + 2]) {
                if (boardState[3 * i] == 'X') return 10;
                else if (boardState[3 * i] == 'O') return -10;
            }
            if (boardState[i] == boardState[3 + i] && boardState[i] == boardState[6 + i]) {
                if (boardState[i] == 'X') return 10;
                else if (boardState[i] == 'O') return -10;
            }
        }
        if (boardState[0] == boardState[4] && boardState[0] == boardState[8]) {
            if (boardState[0] == 'X') return 10;
            else if (boardState[0] == 'O') return -10;
        }
        if (boardState[6] == boardState[4] && boardState[6] == boardState[2]) {
            if (boardState[6] == 'X') return 10;
            else if (boardState[6] == 'O') return -10;
        }
    return 0;
    }

    private int miniMax(char[] boardState, Boolean isMax, int alpha, int beta) {
        int score = evaluate(boardState);
        if (score != 0 || !isMovesLeft(boardState)) return score;

        if (isMax) {
            int maxEval = -999;
            for (int i = 0; i < 9; ++i) {
                if (boardState[i] == 0) {
                    boardState[i] = 'X';
                    maxEval = Math.max(maxEval, miniMax(boardState, false, alpha, beta));
                    alpha = Math.max(alpha, maxEval);
                    boardState[i] = 0;
                    if (beta <= alpha) break;
                }
            } return maxEval;
        } else {
            int minEval = 999;
            for (int i = 0; i < 9; ++i) {
                if (boardState[i] == 0) {
                    boardState[i] = 'O';
                    minEval = Math.min(minEval, miniMax(boardState, true, alpha, beta));
                    beta = Math.min(beta, minEval);
                    boardState[i] = 0;
                    if (beta <= alpha) break;
                }
            } return minEval;
        }
    }

    public PlayerMoveSend getMove(MinimaxMoveSend move) {
        char [] boardState = move.boardState();
        int maxEval = -999;
        int pos = -1;

        for (int i = 0; i < 9; ++i) {
            if (boardState[i] == 0) {
                boardState[i] = 'X';
                int moveEval = miniMax(boardState, false, -999, 999);
                boardState[i] = 0;

                if (moveEval > maxEval) {
                    pos = i;
                    maxEval = moveEval;
                }
            }
        }
        return new PlayerMoveSend(move.gameName(), move.playerToken(), pos, move.boardState());
    }
}