package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;
import info.jemsit.chessFigure.premove.PreMoveSquare;
import javafx.application.Platform;

public class KnightPreMove extends PreMoveImpl {


    // Knight moves: (x, y) offsets
    int[] knightXMoves = {1, 1, 2, 2, -1, -1, -2, -2};
    int[] knightYMoves = {2, -2, 1, -1, 2, -2, 1, -1};


    public KnightPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
        Platform.runLater(this::addPreMoves);
    }

    @Override
    public void addPreMoves() {
        for (int i = 0; i < knightXMoves.length; i++) {
            int newX = currentXCoordinate + knightXMoves[i];
            int newY = currentYCoordinate + knightYMoves[i];

            if (isValidMove(newX, newY)) {
                ApplicationStart.premovefigureGroup.getChildren().add(new PreMoveDot(newX, newY, isWhite)); // Add pre-move knight figure
            }
            ChessFigureImpl chessFigure = ApplicationStart.hasFigureAt(newX, newY);
            if (chessFigure != null && chessFigure.isWhite != isWhite) {
                ApplicationStart.premovefigureGroup.getChildren().add(new PreMoveSquare(newX, newY,isWhite)); // Add pre-move knight figure
            }
        }
    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 && ApplicationStart.hasFigureAt(x, y) == null;
    }
}

