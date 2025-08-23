package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.FigureTypes;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PreMoveDot extends ChessFigureImpl {

    public PreMoveDot(int x, int y, boolean isWhite) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        type = FigureTypes.KNIGHT;

        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);
        Circle circle = new Circle(ApplicationStart.TILE_SIZE / 6.0, Color.color(1, 0, 0, 0.3));
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(2);
        if (isWhite){
            circle.setFill(Color.color(0,1,0,0.3));
            circle.setStroke(Color.BLACK);
        }
        StackPane stack = new StackPane(circle);
        stack.setPrefSize(ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE);

        getChildren().add(stack);
    }
}
