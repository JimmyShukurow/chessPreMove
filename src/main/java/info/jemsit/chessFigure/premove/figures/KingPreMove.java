package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.Sides;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveFigure;
import javafx.application.Platform;
import javafx.scene.Node;

public class KingPreMove extends PreMoveImpl {

    int[] kingXMovesDiagonal = {1, 1, -1, -1};
    int[] kingYMovesDiagonal = {1, -1, 1, -1};
    int[] kingXMovesDirect = {1, -1, 0, 0};
    int[] kingYMovesDirect = {0, 0, 1, -1};

    public KingPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite,  boolean forKingSafety) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
        if (ApplicationStart.turn == Sides.WHITE && isWhite || ApplicationStart.turn == Sides.BLACK && !isWhite) {
            fillPreMovesToCheckKingMove();
        }
        Platform.runLater((this::addPreMoves));
        this.forKingSafety = forKingSafety;
    }

    @Override
    public void addPreMoves() {
        for (int dir = 0; dir < kingYMovesDirect.length; dir++) {
            int newX = currentXCoordinate;
            int newY = currentYCoordinate;

            newX += kingXMovesDiagonal[dir];
            newY += kingYMovesDiagonal[dir];

            //Straight axis
            if (forKingSafety)super.addPreMoveToSafeKingMoves(newX, newY);
            else if (isValidMove(newX, newY) && ApplicationStart.hasFigureAt(newX, newY) == null && !isForbidden(newX, newY)) {
                ApplicationStart.premovefigureGroup.getChildren().add(
                        new PreMoveDot(newX, newY ,isWhite) // true,true → maybe premove flag
                );

            }
            newX = currentXCoordinate;
            newY = currentYCoordinate;

            newX += kingXMovesDirect[dir];
            newY += kingYMovesDirect[dir];

            //Diagonal axis
            if (forKingSafety)super.addPreMoveToSafeKingMoves(newX, newY);
            else if (isValidMove(newX, newY) && ApplicationStart.hasFigureAt(newX, newY) == null && isValidMove(newX,newY) && !isForbidden(newX, newY)) {
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

    private void fillPreMovesToCheckKingMove() {
        for (Node node : ApplicationStart.figureGroup.getChildren()) {
            if(((ChessFigureImpl) node).isWhite != this.isWhite) {
                var figure = (ChessFigureImpl) node;
                PreMoveFigure preMoveThread = new  PreMoveFigure(true,figure, figure.isWhite);
                preMoveThread.addPreMove();
            }
        }
    }

    private boolean isForbidden(int x, int y) {
        return ApplicationStart.hasPreMoveFigureAtForKingSafety(x, y);
    }
}

