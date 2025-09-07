package info.jemsit;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(boolean light, int x, int y) {
        setWidth(ApplicationStart.TILE_SIZE);
        setHeight(ApplicationStart.TILE_SIZE);
        relocate(x * ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);
        setFill(light ? Color.DARKGREY : Color.WHEAT);
    }

}
