package info.jemsit.chessFigure;


import javafx.scene.input.MouseEvent;

public interface ChessFigure extends Moveable {


    void updateChesBoard(int[][] chessboard, boolean clearPreviousPosition);

    FigureTypes getFigureType();

    int getXCoordinate();
    int getYCoordinate();
}
