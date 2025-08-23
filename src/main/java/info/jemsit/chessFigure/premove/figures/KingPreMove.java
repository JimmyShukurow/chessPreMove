package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;
import javafx.application.Platform;

public class KingPreMove extends PreMoveImpl {

    int[] kingXMovesDiagonal = {1, 1, -1, -1};
    int[] kingYMovesDiagonal = {1, -1, 1, -1};
    int[] kingXMovesDirect = {1, -1, 0, 0};
    int[] kingYMovesDirect = {0, 0, 1, -1};

    public KingPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
        Platform.runLater((this::addPreMoves));
    }

    @Override
    public void addPreMoves() {
        for (int dir = 0; dir < kingYMovesDirect.length; dir++) {
            int newX = currentXCoordinate;
            int newY = currentYCoordinate;

            newX += kingXMovesDiagonal[dir];
            newY += kingYMovesDiagonal[dir];

            if (isValidMove(newX, newY) && ApplicationStart.hasFigureAt(newX, newY) == null) {
                ApplicationStart.premovefigureGroup.getChildren().add(
                        new PreMoveDot(newX, newY ,isWhite) // true,true → maybe premove flag
                );
            }

            newX = currentXCoordinate;
            newY = currentYCoordinate;

            newX += kingXMovesDirect[dir];
            newY += kingYMovesDirect[dir];

            if (isValidMove(newX, newY) && ApplicationStart.hasFigureAt(newX, newY) == null) {
                ApplicationStart.premovefigureGroup.getChildren().add(
                        new PreMoveDot(newX, newY,isWhite) // true,true → maybe premove flag
                );
            }
        }
    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}

