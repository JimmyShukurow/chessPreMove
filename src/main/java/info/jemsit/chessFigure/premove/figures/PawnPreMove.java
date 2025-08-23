package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;
import info.jemsit.chessFigure.premove.PreMoveSquare;
import javafx.application.Platform;

public class PawnPreMove extends PreMoveImpl {

    boolean isFirstMove;


    public PawnPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite, boolean isFirstMove) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
        this.isFirstMove = isFirstMove;
        Platform.runLater((this::addPreMoves));

    }

    @Override
    public void addPreMoves() {
        add();
        addTargetsIfThereIs();
        if (this.isFirstMove) {
            if (isWhite && ApplicationStart.hasFigureAt(currentXCoordinate, currentYCoordinate) == null) add();
            if (!isWhite && ApplicationStart.hasFigureAt(currentXCoordinate, currentYCoordinate) == null) add();
        }
    }

    private void add() {

        if (isWhite) currentYCoordinate--;
        else  currentYCoordinate++;

        if (isValidMove(currentXCoordinate, currentYCoordinate) && ApplicationStart.hasFigureAt(currentXCoordinate, currentYCoordinate) == null) {
            ApplicationStart.premovefigureGroup.getChildren().add(
                    new PreMoveDot(currentXCoordinate, currentYCoordinate, isWhite)
            );
        }

    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private void addTargetsIfThereIs() {
        ChessFigureImpl chessFigureAtRight = ApplicationStart.hasFigureAt(currentXCoordinate + 1, currentYCoordinate);
        if (chessFigureAtRight != null && chessFigureAtRight.isWhite != isWhite ) {
            ApplicationStart.premovefigureGroup.getChildren().add(
                    new PreMoveSquare(currentXCoordinate + 1, currentYCoordinate, isWhite)
            );
        }

        ChessFigureImpl chessFigureAtLeft = ApplicationStart.hasFigureAt(currentXCoordinate - 1, currentYCoordinate);
        if (chessFigureAtLeft != null && chessFigureAtLeft.isWhite != isWhite ) {
            ApplicationStart.premovefigureGroup.getChildren().add(
                    new PreMoveSquare(currentXCoordinate - 1, currentYCoordinate, isWhite)
            );
        }
    }
}

