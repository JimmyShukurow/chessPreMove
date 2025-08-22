package info.jemsit.chessFigure.premove;

public class PreMoveImpl implements PreMove {

    public int currentXCoordinate;
    public  int currentYCoordinate;
    public boolean isWhite;

    @Override
    public void addPreMoves() {}

    @Override
    public boolean isValidMove(int x, int y) {
        return false;
    }
}
