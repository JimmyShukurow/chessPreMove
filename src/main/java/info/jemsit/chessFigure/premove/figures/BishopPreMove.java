package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveSquare;
import javafx.application.Platform;

public class BishopPreMove extends PreMoveImpl {


    // Diagonal directions: top-right, top-left, bottom-right, bottom-left
    int[] bishopXMoves = {1, 1, -1, -1};
    int[] bishopYMoves = {1, -1, 1, -1};

    public BishopPreMove(int currentXCoordinate, int currentYCoordinate, boolean isWhite, boolean forKingSafety) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.isWhite = isWhite;
        this.forKingSafety = forKingSafety;
        Platform.runLater(this::addPreMoves);
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
                ChessFigureImpl chessFigure = ApplicationStart.hasFigureAt(newX, newY);
                if (chessFigure != null) {
                    if (chessFigure.isWhite != this.isWhite) {
                        ApplicationStart.premovefigureGroup.getChildren().add(
                                new PreMoveSquare(newX, newY, isWhite)
                        );
                    }
                    break;
                }
                if (forKingSafety) {
                    super.addPreMoveToSafeKingMoves(newX, newY);
                } else {

                    ApplicationStart.premovefigureGroup.getChildren().add(
                            new PreMoveDot(newX, newY, isWhite)
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
