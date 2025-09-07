package info.jemsit.chessFigure;


public interface ChessFigure extends Moveable {

    FigureTypes getFigureType();
    int getXCoordinate();
    int getYCoordinate();
    void capture();
}
