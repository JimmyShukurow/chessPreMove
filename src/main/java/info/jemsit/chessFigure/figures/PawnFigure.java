package info.jemsit.chessFigure.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.FigureTypes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PawnFigure extends ChessFigureImpl {


    public PawnFigure(int x, int y, boolean isWhite) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        this.isWhite = isWhite;
        type = FigureTypes.PAWN;
        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);
        Image image = new Image("images/pawnb.png", ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE, false, false);
        ImageView imageView = new ImageView(image);

        getChildren().add(imageView);
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseDragExited);
    }



}
