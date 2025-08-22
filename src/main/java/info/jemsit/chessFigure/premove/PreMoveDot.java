package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.FigureTypes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PreMoveDot extends ChessFigureImpl {

    public PreMoveDot(int x, int y) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        type = FigureTypes.KNIGHT;

        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);

        Image image = new Image("images/dot.png", ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE, false, false);
        ImageView imageView = new ImageView(image);

        imageView.setOpacity(0.3);

        getChildren().add(imageView);
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseDragExited);
    }
}
