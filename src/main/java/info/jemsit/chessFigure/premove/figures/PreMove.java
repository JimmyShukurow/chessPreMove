package info.jemsit.chessFigure.premove.figures;

public interface PreMove {
    void addPreMoves();
    boolean isValidMove(int x, int y);
    void addPreMoveToSafeKingMoves(int x, int y);


}
