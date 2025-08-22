package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigure;
import info.jemsit.chessFigure.premove.figures.*;
import javafx.application.Platform;

public class PreMoveFigure {

    private final ChessFigure figureType;
    private final boolean isWhite;


    public PreMoveFigure(ChessFigure figureType, boolean isWhite) {
        this.figureType = figureType;
        this.isWhite = isWhite;
    }

    public static void clearPreMove() {
        Platform.runLater(() -> {
            ApplicationStart.premovefigureGroup.getChildren().clear();
        });
    }

    public void addPreMove() {
        switch (figureType.getFigureType()) {
            case KNIGHT -> {
                PreMove knightPreMove = new KnightPreMove(figureType.getXCoordinate(), figureType.getYCoordinate());
                Platform.runLater((knightPreMove::addPreMoves));
            }
            case BISHOP -> {
                PreMove bishopPreMove = new BishopPreMove(figureType.getXCoordinate(), figureType.getYCoordinate());
                Platform.runLater((bishopPreMove::addPreMoves));
            }
            case ROOK -> {
                PreMove rookPreMove = new RookPreMove(figureType.getXCoordinate(), figureType.getYCoordinate());
                Platform.runLater((rookPreMove::addPreMoves));
            }
            case QUEEN -> {
                PreMove queenPreMove = new QueenPreMove(figureType.getXCoordinate(), figureType.getYCoordinate());
                Platform.runLater((queenPreMove::addPreMoves));
            }
            case KING -> {
                PreMove kingPreMove = new KingPreMove(figureType.getXCoordinate(), figureType.getYCoordinate());
                Platform.runLater((kingPreMove::addPreMoves));
            }
            case PAWN -> {
                PreMove pawnPreMove = new PawnPreMove(figureType.getXCoordinate(), figureType.getYCoordinate(), isWhite );
                Platform.runLater((pawnPreMove::addPreMoves));
            }
        }
    }
}
