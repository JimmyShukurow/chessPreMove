package info.jemsit.chessFigure.premove;

public interface PreMove {
    void addPreMoves();

    boolean isValidMove(int x, int y);

}
