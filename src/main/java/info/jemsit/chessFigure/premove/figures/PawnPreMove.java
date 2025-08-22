package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;

public class PawnPreMove extends PreMoveImpl {

    int[] pawnMoves = {1, -1};
    boolean isFirstMove;


    public PawnPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite, boolean isFirstMove) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
        this.isFirstMove = isFirstMove;
    }

    @Override
    public void addPreMoves() {
        add();
        if (this.isFirstMove) add();
    }

    private void add() {

        if (!isWhite) currentYCoordinate++;
        else  currentYCoordinate--;

        if (isValidMove(currentXCoordinate, currentYCoordinate) && ApplicationStart.hasFigureAt(currentXCoordinate, currentYCoordinate) == null) {
            ApplicationStart.premovefigureGroup.getChildren().add(
                    new PreMoveDot(currentXCoordinate, currentYCoordinate) // true,true → maybe premove flag
            );
        }
    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}

