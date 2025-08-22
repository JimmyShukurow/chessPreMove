package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;

public class PawnPreMove extends PreMoveImpl {

    int[] pawnMoves = {1, -1};
    boolean isWhite;


    public PawnPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
    }

    @Override
    public void addPreMoves() {
        int newY = currentYCoordinate;

        if (!isWhite) newY += pawnMoves[0];
        else  newY += pawnMoves[1];

        if (isValidMove(currentXCoordinate, newY) && !ApplicationStart.hasFigureAt(currentXCoordinate, newY)) {
            ApplicationStart.premovefigureGroup.getChildren().add(
                    new PreMoveDot(currentXCoordinate, newY) // true,true → maybe premove flag
            );
        }
    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}

