package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveSquare;
import javafx.application.Platform;

public class QueenPreMove extends PreMoveImpl {

    int[] queenXMovesDiagonal = {1, 1, -1, -1};
    int[] queenYMovesDiagonal = {1, -1, 1, -1};
    int[] queenXMovesDirect = {1, -1, 0, 0};
    int[] queenYMovesDirect = {0, 0, 1, -1};

    public QueenPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite, boolean forKingSafety) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
        this.forKingSafety = forKingSafety;
        Platform.runLater(this::addPreMoves);
    }

    @Override
    public void addPreMoves() {
        for (int dir = 0; dir < queenYMovesDiagonal.length; dir++) {
            int newX = currentXCoordinate;
            int newY = currentYCoordinate;

            while (true) {
                newX += queenXMovesDiagonal[dir];
                newY += queenYMovesDiagonal[dir];

                if (!isValidMove(newX, newY)) break;
                ChessFigureImpl chessFigure = ApplicationStart.hasFigureAt(newX, newY);
                if (chessFigure != null) {
                    if (chessFigure.isWhite != isWhite && !forKingSafety) {
                        ApplicationStart.premovefigureGroup.getChildren().add(
                                new PreMoveSquare(newX, newY, isWhite) // true,true → maybe premove flag
                        );
                    }
                    break;
                }
                if (this.forKingSafety) {
                    super.addPreMoveToSafeKingMoves(newX, newY);
                } else {

                    ApplicationStart.premovefigureGroup.getChildren().add(
                            new PreMoveDot(newX, newY, isWhite) // true,true → maybe premove flag
                    );
                }
            }
            newX = currentXCoordinate;
            newY = currentYCoordinate;
            while (true) {
                newX += queenXMovesDirect[dir];
                newY += queenYMovesDirect[dir];

                if (!isValidMove(newX, newY)) break;

                ChessFigureImpl chessFigure = ApplicationStart.hasFigureAt(newX, newY);

                if (chessFigure != null) {
                    if (chessFigure.isWhite != isWhite && !forKingSafety) {
                        ApplicationStart.premovefigureGroup.getChildren().add(
                                new PreMoveSquare(newX, newY, isWhite) // true,true → maybe premove flag
                        );
                    }
                    break;
                }
                if (forKingSafety) {
                    super.addPreMoveToSafeKingMoves(newX, newY);
                } else {
                    ApplicationStart.premovefigureGroup.getChildren().add(
                            new PreMoveDot(newX, newY, isWhite) // true,true → maybe premove flag
                    );
                }
            }
        }
    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}

