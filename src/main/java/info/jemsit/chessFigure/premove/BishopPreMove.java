package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.BishopFigure;

public class BishopPreMove {

    private final int currentXCoordinate;
    private final int currentYCoordinate;

    // Diagonal directions: top-right, top-left, bottom-right, bottom-left
    int[] bishopXMoves = {1, 1, -1, -1};
    int[] bishopYMoves = {1, -1, 1, -1};

    public BishopPreMove(int currentXCoordinate, int currentYCoordinate) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
    }

    public void addPreMoves() {
        for (int dir = 0; dir < bishopXMoves.length; dir++) {
            int newX = currentXCoordinate;
            int newY = currentYCoordinate;

            while (true) {
                newX += bishopXMoves[dir];
                newY += bishopYMoves[dir];

                if (!isValidMove(newX, newY)) break;

                ApplicationStart.preMoveChessBoard[newX][newY] = 1; // Mark pre-move
                ApplicationStart.premovefigureGroup.getChildren().add(
                        new BishopFigure(newX, newY, true, true) // true,true → maybe premove flag
                );

                // If the square is occupied, bishop can’t go further
                if (ApplicationStart.chessBoard[newX][newY] != 0) break;
            }
        }

        ApplicationStart.displayPreMoveChessBoard(); // refresh display
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
