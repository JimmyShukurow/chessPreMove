package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigure;
import info.jemsit.chessFigure.premove.figures.*;
import javafx.application.Platform;

public class PreMoveFigure {

    private final ChessFigure figureType;
    private final boolean isWhite;
    private boolean isFirstMove;
    public boolean forKingSafetyCheck = false;



    public PreMoveFigure(ChessFigure figureType, boolean isWhite, boolean isFirstMove) {
        this.figureType = figureType;
        this.isWhite = isWhite;
        this.isFirstMove = isFirstMove;
    }

    public PreMoveFigure(boolean forKingSafetyCheck, ChessFigure figureType, boolean isWhite) {
        this.figureType = figureType;
        this.isWhite = isWhite;
        this.forKingSafetyCheck = forKingSafetyCheck;
    }

    public static void clearPreMove() {
        Platform.runLater(() -> {
            ApplicationStart.premovefigureGroup.getChildren().clear();
            ApplicationStart.kingSafetyCheck.getChildren().clear();
        });
    }

    public void addPreMove() {
        switch (figureType.getFigureType()) {
            case KNIGHT -> new KnightPreMove(figureType.getXCoordinate(), figureType.getYCoordinate(), isWhite, this.forKingSafetyCheck);
            case BISHOP -> new BishopPreMove(figureType.getXCoordinate(), figureType.getYCoordinate(), isWhite, this.forKingSafetyCheck);
            case ROOK   -> new RookPreMove(figureType.getXCoordinate(), figureType.getYCoordinate(), isWhite, this.forKingSafetyCheck);
            case QUEEN  -> new QueenPreMove(figureType.getXCoordinate(), figureType.getYCoordinate(), isWhite, this.forKingSafetyCheck);
            case KING   -> new KingPreMove(figureType.getXCoordinate(), figureType.getYCoordinate(), isWhite, this.forKingSafetyCheck);
            case PAWN   -> new PawnPreMove(figureType.getXCoordinate(), figureType.getYCoordinate(), isWhite, this.isFirstMove, this.forKingSafetyCheck);
        }
    }
}
