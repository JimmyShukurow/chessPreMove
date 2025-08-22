package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.FigureTypes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PreMoveDot extends ChessFigureImpl {

    public PreMoveDot(int x, int y) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        type = FigureTypes.KNIGHT;

        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);


        Circle circle = new Circle(ApplicationStart.TILE_SIZE / 6.0, Color.color(1,0,0,0.3));

        StackPane stack = new StackPane(circle);
        stack.setPrefSize(ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE);


        getChildren().add(stack);
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseDragExited);
    }
}
