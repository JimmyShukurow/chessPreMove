package info.jemsit;

import info.jemsit.chessFigure.ChessFigure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private ChessFigure chessFigure;

    public boolean hasChessFigure() {
        return chessFigure != null;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(ApplicationStart.TILE_SIZE);
        setHeight(ApplicationStart.TILE_SIZE);
        relocate(x * ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);
        setFill(light ? Color.BROWN : Color.GRAY);
    }

}
