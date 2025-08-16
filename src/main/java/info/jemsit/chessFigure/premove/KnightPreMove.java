package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.KnightFigure;

public class KnightPreMove {

    private final int currentXCoordinate;
    private final int currentYCoordinate;

    int[] knightXMoves = {1, 1, 2, 2, -1, -1, -2, -2};
    int[] knightYMoves = {2, -2, 1, -1, 2, -2, 1, -1};


    public KnightPreMove(int currentXCoordinate, int currentYCoordinate) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
    }

    public void addPreMoves(){
        for (int i = 0; i < knightXMoves.length; i++) {
            int newX = currentXCoordinate + knightXMoves[i];
            int newY = currentYCoordinate + knightYMoves[i];

            if (isValidMove(newX, newY)) {
                ApplicationStart.preMoveChessBoard[newX][newY] = 1; // Assuming 1 represents a pre-move
                ApplicationStart.premovefigureGroup.getChildren().add(new KnightFigure(newX, newY, true, true)); // Add pre-move knight figure
            }
        }
        ApplicationStart.displayPreMoveChessBoard(); // Display the pre-move chess board
    }
    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 && ApplicationStart.chessBoard[x][y] == 0;
    }
}

