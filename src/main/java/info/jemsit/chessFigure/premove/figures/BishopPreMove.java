package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;

public class BishopPreMove extends PreMoveImpl {


    // Diagonal directions: top-right, top-left, bottom-right, bottom-left
    int[] bishopXMoves = {1, 1, -1, -1};
    int[] bishopYMoves = {1, -1, 1, -1};

    public BishopPreMove(int currentXCoordinate, int currentYCoordinate) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
    }

    @Override
    public void addPreMoves() {
        for (int dir = 0; dir < bishopXMoves.length; dir++) {
            int newX = currentXCoordinate;
            int newY = currentYCoordinate;

            while (true) {
                newX += bishopXMoves[dir];
                newY += bishopYMoves[dir];

                if (!isValidMove(newX, newY)) break;
                if (ApplicationStart.hasFigureAt(newX, newY)) break;

                ApplicationStart.premovefigureGroup.getChildren().add(
                        new PreMoveDot(newX, newY) // true,true → maybe premove flag
                );
            }
        }

    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
