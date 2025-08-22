package info.jemsit.chessFigure;

import javafx.scene.input.MouseEvent;

public interface Moveable {
    void handleMousePressed(MouseEvent mouseEvent);
    void handleMouseDragged(MouseEvent mouseEvent);
    void handleMouseDragExited(MouseEvent mouseEvent);
    boolean canNotMoveTo(int x, int y);
}
