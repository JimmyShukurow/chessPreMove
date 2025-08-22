package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;

public class RookPreMove extends PreMoveImpl {

    int[] rookXMoves = {1, -1, 0, 0};
    int[] rookYMoves = {0, 0, 1, -1};


    public RookPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
    }

    @Override
    public void addPreMoves() {
        for (int dir = 0; dir < rookXMoves.length; dir++) {
            int newX = currentXCoordinate;
            int newY = currentYCoordinate;

            while (true) {
                newX += rookXMoves[dir];
                newY += rookYMoves[dir];

                if (!isValidMove(newX, newY)) break;
                if (ApplicationStart.hasFigureAt(newX, newY) != null) break;

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

