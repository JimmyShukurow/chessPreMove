package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigure;
import javafx.application.Platform;

public class PreMoveFigure implements Runnable {

    private final ChessFigure figureType;

    public PreMoveFigure(ChessFigure figureType) {
        this.figureType = figureType;
    }

    public static void clearPreMove() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ApplicationStart.preMoveChessBoard[i][j] = 0; // Clear pre-move chess board
            }
        }
        Platform.runLater(()->{
            ApplicationStart.premovefigureGroup.getChildren().clear();
        });
    }

    @Override
    public void run() {
        switch (figureType.getFigureType()){
            case KNIGHT -> {
                KnightPreMove knightPreMove = new KnightPreMove(figureType.getXCoordinate(), figureType.getYCoordinate());
                Platform.runLater((knightPreMove::addPreMoves));
            }
            case BISHOP -> {
                BishopPreMove bishopPreMove = new BishopPreMove(figureType.getXCoordinate(), figureType.getYCoordinate());
                Platform.runLater((bishopPreMove::addPreMoves));
            }
        }
    }
}
