package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveSquareKingMove;

public class PreMoveImpl implements PreMove {

    public int currentXCoordinate;
    public  int currentYCoordinate;
    public boolean isWhite;
    public boolean forKingSafety;

    @Override
    public void addPreMoves() {}

    @Override
    public boolean isValidMove(int x, int y) {
        return false;
    }

    @Override
    public void addPreMoveToSafeKingMoves(int x, int y ) {
        ApplicationStart.kingSafetyCheck.getChildren().add(new PreMoveSquareKingMove(x, y, isWhite));
    }
}
