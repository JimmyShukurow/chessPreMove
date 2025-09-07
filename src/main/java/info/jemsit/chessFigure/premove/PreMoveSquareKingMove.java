package info.jemsit.chessFigure.premove;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.FigureTypes;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PreMoveSquareKingMove extends ChessFigureImpl {

    public PreMoveSquareKingMove(int x, int y, boolean isWhite) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        type = FigureTypes.KNIGHT;

        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);
        Rectangle square = new Rectangle(
                ApplicationStart.TILE_SIZE , // width
                ApplicationStart.TILE_SIZE  // height
        );
        square.setArcWidth(0);
        square.setArcHeight(0);
        square.setFill(Color.color(1, 1, 0, 0.3));
        square.setStroke(Color.WHITE);
        square.setStrokeWidth(2);

        StackPane stack = new StackPane(square);
        stack.setPrefSize(ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE);

        getChildren().add(stack);
    }
}
